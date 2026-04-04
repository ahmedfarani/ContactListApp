package com.example.contactslistapp;

public class Contact {
    private String name, phoneNumber, email;
    private int imageResource;

    public Contact(String name, String phoneNumber, String email, int imageResource) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
