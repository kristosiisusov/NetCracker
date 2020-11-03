package com.nc.labs.search;


import java.util.function.Predicate;

public class Searcher<T> {
   public T search(T[] array, Predicate<T> predicate, int n){
        for (int i = 0; i < array.length; i++) {
            if(predicate.test(array[i])){
                return array[i];
            }
        }
        return null;
    }
}
