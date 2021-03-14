package com.nc.labs.introduction.search;


import java.util.Arrays;
import java.util.function.Predicate;

public class Searcher<T> {
   public T[] search(T[] array, Predicate<T> predicate){
       int countFoundItems = 0;
        for (int i = 0; i < array.length; i++) {
            if(predicate.test(array[i])){
                array[countFoundItems] = array[i];
                countFoundItems++;
            }
        }
       if (countFoundItems != array.length) {
           return Arrays.copyOf(array, countFoundItems);
       }
       return array;
   }
}
