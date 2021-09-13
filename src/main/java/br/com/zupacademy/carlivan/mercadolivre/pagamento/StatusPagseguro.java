package br.com.zupacademy.carlivan.mercadolivre.pagamento;

public enum StatusPagseguro {
    SUCESSO, ERRO;

    public StatusTransacao normaliza(){
        if(this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }
}
