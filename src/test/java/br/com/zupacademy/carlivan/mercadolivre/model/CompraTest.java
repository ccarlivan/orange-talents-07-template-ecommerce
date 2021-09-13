package br.com.zupacademy.carlivan.mercadolivre.model;

import br.com.zupacademy.carlivan.mercadolivre.pagamento.StatusTransacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CompraTest {

    private Compra compra;
    private Transacao transacao;
    private Set<Transacao> transacoes;

    @BeforeEach
    void setUp() {
        compra = mock(Compra.class);
        transacoes = new HashSet<>();
    }

    @Test
    void podeAdicionarTransacao(){
        transacao = new Transacao(StatusTransacao.sucesso,"123456",compra);
        if(!transacoes.contains(transacao)){
            transacoes.add(transacao);
        }
        Assertions.assertEquals(1,transacoes.size());
    }
    @Test
    void naoPodeAdicionarAMesmaTransacao(){
        transacao = new Transacao(StatusTransacao.sucesso,"123456",compra);

        if(!transacoes.contains(transacao)){
            transacoes.add(transacao);
        }

        transacao = new Transacao(StatusTransacao.erro,"123456",compra);
        transacoes.add(transacao);
        Assertions.assertEquals(1,transacoes.size());
    }
}