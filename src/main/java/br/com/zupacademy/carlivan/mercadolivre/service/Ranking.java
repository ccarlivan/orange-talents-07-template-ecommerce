package br.com.zupacademy.carlivan.mercadolivre.service;

import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.EventoCompraSucesso;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        RestTemplate template = new RestTemplate();
        Map<String, Object> form = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProduto().getDono().getId());
        template.postForEntity("http://localhost:8080/ranking",form,String.class);
    }
}
