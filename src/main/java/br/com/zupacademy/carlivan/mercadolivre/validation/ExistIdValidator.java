package br.com.zupacademy.carlivan.mercadolivre.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistIdValidator implements ConstraintValidator<ExistId, Object> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    EntityManager manager;

    @Override
    public void initialize(ExistId constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();

        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Query query = manager.createQuery("select 1 from " + klass.getName()+ " where " + domainAttribute+"=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        Assert.isTrue(list.size() <= 1, "Verifique inconsistencias!!");

        return !list.isEmpty();
    }
}
