package com.nc.labs.repositories;


import com.nc.labs.exceptions.EmptyRepositoryException;
import com.nc.labs.exceptions.NotFoundElement;

public interface Repository<T> {
 void add(T obj);
 void removeItemById(long id) throws EmptyRepositoryException, NotFoundElement;
 T getItemById(long id) throws EmptyRepositoryException, NotFoundElement;
}
