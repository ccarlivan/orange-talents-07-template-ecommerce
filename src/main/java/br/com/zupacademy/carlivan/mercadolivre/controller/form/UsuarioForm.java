package br.com.zupacademy.carlivan.mercadolivre.controller.form;

import br.com.zupacademy.carlivan.mercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioForm {

    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioForm(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toUsuario(){
        return new Usuario(this.login, new SenhaLimpa(senha));
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
