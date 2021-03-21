package com.nc.labs.multithreading.bank;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Operator extends Thread {

    private Deque<Customer> queue;
    private CashRegister cashRegister;
    private final static Logger LOGGER = Logger.getLogger(Operator.class.getName());
    private int i;
    Operator(CashRegister cashRegister, int i) {
        this.cashRegister = cashRegister;
        this.i = i;
    }

    public Deque<Customer> getQueue() {
        return queue;
    }
    @Override
    public void run() {
        queue = new ArrayDeque<>();
        for(;;) {
            if (queue.size() == 0) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Customer customer = queue.pop();
            if (customer.getTypeOfAction() == TypeOfAction.DEPOSIT) {
                synchronized (cashRegister) {
                    cashRegister.setCash(cashRegister.getCash() + customer.getCash());
                    LOGGER.log(Level.INFO, "Customer " + customer.getNumber() + " put " + customer.getCash() + " in the account           " + i);
                    LOGGER.log(Level.INFO, "Total Cash Register: " + cashRegister.getCash());
                }
            } else {
                synchronized (cashRegister) {
                    if (cashRegister.getCash() >= customer.getCash()) {
                        cashRegister.setCash(cashRegister.getCash() - customer.getCash());
                        LOGGER.log(Level.INFO, "Customer " + customer.getNumber() + " withdrew " + customer.getCash() + " in the account           " + i);
                        LOGGER.log(Level.INFO, "Total Cash Register: " + cashRegister.getCash());
                    }
                }
            }
            try {
                Thread.sleep(customer.getServiceTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
