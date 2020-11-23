package com.nc.labs.validation;

import java.util.ArrayList;
import java.util.List;


public class ValidatorImpl<T> implements IValidator<T> {

    private List<Condition<T>> conditions;

    public ValidatorImpl(List<Condition<T>> conditions) {
        this.conditions = conditions;
    }

    @Override
    public List<ValidationResult> validate(T param) {
        List<ValidationResult> validationResults = new ArrayList<>();
        for (Condition<T> condition : conditions) {
            validationResults.add(condition.getPredicate().test(param) ? ValidationResult.correct(condition.getMessageInfoSuccess()) : ValidationResult.uncorrected(condition.getMessageInfoError()));
        }
        return validationResults;
    }

}

