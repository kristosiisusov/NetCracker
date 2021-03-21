package com.nc.labs.multithreading.bank;


import org.junit.jupiter.api.Test;


class BankTest {
    @Test
    void simulateTest() throws InterruptedException {
        Bank bank = new Bank();
        bank.simulate(5);
    }
}