package br.com.zupacademy.carlivan.mercadolivre.validation;

import br.com.zupacademy.carlivan.mercadolivre.controller.form.ProdutoForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        ProdutoForm form = (ProdutoForm) o;

        Set<String> nomesIguais = form.buscaCaracteristicasIguais();
        if(!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null, "Valores repetidos, corrija!");
        }
    }
}
