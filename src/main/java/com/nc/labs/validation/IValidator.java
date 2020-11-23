package com.nc.labs.validation;

import java.util.List;

@FunctionalInterface
public interface IValidator<T> {
    List<ValidationResult> validate(T param);
}
