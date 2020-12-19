package com.nc.labs.sorts;

import java.util.Comparator;

public class SelectionSort<T> implements ISort<T> {

    public void sort(T[] array, Comparator<T> comparator, int length) {
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            T min = array[i];
            for (int j = i + 1; j < length; j++) {
                if (comparator.compare(array[j], min) < 0) {
                    minIndex = j;
                    min = array[j];
                }
            }
            array[minIndex] = array[i];
            array[i] = min;
        }
    }
}
