package com.iris.ngc.dto;

public class ValidationStatus {

    private boolean success = false;
    private String message;

    public ValidationStatus() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ValidationStatus{" + "success=" + success + ", message=" + message + '}';
    }

}
