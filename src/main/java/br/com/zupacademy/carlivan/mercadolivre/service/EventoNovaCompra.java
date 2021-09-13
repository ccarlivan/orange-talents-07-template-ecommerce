package br.com.zupacademy.carlivan.mercadolivre.service;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.EventoCompraSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventoNovaCompra {
    @Autowired
    private Set<EventoCompraSucesso> eventoCompraSucesso;
    @Autowired
    private Email email;

    public void processa(Compra compra) {
        if(compra.processadaComSucesso()){
            eventoCompraSucesso.forEach(evento -> evento.processa(compra));
            email.sendSucesso(compra);
        }else {
            email.sendFail(compra);
        }
    }
}
