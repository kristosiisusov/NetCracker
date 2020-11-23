package com.nc.labs.validation;

public class ValidationResult {
    private String messageInfo;
    private boolean result;

    public ValidationResult(boolean result, String messageInfo) {
        this.result = result;
        this.messageInfo = messageInfo;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public boolean isResult() {
        return result;
    }

    public static ValidationResult correct(){
        return new ValidationResult(true, "Validation is success");
    }

    public static ValidationResult uncorrected(String message){
        return new ValidationResult(false, message);
    }

}