package com.tekion.cricket23.cricketSpring.dtos.resdtos;

public class ErrorRes {
    private String errorMessage;

    public ErrorRes(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
