package com.nc.labs.multithreading.bank;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {

    private final static Logger LOGGER = Logger.getLogger(Bank.class.getName());

    public void simulate(int n) throws InterruptedException {
        LOGGER.log(Level.INFO,"Bank has started its work");
        CashRegister cashRegister = new CashRegister(103550);
        LOGGER.log(Level.INFO, "Total Cash Register: " + cashRegister.getCash());
        Operator[] operators = new Operator[n];
        for (int i = 0; i < n; i++) {
            operators[i] = new Operator(cashRegister, i);
            operators[i].start();
        }
        Thread customerFactory = new Thread(new CustomerFactory(cashRegister, operators));
        customerFactory.start();
        customerFactory.join();
    }
}
