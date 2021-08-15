package br.com.zupacademy.carlivan.mercadolivre.model;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.SenhaLimpa;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    private LocalDateTime dataCadastro;

    public Usuario(@NotBlank @Email String login,
                   @NotBlank @Length(min = 6) SenhaLimpa senha) {
        this.login = login;
        this.senha = senha.hash();
        this.dataCadastro = LocalDateTime.now();
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
