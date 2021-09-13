package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.PagseguroForm;
import br.com.zupacademy.carlivan.mercadolivre.controller.form.PaypalForm;
import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.RetornoGatewayPagamento;
import br.com.zupacademy.carlivan.mercadolivre.service.EventoNovaCompra;
import br.com.zupacademy.carlivan.mercadolivre.service.NotaFiscal;
import br.com.zupacademy.carlivan.mercadolivre.service.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class ConfirmaCompraController {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private EventoNovaCompra eventoNovaCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String confirmaPagseguro(@PathVariable("id") Long idCompra, @Valid PagseguroForm form){
        return processa(idCompra, form);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String confirmaPaypal(@PathVariable("id") Long idCompra, @Valid PaypalForm form){
        return processa(idCompra, form);
    }
    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento){
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        manager.merge(compra);
        eventoNovaCompra.processa(compra);
        return compra.toString();
    }
}
