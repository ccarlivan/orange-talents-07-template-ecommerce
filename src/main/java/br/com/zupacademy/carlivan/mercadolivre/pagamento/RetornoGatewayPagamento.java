package br.com.zupacademy.carlivan.mercadolivre.pagamento;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.model.Transacao;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);
}
