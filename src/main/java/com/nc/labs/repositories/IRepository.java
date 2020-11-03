package com.nc.labs.repositories;

import com.nc.labs.sorts.ISort;


import java.util.Comparator;
import java.util.UUID;
import java.util.function.Predicate;

public interface IRepository<T> {
 void add(T obj);
 boolean removeItemById(UUID id);
 Object getItemById(UUID id);
 Integer length();
 boolean remove();
 boolean isEmpty();
 void sort(Comparator<T> comparator, ISort<T> typeOfSort);
 Object[] getArray();
 T search(Predicate<T> predicate);
}
