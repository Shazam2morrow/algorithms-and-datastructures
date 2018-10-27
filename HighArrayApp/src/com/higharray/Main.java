package com.higharray;

public class Main
{
    /*Need to construct at least 20 elements in array */
    public static void main(String[] args)
    {
	    HighArray highArray = new HighArray(10);
	    highArray.insert(5);
	    highArray.insert(30);
	    highArray.insert(7);
	    highArray.insert(14);
	    highArray.insert(19);
	    highArray.insert(1);
	    highArray.insert(11);
	    highArray.insert(18);
	    highArray.insert(48);
	    highArray.insert(77);

	    //Show array
	    highArray.display();

	    if (highArray.find(11))
        {
            System.out.println("Found 11 value");
        }
        else
        {
            System.out.println("There is no 11 value");
        }

        highArray.delete(19);
        highArray.delete(7);
        highArray.delete(30);

        highArray.display();
    }
}

class HighArray
{
    private long[] a;
    private int nElems;

    public HighArray(int size)
    {
        a = new long[size + 1];
        nElems = 0;
    }

    public boolean find(long searchKey)
    {
        int j;
        for (j = 0; j < a.length; j++)
        {
            if (a[j] == searchKey)
                break;
        }
        if (j == nElems)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void insert(long value)
    {
        a[nElems] = value;
        nElems++;
    }

    public boolean delete(long value)
    {
        int j;
        for (j = 0; j < a.length; j++)
        {
            if (a[j] == value)
                break;
        }
        if (j == nElems)
        {
            return false;
        }
        else
        {
            for (int k = j; k < nElems; k++)
            {
                a[k] = a[k + 1];
            }
            nElems--;
            return true;
        }
    }

    public void display()
    {
        for (int i = 0; i < nElems; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
