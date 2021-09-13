package br.com.zupacademy.carlivan.mercadolivre.pagamento;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;

public interface EventoCompraSucesso {
    void processa(Compra compra);
}
