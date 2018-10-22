package com.ecommerce.kharidlo_ui.model;

public class User {
    private final String fullName;
    private final String emailId;
    private final String password;
    private final String phoneNumber;

    public User(String fullName, String emailId, String password, String phoneNumber) {

        this.fullName = fullName;
        this.emailId = emailId;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
