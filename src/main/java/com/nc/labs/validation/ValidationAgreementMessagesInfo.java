package com.nc.labs.validation;




import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * sets the necessary templates for logging depending on the method
 * @param <T>
 */
public class ValidationAgreementMessagesInfo<T> {
    private List<Condition<T>> conditions = new ArrayList<>();

    public void lessThan(List<Condition<T>> conditions) {
        for (Condition<T> condition:conditions) {
            condition.setMessageInfoError(String.format("validation error: %s more than %s",condition.getParam(),condition.getCriterion().get(0)));
            condition.setMessageInfoSuccess(String.format("validation success: %s less than %s",condition.getParam(),condition.getCriterion().get(0)));
        }
        this.conditions = ListUtils.union(conditions, this.conditions);
    }
    public void moreThan(List<Condition<T>> conditions) {
        for (Condition<T> condition:conditions) {
            condition.setMessageInfoError(String.format("validation error: %s less than  %s",condition.getParam(),condition.getCriterion().get(0)));
            condition.setMessageInfoSuccess(String.format("validation success: %s more than  %s",condition.getParam(),condition.getCriterion().get(0)));
        }
        this.conditions = ListUtils.union(conditions, this.conditions);
    }
    public void between(List<Condition<T>> conditions) {
        for (Condition<T> condition:conditions) {
            condition.setMessageInfoError(String.format("validation error: %s is not between %s and  %s",condition.getParam(),condition.getCriterion().get(0),
                    condition.getCriterion().get(condition.getCriterion().size())));
            condition.setMessageInfoSuccess(String.format("validation success: %s is between %s and  %s",condition.getParam(),condition.getCriterion().get(0),
                    condition.getCriterion().get(condition.getCriterion().size())));
        }
        this.conditions = ListUtils.union(conditions, this.conditions);
    }
    public void equal(List<Condition<T>> conditions) {
        for (Condition<T> condition:conditions) {
            condition.setMessageInfoError(String.format("validation error: %s is not equal %s",condition.getParam(),condition.getCriterion()));
            condition.setMessageInfoSuccess(String.format("validation success: %s is equal %s",condition.getParam(),condition.getCriterion()));
        }
       this.conditions = ListUtils.union(conditions, this.conditions);
    }
    public IValidator<T> createValidator (){
        return new ValidatorImpl<>(conditions);
    }
}
