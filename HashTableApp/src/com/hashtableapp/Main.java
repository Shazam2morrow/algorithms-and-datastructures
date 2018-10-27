/*
    *   HashTable is the data structure where operations
    * of searching and inserting are executed very fast,
    * in O-notation the complexity of the HashTable is
    * about O(1).
    *   Physically the HashTable is associated array
    * of data. The position of every element is calculated
    * by HashFunction, which main purpose is to reduce the
    * index diapason. It helps to reduce the amount of
    * computer memory. By the way, because of this, two
    * or more elements could have the same array index.
    * It is called a collision.
    *   To resolve the problem of collisions there are two
    * different strategies:
    *   1) Method of direct addresses (for example, double-hashing)
    *   2) Method of Lists
    * This program demonstrates using of the second method.
    *
    * @author Shazam2morrow
    * @date October 22, 2018
    *
    * P.S.
    *   You are free to use this application for educational purposes.
    *   Best of luck!
 */

package com.hashtableapp;

public class Main
{
    public static void main(String[] args)
    {
        /*Example of using the HashTable*/

        Link aDataItem;

        int aKey;
        int size = 100;
        int startWith = 60;
        int keysPerCell = 20;

        // Create HashTable with 1000 elements
        HashTable theHashTable = new HashTable(size);

        // Insert some data
        for (int j = 0; j < startWith; j++)
        {
            aKey = (int) (java.lang.Math.random()
                                        * keysPerCell * size);
            aDataItem = new Link(aKey);
            theHashTable.insert(aDataItem);
        }

        theHashTable.displayTable();

        theHashTable.insert(new Link(5601));
        theHashTable.insert(new Link(7805));
        theHashTable.insert(new Link(3600));
        theHashTable.insert(new Link(5610));

        theHashTable.displayTable();

        Link temp1 = theHashTable.find(3600);
        Link temp2 = theHashTable.find(5610);
        System.out.print("temp1 is: " + temp1.getKey() + ", " + "temp2 is " + temp2.getKey());
    }
}

    /*
        * This class contains some data to be
        * stored in the SortedList. It can be
        * any kind of data. Objects, primitives,
        * etc. are allowed.
     */

class Link
{
    private int iData;
    public Link next;

    public Link(int iData)
    {
        this.iData = iData;
    }

    public int getKey()
    {
        return this.iData;
    }

    public void displayData()
    {
        System.out.println(iData + " ");
    }
}

    /*
        * Each index of the array is the structure
        * of the SortedList. There are different strategies
        * against collisions. One of them is the SortedList
        * data structure (a.k.a Method of lists).
     */

class SortedList
{
    private Link first;

    public SortedList()
    {
        first = null;
    }

    public boolean isListEmpty()
    {
        return first == null ? true : false;
    }

    /*
         * Inserts given link into the SortedList.
         * This operation will be done anyway,
         * even if the SortedList is empty.
     */

    public void insert(Link theLink)
    {
        int key = theLink.getKey();

        Link previous = null;
        Link current = first;

        while (current != null && key > current.getKey())
        {
            previous = current;
            current = current.next;
        }

        if (previous == null)
        {
            first = theLink;
        }
        else
        {
            previous.next = theLink;
        }

        theLink.next = current;
    }

    /*
         * Finds an object with transmitted key.
         * Returns an object with custom key if
         * it exists, or null if it is not there.
         * (Because our List is sorted, the average
         * time of searching is O(N / 2), where
         * N is the number of elements in the List).
     */

    public Link find(int key)
    {
        if (!isListEmpty())
        {
            Link current = first;

            while(current != null && current.getKey() <= key)
            {
                if (current.getKey() == key)
                {
                    return current;
                }
                current = current.next;
            }

            return null;
        }
        else
        {
            return null;
        }
    }

    /*    #This operation is not popular!#

        * Removes Link from the SortedList.
        * Takes key to search element in the SortedList.
        * Returns deleted element if success,
        * null if there is no such element OR
        * if SortedList is empty.
     */

    public Link delete(int key)
    {
        if (!isListEmpty())
        {
            Link previous = null;
            Link current = first;

            while (current != null && current.getKey() != key)
            {
                previous = current;
                current = current.next;
            }

            if (previous == null)
            {
                first = first.next;
            }
            else
            {
                previous.next = current.next;
            }

            return current;
        }
        else
        {
            return null;
        }
    }

    public void displayList()
    {
        System.out.print("List (first-->last: ");
        Link current = first;
        while (current != null)
        {
            current.displayData();
            current = current.next;
        }
        System.out.println("");
    }
}

class HashTable
{
    private SortedList[] hashArray;
    private int size;

    public HashTable(int size)
    {
        this.size = size;
        hashArray = new SortedList[size];
        for (int i = 0; i < hashArray.length; i++)
        {
            hashArray[i] = new SortedList();
        }
    }

    /*
        * Gets hash value of the link and
        * inserts object into the SortedList
     */

    public void insert(Link theLink)
    {
        int key = theLink.getKey();
        int hash = hashFunc(key);
        hashArray[hash].insert(theLink);
    }

    public void delete(int key)
    {
        int hash = hashFunc(key);

        if (hashArray[hash].delete(key) != null)
        {
            System.out.print(key + " object has been deleted!");
        }
        else
        {
            System.out.print(key + " has not been found!");
        }
    }

    public Link find(int key)
    {
        int hash = hashFunc(key);

        return (hashArray[hash].find(key));
    }

    public void displayTable()
    {
        for (int k = 0; k < size; k++)
        {
            System.out.print(k + ": ");
            hashArray[k].displayList();
        }
    }

    /*
        * Simple hash function reduces the range of
        * the array indexes. There are options to create
        * hashFunc for String objects too.
     */

    public int hashFunc(int key)
    {
        return key % size;
    }
}