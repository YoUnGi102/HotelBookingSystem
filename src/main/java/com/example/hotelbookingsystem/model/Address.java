package com.example.hotelbookingsystem.model;

public class Address {

    private int addressId;
    private String city;
    private String street;
    private String houseNumber;
    private String postalCode;

    public Address(String city, String street, String houseNumber, String postalCode) {
        this(-1, city, street, houseNumber, postalCode);
    }

    public Address(int addressId, String city, String street, String houseNumber, String postalCode) {
        this.addressId = addressId;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public void setAddressId(int addressId) {
        if(this.addressId == -1)
            this.addressId = addressId;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getAddressId() {
        return addressId;
    }
    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public String getHouseNumber() {
        return houseNumber;
    }
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return street + " " + houseNumber + ", " + city + " " + postalCode;
    }
}
