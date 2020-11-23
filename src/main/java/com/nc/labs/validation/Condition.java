package com.nc.labs.validation;

import java.util.List;
import java.util.function.Predicate;

public class Condition<T> {
    private List<String> criterion;
    private Predicate<T> predicate;
    private String messageInfoSuccess;
    private String messageInfoError;
    private String param;

    public Condition(List<String> criterion, Predicate<T> predicate, String param) {
        this.criterion = criterion;
        this.predicate = predicate;
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public List<String> getCriterion() {
        return criterion;
    }

    public void setCriterion(List<String> criterion) {
        this.criterion = criterion;
    }

    public Predicate<T> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public String getMessageInfoSuccess() {
        return messageInfoSuccess;
    }

    public String getMessageInfoError() {
        return messageInfoError;
    }

    public void setMessageInfoSuccess(String messageInfoSuccess) {
        this.messageInfoSuccess = messageInfoSuccess;
    }

    public void setMessageInfoError(String messageInfoError) {
        this.messageInfoError = messageInfoError;
    }
}
