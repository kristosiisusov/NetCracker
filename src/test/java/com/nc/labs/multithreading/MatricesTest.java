package com.nc.labs.multithreading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatricesTest {
    @Test
    void multiplyTest() throws InterruptedException {
        int n = 3;
        Integer[][] matrixA = {
                {-9, 1, 0},
                {4, 1, 1}
        };

        Integer[][] matrixB = {
                {-9, 1},
                {4, 1},
                {-2, 2}
        };
        Integer[][] matrixC = {
                {85, -8},
                {-34, 7}
        };

        Integer[][] matrixRes = Matrices.multiply(matrixA, matrixB, n);
        for (int i = 0; i < matrixC.length; i++) {
            for (int j = 0; j < matrixC[0].length; j++) {
                assertEquals(matrixC[i][j], matrixRes[i][j]);
            }
        }
    }
}