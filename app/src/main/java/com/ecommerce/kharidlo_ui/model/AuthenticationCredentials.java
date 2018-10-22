package com.ecommerce.kharidlo_ui.model;

public class AuthenticationCredentials {
    private String emailId;
    private String password;

    public AuthenticationCredentials(String email, String password) {
        this.emailId = email;
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
