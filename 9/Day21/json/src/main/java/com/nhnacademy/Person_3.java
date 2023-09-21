package com.nhnacademy;

public class Person_3 {
    String name;
    // Address_3 address_3 = new Address_3(name);
    Address_3 address;

    public Person_3(String name, Address_3 address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address_3 getAddress() {
        return address;
    }

    /* public Address_3 getAddress2() {
        return address();
    } */
    // -> ? 이거 어케 함?
}
