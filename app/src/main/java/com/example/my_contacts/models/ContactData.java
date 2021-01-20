package com.example.my_contacts.models;

public class ContactData {
    private  int imageUrl;
    private String name;
    private String number;

    public ContactData(int imageUrl,String name, String number) {
        this.imageUrl=imageUrl;
        this.name = name;
        this.number = number;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
