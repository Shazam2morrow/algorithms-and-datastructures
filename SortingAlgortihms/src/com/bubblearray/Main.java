/*
    * This app demonstrates 5 different types of sorting:
    * 1) Bubble sorting;
    * 2) Select sorting;
    * 3) Insert sorting;
    * 4) Quick sorting;
    * 5) Shell sorting;
    *
    * @author Shazam2morrow
    * @date October 26, 2018
    *
    *   P.S.
    *       You are free to use this app for educational purposes.
    *       Best of luck!
 */

package com.bubblearray;

public class Main
{
    public static void main(String[] args)
    {
      /* A little example, try another as well */
      int size = 100;
	    Array Array = new Array(size);

	    for (int i = 0; i < size; i++)
	    {
	        long n = (int)(java.lang.Math.random()*99);
	        Array.insert(n);
      }
      Array.display();

	    long befTime = System.currentTimeMillis();
	    Array.quickSort();
	    Array.display();
	    long aftTime = System.currentTimeMillis();
	    System.out.println("Time is " + (aftTime - befTime) + "ms");
    }
}

/*
    * Class represents a wrapper
    * for trivial array. It can be
    * any custom object that you want.
 */

class Array
{
    private long[] a;
    private int nElems;

    public Array(int size)
    {
        a = new long[size];
        nElems = 0;
    }

    public void insert(long value)
    {
        a[nElems] = value;
        nElems++;
    }

    public void display()
    {
        for (int i = 0; i < nElems; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    /*
        * Just calls main quickSort function
     */

    public void quickSort()
    {
        recQuickSort(0, nElems - 1);
    }

    /*
        * Represents recursive quick sort function.
        * This method divides array into two smaller
        * arrays and sorts left and right sides of it
        * alternately.
        * When size of array is less then 10 better
        * to use insertionSort, but it depends of
        * many factors such as CPU, OS, programming
        * implementation, type of data and etc.
        * For better use try another values.
        * Tests are required!
     */

    public void recQuickSort(int left, int right)
    {
        int size = right - left + 1;

        if (size < 10)
        {
            insertionSort(left, right);
        }
        else
        {
            long median = medianOf3(left, right);
            int partition = partitionIt(left, right, median);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    /*
        * Median helps to find better pivot value.
        * If calculating data are sorted in some
        * way (from less to the highest for example),
        * quick sorting gets O(N) complexity, and it
        * is not better than common sorting algorithm.
        * Median prevents this behavior. There is no
        * impact if incoming data are random!
        * Returns median.
     */

    public long medianOf3(int left, int right)
    {
        int center = (left + right) / 2;

        if (a[left] > a[center])
        {
            swap(left, center);
        }

        if (a[left] > a[right])
        {
            swap(left, right);
        }

        if (a[center] > a[right])
        {
            swap(center, right);
        }
        /* places median to the end  */
        swap(center, right - 1);

        return a[right - 1];
    }

    /*
        * Swaps two elements of an array
     */

    public void swap(int left, int right)
    {
        long temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    /*
        * Places elements that are less than
        * pivot value to the left side of the
        * array, others are placed to the right
        * side.
        * Returns left pointer.
     */

    public int partitionIt(int left, int right, long pivot)
    {
        int leftPtr = left;
        int rightPtr = right - 1;

        while(true)
        {
            while(a[++leftPtr] < pivot) ;
            while(a[--rightPtr] > pivot) ;

            if (leftPtr >= rightPtr)
            {
                break;
            }
            else
            {
                swap(leftPtr, rightPtr);
            }
        }
        /* Places median value to the center */
        swap(leftPtr, right - 1);

        return leftPtr;
    }

    /*
        * Represents Shell sorting.
        * h is calculated according to
        * Knuth's recursive formula.
        * Very similar to insertion
        * sorting.
     */

    public void shellSort()
    {
        int inner;
        int outer;
        long temp;

        int h = 1;

        while (h <= nElems / 3)
        {
            h = (h * 3) + 1;
        }

        while(h > 0)
        {
            for (outer = h; outer < nElems; outer++)
            {
                temp = a[outer];
                inner = outer;

                while(inner > h - 1 && a[inner - h] >= temp)
                {
                    a[inner] = a[inner - h];
                    inner -= h;
                }
                a[inner] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    public void insertionSort(int left, int right)
    {
        int in;
        int out;
        long temp;

        for (out = left + 1; out <= right; out++)
        {
            temp = a[out];
            in = out;

            while(in > left && a[in - 1] >= temp)
            {
                a[in] = a[in - 1];
                in--;
            }
            a[in] = temp;
        }
    }

    public void selectSort()
    {
        int in;
        int out;
        int min;
        long temp;

        for (out = 0; out < nElems - 1; out++)
        {
            min = out;

            for (in = out + 1; in < nElems; in++)
            {
                if (a[in] < a[min])
                {
                    min = in;
                }
            }

            temp = a[out];
            a[out] = a[min];
            a[min] = temp;
        }
    }

    public void bubbleSort()
    {
        int in;
        int out;
        long temp;

        for (out = nElems - 1; out > 1; out--)
        {
            for (in = 0; in < out; in++)
            {
                if (a[in] > a[in+1])
                {
                    temp = a[in];
                    a[in] = a[in+1];
                    a[in+1] = temp;
                }
            }
        }
    }
}
