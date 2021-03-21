package com.nc.labs.multithreading.bank;


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerFactory implements Runnable {

    private final static Logger LOGGER = Logger.getLogger(CustomerFactory.class.getName());
    private CashRegister cashRegister;
    private Operator[] operators;

    public CustomerFactory(CashRegister cashRegister, Operator[] operators) {
        this.cashRegister = cashRegister;
        this.operators = operators;
    }

    @Override
    public void run() {
        Random random = new Random();
        int number = 0;
        for (; ; ) {
            number++;
            TypeOfAction typeOfAction;
            if (random.nextInt(2) == 0) {
                typeOfAction = TypeOfAction.WITHDRAW;
            } else {
                typeOfAction = TypeOfAction.DEPOSIT;
            }
            Operator operatorWithMinQueue = minQueue();
            Integer cash = random.nextInt(10001) + 1;
            operatorWithMinQueue.getQueue().addLast(new Customer(typeOfAction, cash, random.nextInt(2000) + 500 , number));
            LOGGER.log(Level.INFO, "New customer " +  number + " operation = " + typeOfAction.name() + " money = " + cash);
            synchronized (operatorWithMinQueue){
                operatorWithMinQueue.notify();
            }
            try {
                Thread.sleep(random.nextInt(400) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private Operator minQueue() {
        Operator operatorWithMinQueue = operators[0];
        int min = operators[0].getQueue().size();
        for (Operator operator : operators) {
            if (operator.getQueue().size() < min) {
                min = operator.getQueue().size();
                operatorWithMinQueue = operator;
            }
        }
        return operatorWithMinQueue;
    }
}
