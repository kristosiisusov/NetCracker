package com.nc.labs.repositories;


import com.nc.labs.exceptions.EmptyRepositoryException;
import com.nc.labs.exceptions.NotFoundElement;

import java.util.UUID;

public interface Repository<T> {
 void add(T obj);
 void removeItemById(UUID id) throws EmptyRepositoryException, NotFoundElement;
 T getItemById(UUID id) throws EmptyRepositoryException, NotFoundElement;
 Integer length();
 void remove() throws EmptyRepositoryException;
}
