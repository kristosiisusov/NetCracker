package com.nc.labs.repositories;


import com.nc.labs.agreements.Agreement;
import com.nc.labs.search.Searcher;
import com.nc.labs.sorts.ISort;


import java.util.Comparator;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * expanding repository which hold different kinds of agreement
 */
public class RepositoryList<T extends Agreement> implements IRepository<T> {
    private static final int capacity = 10;
    private int occupancy = 0;
    private T[] array;
    @SuppressWarnings("unchecked")
    public RepositoryList() {
        array = (T[]) new Agreement[capacity];
    }
    @SuppressWarnings("unchecked")
    public RepositoryList(int capacity) {
        array = (T[]) new Agreement[capacity];
    }

    /**
     * adding new item to the end of repository
     *
     * @param obj new agreement which would be add to repository
     */
    public void add(T obj) {
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
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Agreement[(int)(array.length * 1.5)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * removing item of repository by id
     *
     * @param id identification of each items
     */
    public boolean removeItemById(UUID id) {
        int index = getIndexById(id);
        if (index >= 0) {
            System.arraycopy(array, index + 1, array, index, occupancy - index - 1);
            array[occupancy - 1] = null;
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
    public Object getItemById(UUID id) {
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
                if(((array[index]).getId().compareTo(id)) == 0) {
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

    /**
     * it sorts array of items with comparator
     * @param comparator getting expression to compare different items
     * and calls methods sort of ISort to sort items with defined sorting like Bubble sort etc
     */
    public void sort(Comparator<T> comparator, ISort<T> typeOfSort){
        typeOfSort.sort(array, comparator, occupancy);
    }

    public T[] getArray(){
        return array;
    }

    /**
     * it find items by predicate
     * @param predicate condition
     * @return found item
     */
    public T search(Predicate<T> predicate){
        Searcher<T> searcher = new Searcher<>();
        return searcher.search(array, predicate, occupancy);
    }
}
