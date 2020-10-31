package com.nc.labs.repositories;


import java.util.UUID;

public interface Repository<T> {
 void add(T obj);
 boolean removeItemById(UUID id);
 T getItemById(UUID id);
 Integer length();
 boolean remove();
 boolean isEmpty();
}
