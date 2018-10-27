/*
                                                                    # The Heap data structure
    *   Heap is the data structure that is used
    * to implement functions of PriorityQueue.
    * It is one kind of ADT (a.k.a Abstract Data Type).
    * Physically Heap is stored in the array, but it
    * also possible to do it using Tree for example.
    *   Every node in the Heap is bigger than his child
    * elements. This is the main rule. Because of the nature
    * of this implementation, the Heap data structure can be
    * used as a sorting instrument. See also Heap sorting!
    *
    * @author Shazam2morrow
    * @Date October 23, 2018
    *
    *   P.S.
    *       You are free to use this app for educational purposes.
    *       Best of luck!
 */

package com.heapapp;

public class Main
{
    public static void main(String[] args)
    {
        /* Small example */
        Heap theHeap = new Heap(15);

        System.out.println("Random array:");
        for (int j = 0; j < 10; j++)
        {
            theHeap.insert( (int)(java.lang.Math.random() * 100) );
        }
        theHeap.displayHeap();

        System.out.println("Sorted array:");
        for (int k = 0; k < 10; k++)
        {
            System.out.println(k + ": " + theHeap.remove().getKey());
        }

        System.out.println("Inserted elements:");
        theHeap.insert(150);
        theHeap.insert(230);
        theHeap.insert(355);
        theHeap.insert(12);
        theHeap.insert(88);

        theHeap.displayHeap();

        theHeap.change(0, 450);
        theHeap.change(4, 186);

        System.out.println("Changed keys:");
        theHeap.displayHeap();
    }
}

    /*
        * Node class represents the data
        * to be stored in the Heap. It can
        * be any type that you want to implement.
        * Objects are sorted by key.
     */

class Node
{
    private int iData;

    public Node(int key)
    {
        iData = key;
    }

    public int getKey()
    {
        return iData;
    }

    public void setKey(int key)
    {
        iData = key;
    }
}

class Heap
{
    private Node[] heapArray;
    private int maxSize;
    private int curSize;

    public Heap(int size)
    {
        maxSize = size;
        curSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean isEmpty()
    {
        return (curSize == 0);
    }

    public boolean insert(int key)
    {
        if (curSize == maxSize)
        {
            return false;
        }

        Node newNode = new Node(key);
        heapArray[curSize] = newNode;
        trickleUp(curSize++);

        return true;
    }

    /*
        * Gets index of the inserted element
        * and promotes it to the top of the Heap
        * according to the rules.
        *   This formulas help to calculate
        * different nodes:
            # parent     = (index - 1) / 2;
            # leftChild  = (2 * index) + 1;
            # rightChild = (2 * index) + 2 == leftChild + 1;
     */

    public void trickleUp(int index)
    {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];

        while (index > 0 &&
                            heapArray[parent].getKey() < bottom.getKey())
        {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    /*
        * Takes the top element of the Heap.
        * Because of the data structure the root
        * node will always be the biggest.
     */

    public Node remove()
    {
        if (!isEmpty())
        {
            Node root = heapArray[0];
            heapArray[0] = heapArray[--curSize];
            trickleDown(0);

            return root;
        }
        return null;
    }

    /*
        * When top node is removed
        * the bottom element becomes the
        * root node. According to the rules
        * if this node is less than one of
        * his child, the biggest child element
        * replaces him.
        * Method takes index of removed node.
        *   The bottom node is calculated as
        *   [curSize - 1]
     */

    public void trickleDown(int index)
    {
        Node top = heapArray[0];
        int largerChild;

        while (index < curSize / 2)
        {
            int leftChild = (2 * index) + 1;
            int rightChild = leftChild + 1;

            if (rightChild < curSize &&
                                        heapArray[leftChild].getKey() <
                                        heapArray[rightChild].getKey())
            {
                largerChild = rightChild;
            }
            else
            {
                largerChild = leftChild;
            }

            if (top.getKey() >= heapArray[largerChild].getKey())
            {
                break;
            }

            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }

    /*
        * Changes key of the given node
        * using index as a pointer. It is
        * useful when you need to change
        * the priority of custom node.
     */

    public boolean change(int index, int newKey)
    {
        if (index < 0 || index >= maxSize)
        {
            return false;
        }

        int oldKey = heapArray[index].getKey();
        heapArray[index].setKey(newKey);

        if (oldKey < newKey)
        {
            trickleUp(index);
        }
        else
        {
            trickleDown(index);
        }

        return true;
    }

    public void displayHeap()
    {
        for (int i = 0; i < curSize; i++)
        {
            System.out.println(i + ": " + heapArray[i].getKey());
        }
    }
}
