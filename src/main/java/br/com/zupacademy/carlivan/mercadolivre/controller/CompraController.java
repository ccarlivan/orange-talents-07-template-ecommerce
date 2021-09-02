package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.CompraForm;
import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.Gateway;
import br.com.zupacademy.carlivan.mercadolivre.security.TokenService;
import br.com.zupacademy.carlivan.mercadolivre.service.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.BindException;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private Email email;

    @PostMapping
    @Transactional
    public String compra(@RequestBody @Valid CompraForm form, UriComponentsBuilder builder) throws BindException {
        Produto produto = manager.find(Produto.class, form.getIdProduto());
        boolean abateu = produto.abateEstoque(form.getQuantidade());

        if (abateu) {
            Gateway gateway = form.getGateway();
            Compra compra = form.toCompra(manager, tokenService.getLogado());
            manager.persist(compra);
            if(gateway.equals(Gateway.PAGSEGURO)){
                String urlRetornoAppPosPagamento = builder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(compra.getId()).toString();
                email.sendCompra(compra);
                return "pagseguro.com?returnId="+compra.getId()+"&redirectUrl=" + urlRetornoAppPosPagamento;
            }else{
                String urlRetornoAppPosPagamento = builder.path("/retorno-paypal/{id}")
                        .buildAndExpand(compra.getId()).toString();
                email.sendCompra(compra);
                return "paypal.com?returnId="+compra.getId()+"&redirectUrl=" + urlRetornoAppPosPagamento;
            }
        }
        BindException semEstoque = new BindException("sem estoque");

        return semEstoque.toString();
    }
}
