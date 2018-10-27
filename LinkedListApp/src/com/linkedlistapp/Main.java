/*
    * This class demonstrates LinkedList data structure
    *
    * @author Shazam2morrow
    * @date 27 October, 2018
 */


package com.linkedlistapp;

public class Main {

    public static void main(String[] args) {
	    LinkList linkList = new LinkList();
	    linkList.insertFirst(22, 2.99);
	    linkList.insertFirst(44, 4.99);
	    linkList.insertFirst(66, 6.99);
	    linkList.insertFirst(88, 8.99);
	    linkList.insertFirst(150, 8.99);
	    linkList.insertFirst(33, 8.99);
	    linkList.insertFirst(149, 8.99);

	    linkList.displayList();

	    Link aLink = linkList.find(150);
	    aLink.displayLink();
	    System.out.println("");

	    aLink = linkList.find(149);
	    aLink.displayLink();
        System.out.println("");

        aLink = linkList.delete(88);
        aLink.displayLink();
        System.out.println("");

        linkList.delete(44);
        linkList.delete(150);

        linkList.displayList();
    }
}

class Link {
    public int iData;
    public double dData;
    public Link next = null;

    public Link(int iData, double dData) {
        this.iData = iData;
        this.dData = dData;
    }

    public void displayLink() {
        System.out.print("{ " + iData + " " + dData  + " }");
    }
}

class LinkList {
    private Link first;

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(int id, double dd) {
        Link newLink = new Link(id, dd);
        newLink.next = first;
        first = newLink;
    }

    public Link deleteFirst() {

        if (!isEmpty()) {
             Link temp = first;
             first = first.next;
             return temp;
        }
        return null;
    }

    public Link find(int key) {
        if (!isEmpty()) {
            Link current = first;

            while (current.iData != key) {
                if (current == null) {
                    return null;
                } else {
                    current = current.next;
                }
            }
            return current;
        }
        return null;
    }

    public Link delete(int key) {
        if (!isEmpty()) {
            Link current = first;
            Link previous = first;

            while (current.iData != key) {
                if (current == null) {
                    return null;
                } else {
                    previous = current;
                    current = current.next;
                }
            }
            if (current == first) {
                first = first.next;
            } else {
                previous.next = current.next;
            }
            return current;
        }
        return null;
    }

    public void displayList() {
        if (!isEmpty()) {
            System.out.println("List (first-->last): ");
            Link current = first;
            while (current != null) {
                current.displayLink();
                current = current.next;
            }
            System.out.println("");
        }
    }
}
