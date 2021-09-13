package br.com.zupacademy.carlivan.mercadolivre.service;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.model.Transacao;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.Gateway;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.StatusTransacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

class EventoNovaCompraTest {
    private Compra compra;
    private Produto produto;
    private Usuario usuario;
    private Transacao transacao;
    private Set<Transacao> transacoes;
    private EventoNovaCompra eventoNovaCompra;
    Set<Transacao> transacoesConcluidasComsucesso;

    @BeforeEach
    void inicio(){
        produto = mock(Produto.class);
        usuario = mock(Usuario.class);
        transacoes = new HashSet<>();
        eventoNovaCompra = mock(EventoNovaCompra.class);
        transacoesConcluidasComsucesso = new HashSet<>();
    }

    @Test
    public void compraProcessadaComSucesso(){
        compra = new Compra(produto, 100, usuario, Gateway.PAYPAL,new BigDecimal(1000.0));
        transacao = new Transacao(StatusTransacao.sucesso,"1234",compra);

        if(!transacoes.contains(transacao)){
            if(transacao.getStatus().equals(StatusTransacao.sucesso)){
                transacoesConcluidasComsucesso.add(transacao);
            }
            transacoes.add(transacao);
        }
        boolean compraOk = !transacoesConcluidasComsucesso.isEmpty();
        Assertions.assertTrue(compraOk);
    }
    @Test
    public void compraProcessadaComErro(){
        compra = new Compra(produto, 100, usuario, Gateway.PAYPAL,new BigDecimal(1000.0));
        transacao = new Transacao(StatusTransacao.erro,"1234",compra);

        if(!transacoes.contains(transacao)){
            if(transacao.getStatus().equals(StatusTransacao.sucesso)){
                transacoesConcluidasComsucesso.add(transacao);
            }
            transacoes.add(transacao);
        }
        boolean compraOk = !transacoesConcluidasComsucesso.isEmpty();
        Assertions.assertFalse(compraOk);
    }

}