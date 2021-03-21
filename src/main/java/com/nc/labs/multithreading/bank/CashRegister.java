package com.nc.labs.multithreading.bank;

public class CashRegister {

    private Integer cash;

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public CashRegister(Integer cash) {
        this.cash = cash;
    }
}
