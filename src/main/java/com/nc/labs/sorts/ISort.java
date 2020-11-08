package com.nc.labs.sorts;


import java.util.Comparator;

public interface ISort<T> {
    void  sort(T[] array, Comparator<T> comparator, int length);

}
