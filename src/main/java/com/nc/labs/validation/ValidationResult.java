package com.nc.labs.validation;

/**
 * stores the status of the check for the condition: passed or failed, and a message depending on the result
 */
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
    public static ValidationResult correct(String message){
        return new ValidationResult(true, message);
    }

    public static ValidationResult uncorrected(String message){
        return new ValidationResult(false, message);
    }

}
