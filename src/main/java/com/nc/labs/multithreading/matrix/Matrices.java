package com.nc.labs.multithreading.matrix;

public class Matrices {
    private static Integer[][] a;
    private static Integer[][] b;
    private static Integer[][] c;
    private static int n;


    /**
     *
     * @param a first matrix
     * @param b second matrix
     * @param n count of threads
     * @return multiplication
     */
    public static Integer[][] multiply(Integer[][] a, Integer[][] b, int n) throws InterruptedException {
        if(isValidMatrices(a, b)) {
            Matrices.a = a;
            Matrices.b = b;
            Matrices.n = n;
            c = new Integer[a.length][b[0].length];
            MyThread[] threads = new MyThread[n];
            for (int i = 0; i < n; i++) {
                threads[i] = new MyThread(i);
                threads[i].start();
            }
            for (int i = 0; i < n; i++) {
                threads[i].join();
            }
            return c;
        } else throw new IllegalArgumentException("matrices are not valid");
    }

    private static boolean isValidMatrices(Integer[][] a, Integer[][] b) {
        return a.length == b[0].length;

    }

    private static Integer multiplyForCell(int row, int column){
        int amount = 0;
        for (int i = 0; i < a[0].length; i++) {
            amount = amount + (a[row][i] * b[i][column]);
        }
        return amount;
    }

    private static class MyThread extends Thread {
        private int order;

        private MyThread(int order) {
            this.order = order;
        }

        @Override
        public void run() {
            for (int i = order; i < c.length*c[0].length; i += n) {
                int row = i/c.length;
                int column = i - (c[0].length * row);
                c[row][column] = multiplyForCell(row, column);
            }
        }
    }

}
