package com.nc.labs.introduction.repositories;



import com.nc.labs.introduction.sorts.ISort;

import java.util.Comparator;
import java.util.UUID;
import java.util.function.Predicate;

public interface IRepository<T> {
 void add(T obj);

 boolean removeItemById(UUID id);

 T getItemById(UUID id);

 Integer length();

 boolean remove();

 boolean isEmpty();

 void sort(Comparator<T> comparator);

 IRepository<T> search(Predicate<T> predicate);

 T getItemsByIndex(int index);

 T[] toArray();

 void setTypeOfSort(ISort<T> typeOfSort);

 ISort<T> getTypeOfSort();
}
