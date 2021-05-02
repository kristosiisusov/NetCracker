package com.nc.labs.introduction.repositories;


import com.nc.labs.introduction.agreements.Agreement;
import com.nc.labs.introduction.search.Searcher;
import com.nc.labs.introduction.sorts.BubbleSort;
import com.nc.labs.introduction.sorts.ISort;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * expanding repository which hold different kinds of agreement
 */
@XmlRootElement(name = "agreements")
public class RepositoryList<T extends Agreement> implements IRepository<T> {
    private static final int capacity = 10;
    private int occupancy = 0;
    @XmlElement(name = "agreement")
    private T[] array;
    private ISort<T> typeOfSort;

    @SuppressWarnings("unchecked")
    public RepositoryList() {
        this.array = (T[]) new Agreement[capacity];
        typeOfSort = new BubbleSort<>();
    }

    public T[] getArray() {
        return array;
    }

    @SuppressWarnings("unchecked")
    public RepositoryList(int capacity) {
        this.array = (T[]) new Agreement[capacity];
        typeOfSort = new BubbleSort<>();
    }

    @SuppressWarnings("unchecked")
    public RepositoryList(T[] array) {
        this.array = (T[]) new Agreement[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
            if(array[i] != null){
                occupancy++;
            }
        }
        typeOfSort = new BubbleSort<>();
    }

    @XmlTransient
    public ISort<T> getTypeOfSort() {
        return typeOfSort;
    }

    public void setTypeOfSort(ISort<T> typeOfSort) {
        this.typeOfSort = typeOfSort;
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
        array[occupancy] =  obj;
        occupancy++;
    }


    /**
     * expend repository if there isn't place and copy items to new repository
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        Agreement[] newArray =  new Agreement[(int)(array.length * 1.5)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = (T[]) newArray;
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
    public T getItemById(UUID id) {
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
    public void sort(Comparator<T> comparator){
        typeOfSort.sort(array, comparator, occupancy);
    }

    /**
     * it find items by predicate
     * @param predicate condition
     * @return repository of found items
     */
    public IRepository<T> search(Predicate<T> predicate){
        Searcher<T> searcher = new Searcher<>();
        return new RepositoryList<>(searcher.search(Arrays.copyOf(array,occupancy),predicate));
    }
    public T[] toArray(){
        return array;
    }

    public T getItemsByIndex(int index){
        return array[index];
    }

}

