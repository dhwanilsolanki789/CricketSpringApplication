package com.tekion.cricket23.cricketSpring.dtos.resdtos;

public class SuccessRes {
    private String successMessage;

    public SuccessRes(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
