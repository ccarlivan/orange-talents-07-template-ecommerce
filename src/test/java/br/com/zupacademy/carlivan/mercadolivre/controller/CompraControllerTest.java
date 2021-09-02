package br.com.zupacademy.carlivan.mercadolivre.controller;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.CaracteristicaForm;
import br.com.zupacademy.carlivan.mercadolivre.controller.form.CompraForm;
import br.com.zupacademy.carlivan.mercadolivre.model.Categoria;
import br.com.zupacademy.carlivan.mercadolivre.model.Compra;
import br.com.zupacademy.carlivan.mercadolivre.model.Produto;
import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import br.com.zupacademy.carlivan.mercadolivre.pagamento.Gateway;
import br.com.zupacademy.carlivan.mercadolivre.service.Email;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.BindException;
import java.util.Collection;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompraControllerTest {
    private Email email;
    private Compra compra;
    private Produto produto;
    private Usuario usuario;
    private Categoria categoria;
    private Gateway gateway;
    Collection<CaracteristicaForm> caracteristicas=new HashSet<>();

    @BeforeEach
    public void criaCenario(){
        usuario = mock(Usuario.class);
        when(usuario.getLogin()).thenReturn("Fulano");
        email = mock(Email.class);
        categoria = new Categoria("EletroDomestico");
        CaracteristicaForm a = new CaracteristicaForm("cor", "branca");
        CaracteristicaForm b = new CaracteristicaForm("tamanho", "grande");
        caracteristicas.add(a);
        caracteristicas.add(b);
        produto = new Produto("Geladeira", 100, "Gelo seco", new BigDecimal(2500), categoria,
                usuario, caracteristicas);
    }

    @Test
    void liberaCompra() {
        boolean estoqueOk = produto.abateEstoque(10);
        assertTrue(estoqueOk);
        assertEquals(90, produto.getQuantidade());
    }

    @Test
    void bloqueiaCompra(){
        boolean estoqueOk = produto.abateEstoque(101);
        assertTrue(!estoqueOk);
        assertEquals(100, produto.getQuantidade());
    }

    @Test
    void contaCaracteristicas(){
        assertTrue( caracteristicas.size()<3);

        CaracteristicaForm c = new CaracteristicaForm("portas", "duas");
        caracteristicas.add(c);

        assertTrue(3==caracteristicas.size());
    }
    @Test
    void testaEmail1(){
        gateway = Gateway.PAYPAL;
        boolean estoqueOk = produto.abateEstoque(10);
        if(estoqueOk && gateway != null){
            email.sendCompra(compra);
        }
        verify(email).sendCompra(compra);
    }
    @Test
    void testaEmail2() {
        gateway = Gateway.PAGSEGURO;
        boolean estoqueOk = produto.abateEstoque(101);
        if(estoqueOk && gateway != null){
            email.sendCompra(compra);
        }
        verify(email,never()).sendCompra(compra);
    }
    @Test
    void testaEmail3() {
        gateway = null;
        boolean estoqueOk = produto.abateEstoque(10);
        if(estoqueOk && gateway != null){
            email.sendCompra(compra);
        }
        verify(email,never()).sendCompra(compra);
    }
}