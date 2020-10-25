package com.nc.labs.repositories;


import com.nc.labs.agreements.Agreement;
import com.nc.labs.exceptions.EmptyRepositoryException;
import com.nc.labs.exceptions.NotFoundElement;

public class repositoryList implements Repository<Agreement> {
    private int capacity = 10;
    private int occupancy = 0;
    private Agreement[] array;

    public repositoryList() {

    }

    public repositoryList(int capacity) {
        array = (Agreement[]) new Object[capacity];
        this.capacity = capacity;
    }


    public void add(Agreement obj) {
        if (occupancy >= capacity) {
            int tempCapacity = capacity * 2;
            Agreement[] newArray = new Agreement[tempCapacity];
            for (int i = 0; i < capacity; i++) {
                newArray[i] = array[i];
            }
            newArray[capacity] = obj;
            array = newArray;
            capacity = tempCapacity;
        } else {
            array[occupancy] = obj;
        }
        occupancy++;
    }

    public void removeItemById(long id) throws EmptyRepositoryException, NotFoundElement {
        for (int i = getIndexById(id); i < occupancy - 1; i++) {
            array[i] = array[i + 1];
        }

    }

    public Agreement getItemById(long id) throws EmptyRepositoryException, NotFoundElement {
        return array[getIndexById(id)];
    }

    private Integer getIndexById(long id) throws EmptyRepositoryException, NotFoundElement {
        int index;
        if (occupancy != 0) {
            index = 0;
            while (array[index].getId() != id) {
                index++;
            }
            if (array[index].getId() == id) {
                return index;
            } else {
                throw new NotFoundElement("Item not found in the repository by ID");
            }
        } else {
            throw new EmptyRepositoryException("Repository is empty");
        }
    }
}
