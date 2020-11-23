package com.nc.labs.validation;




import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class ValidationAgreementMessagesInfo<T> {
    private List<Condition<T>> conditions = new ArrayList<>();

    public void lessThan(List<Condition<T>> conditions) {
        for (Condition<T> condition:conditions) {
            condition.setMessageInfo(String.format("%s more than %s",condition.getParam(),condition.getCriterion().get(0).toString()));
        }
        this.conditions = ListUtils.union(conditions, this.conditions);
    }
    public void moreThan(List<Condition<T>> conditions) {
        for (Condition<T> condition:conditions) {
            condition.setMessageInfo(String.format("%s less than  %s",condition.getParam(),condition.getCriterion().get(0).toString()));
        }
        this.conditions = ListUtils.union(conditions, this.conditions);
    }
    public void between(List<Condition<T>> conditions) {
        for (Condition<T> condition:conditions) {
            condition.setMessageInfo(String.format("%s is not between %s and  %s",condition.getParam(),condition.getCriterion().get(0),
                    condition.getCriterion().get(condition.getCriterion().size())));
        }
        this.conditions = ListUtils.union(conditions, this.conditions);
    }
    public void equal(List<Condition<T>> conditions) {
        for (Condition<T> condition:conditions) {
            condition.setMessageInfo(String.format("%s is not equal %s",condition.getParam(),condition.getCriterion()));
        }
       this.conditions = ListUtils.union(conditions, this.conditions);
    }
    public IValidator<T> createValidator (){
        return new ValidatorImpl<>(conditions);
    }
}
