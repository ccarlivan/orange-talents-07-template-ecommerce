package br.com.zupacademy.carlivan.mercadolivre.controller.dto;

import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto {
    private String login;
    private LocalDateTime dataCadastro;

    public UsuarioDto(Usuario usuario) {
        this.login = usuario.getLogin();
        this.dataCadastro = usuario.getDataCadastro();
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

}
