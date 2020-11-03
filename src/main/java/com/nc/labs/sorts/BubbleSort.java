package com.nc.labs.sorts;

import java.util.Comparator;

public class BubbleSort<T> implements ISort<T>{
    @Override
    public void sort(T[] array, Comparator<T> c, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if ((c.compare(array[j], array[j + 1]) > 0)){
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
