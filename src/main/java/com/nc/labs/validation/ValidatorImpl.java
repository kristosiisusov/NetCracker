package com.nc.labs.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * implementation of validation with messages for logging validation
 * @param <T>
 */
public class ValidatorImpl<T> implements IValidator<T> {

    private List<Condition<T>> conditions;

    public ValidatorImpl(List<Condition<T>> conditions) {
        this.conditions = conditions;
    }

    /**
     *
     * @param param value to check
     * @return list of results obtained by applying the condition
     */

    @Override
    public List<ValidationResult> validate(T param) {
        List<ValidationResult> validationResults = new ArrayList<>();
        for (Condition<T> condition : conditions) {
            validationResults.add(condition.getPredicate().test(param) ? ValidationResult.correct(condition.getMessageInfoSuccess()) : ValidationResult.uncorrected(condition.getMessageInfoError()));
        }
        return validationResults;
    }

}

