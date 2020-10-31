package com.nc.labs.repositories;


import com.nc.labs.agreements.Agreement;


import java.util.Optional;
import java.util.UUID;

/**
 * expanding repository which hold different kinds of agreement
 */
public class RepositoryList implements Repository<Agreement> {
    private int capacity = 10;
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
     *
     * @param obj new agreement which would be add to repository
     */
    public void add(Agreement obj) {
        if (occupancy >= capacity) {
            grow();
        }
        array[occupancy] = obj;
        occupancy++;
    }

    /**
     * expend repository if there isn't place and copy items to new repository
     */
    private void grow() {
        int tempCapacity = (int) (capacity * 1.5);
        Agreement[] newArray = new Agreement[tempCapacity];
        System.arraycopy(array, 0, newArray, 0, capacity);
        array = newArray;
        capacity = tempCapacity;
    }

    /**
     * removing item of repository by id
     *
     * @param id identification of each items
     */
    public boolean removeItemById(UUID id) {
        int index = getIndexById(id);
        if (index >= 0) {
            if ((occupancy - 1) == index) {
                array[index] = null;
            } else {
                System.arraycopy(array, index + 1, array, index, occupancy - 1);
            }
            occupancy--;
            return true;
        }
        return false;
    }

    /**
     * getting item of repository by id
     * also now all items have own id so can't be items with same id!
     *
     * @param id identification of each items
     * @return agreement by id
     */
    public Agreement getItemById(UUID id) {
        int i = getIndexById(id);
        if (i >= 0) {
            return array[i];
        }
        return null;
    }

    /**
     * utility method which return index of items by their id
     *
     * @param id identification of each items
     * @return index of item to repository
     */
    private Integer getIndexById(UUID id) {
        int index = 0;
        if (occupancy != 0) {
            while (index < occupancy) {
                if((array[index].getId().compareTo(id)) == 0) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * calculating length of repository
     *
     * @return count of fully items
     */
    public Integer length() {
        return occupancy;
    }

    /**
     * removing items of repository from the end
     */
    @Override
    public boolean remove() {
        if (occupancy != 0) {
            array[--occupancy] = null;
            return true;
        }
        return false;
    }

    /**
     * @return indicates whether or not there are items in the repository
     */
    public boolean isEmpty() {
        return occupancy == 0;
    }
}
