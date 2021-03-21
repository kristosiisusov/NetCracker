package com.nc.labs.multithreading.bank;

public class Customer {

    private TypeOfAction typeOfAction;
    private Integer cash;
    private Integer serviceTime;
    private Integer number;

    public Integer getNumber() {
        return number;
    }


    public Customer(TypeOfAction typeOfAction, Integer cash, Integer serviceTime, Integer number) {
        this.typeOfAction = typeOfAction;
        this.cash = cash;
        this.serviceTime = serviceTime;
        this.number = number;
    }

    public TypeOfAction getTypeOfAction() {
        return typeOfAction;
    }

    public Integer getCash() {
        return cash;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

}
