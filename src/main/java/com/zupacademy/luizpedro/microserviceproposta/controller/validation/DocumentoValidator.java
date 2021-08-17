package com.zupacademy.luizpedro.microserviceproposta.controller.validation;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocumentoValidator implements ConstraintValidator <Documento,CharSequence>{

    private final CPFValidator cpfValidator = new CPFValidator();
    private final CNPJValidator cnpjValidator = new CNPJValidator();
    @Override
    public void initialize(Documento constraintAnnotation) {
        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);
    }

    @Override
    public boolean isValid(CharSequence documento, ConstraintValidatorContext constraintValidatorContext) {
        return cpfValidator.isValid(documento, constraintValidatorContext) || cnpjValidator.isValid(documento, constraintValidatorContext);    }
}
