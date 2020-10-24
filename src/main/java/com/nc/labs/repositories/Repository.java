package com.nc.labs.repositories;


public interface Repository<T> {
 void add();
 void remove(long id);
 T get(long id);
}
