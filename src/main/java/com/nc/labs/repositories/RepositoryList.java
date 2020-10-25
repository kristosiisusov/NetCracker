package com.nc.labs.repositories;


import com.nc.labs.agreements.Agreement;
import com.nc.labs.exceptions.EmptyRepositoryException;
import com.nc.labs.exceptions.NotFoundElement;


import java.util.UUID;

/**
 * expanding repository which hold different kinds of agreement
 */
public class RepositoryList implements Repository<Agreement> {
    private int capacity = 2;
    private int occupancy = 0;
    private Agreement[] array;

    public RepositoryList() {
        array = new Agreement[capacity];
    }

    public RepositoryList(int capacity) {
        array = new Agreement[capacity];
        this.capacity = capacity;
    }

    /**
     * adding new item to the end of repository
     * @param obj new agreement which would be add to repository
     */
    public void add(Agreement obj) {
        if (occupancy >= capacity) {
            int tempCapacity = capacity * 2;
            Agreement[] newArray = new Agreement[tempCapacity];
            if (capacity >= 0) {
                System.arraycopy(array, 0, newArray, 0, capacity);
            }
            newArray[capacity] = obj;
            array = newArray;
            capacity = tempCapacity;
        } else {
            array[occupancy] = obj;
        }
        occupancy++;
    }

    /**
     * removing item of repository by id
     * @param id identification of each items
     */
    public void removeItemById(UUID id) throws EmptyRepositoryException, NotFoundElement {
        int index = getIndexById(id);
        if ((occupancy - 1) == index) {
            array[index] = null;
            occupancy--;
        } else {
            for (int i = index; i < occupancy - 1; i++) {
                array[i] = array[i + 1];
                occupancy--;
            }
        }
    }

    /**
     * getting item of repository by id
     * @param id identification of each items
     * @return agreement by id
     */
    public Agreement getItemById(UUID id) throws EmptyRepositoryException, NotFoundElement {
        return array[getIndexById(id)];
    }

    /**
     * utility method which return index of items by their id
     * @param id identification of each items
     * @return index of item to repository
     */
    private Integer getIndexById(UUID id) throws EmptyRepositoryException, NotFoundElement {
        int index;
        if (occupancy != 0) {
            index = 0;
            while ((index < occupancy) && (array[index].getId().compareTo(id)) != 0) {
                index++;
            }
            if ((index < occupancy) && (array[index].getId().compareTo(id)) == 0) {
                return index;
            } else {
                throw new NotFoundElement("Item not found in the repository by ID");
            }
        } else {
            throw new EmptyRepositoryException("Repository is empty");
        }
    }

    /**
     * calculating length of repository
     * @return count of fully items
     */
    public Integer length() {
        return occupancy;
    }

    /**
     * removing items of repository from the end
     */
    @Override
    public void remove() throws EmptyRepositoryException {
        if (occupancy != 0) {
            array[occupancy - 1] = null;
            occupancy--;
        } else {
            throw new EmptyRepositoryException("Repository is empty");
        }
    }

}
