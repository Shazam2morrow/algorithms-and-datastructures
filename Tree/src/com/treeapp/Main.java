/*
    * This app demonstrates Tree data structure
    *
    * @author Shazam2morrow
    * @date 27 October, 2018
 */

package com.treeapp;

public class Main
{
    public static void main(String[] args)
    {
	    Tree tree = new Tree();

	    tree.insert(50, 50);
	    tree.insert(21, 21);
	    tree.insert(75, 75);
	    tree.insert(11, 11);
	    tree.insert(19, 19);
	    tree.insert(26, 26);
	    tree.insert(100, 100);
	    tree.insert(5, 5);
	    tree.insert(32, 32);
	    tree.insert(89, 89);

        tree.display();

        System.out.println("----------------------------------------");

        if (tree.delete(50))
        {
            tree.display();
        }
        System.out.println("----------------------------------------");
        System.out.println("MAX is :" + tree.getMax().key);
        System.out.println("MIN is :" + tree.getMin().key);
    }
}

class Node
{
    public int iData;
    public int key;

    Node leftChild;
    Node rightChild;

    public void displayNode()
    {
        System.out.println(
                "Key: " + key  + ", iData is " + iData
        );
    }
}

class Tree
{
    private Node root;

    public Tree()
    { root = null; }

    public boolean isTreeEmpty()
    {
        return (root == null);
    }

    public void display()
    {
        inOrder(root);
    }

    private void inOrder(Node local)
    {
        if (local != null)
        {
            inOrder(local.leftChild);

            local.displayNode();
            inOrder(local.rightChild);
        }
    }

    /*
        * Binary searching
     */

    public Node find(int key)
    {
        Node current = root;

        if (!isTreeEmpty())
        {
            while (current.key != key)
            {
                if (key < current.key)
                {
                    current = current.leftChild;
                } else
                {
                    current = current.rightChild;
                }
                if (current == null)
                {
                    return null;
                }
            }

            return current;
        }
        else
        {
            return null;
        }
    }

    public void insert(int data, int key)
    {
        Node newNode = new Node();
        newNode.iData = data;
        newNode.key = key;

        if (isTreeEmpty())
        {
            root = newNode;
        }
        else
        {
            Node current = root;
            Node parent;

            while (true)
            {
                parent = current;

                if (key < current.key)
                {
                    current = current.leftChild;

                    if (current == null)
                    {
                        parent.leftChild = newNode;
                        return;
                    }
                }
                else
                {
                    current = current.rightChild;

                    if (current == null)
                    {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(int key)
    {
            Node current = root;
            Node parent = root;
            boolean isLeftChild = true;

            while (current.iData != key)
            {
                parent = current;

                if (key < current.key)
                {
                    isLeftChild = true;
                    current = current.leftChild;
                }
                else
                {
                    isLeftChild = false;
                    current = current.rightChild;
                }

                // If an element was not found
                if (current == null)
                {
                    return false;
                }
            }
            // Deleting node was found!

            // If node does not has any child he is just removed!
            if  (
                    current.leftChild == null &&
                    current.rightChild == null
                )
            {
                if (current == root)
                {
                    root = null;
                }
                    else if (isLeftChild)
                    {
                        parent.leftChild = null;
                    }
                else
                {
                    parent.rightChild = null;
                }
            }

            // If there is no right child than node is substituted by left subtree
            else if (current.rightChild == null)
            {
                if (current == root)
                {
                    root = current.leftChild;
                }
                    else if (isLeftChild)
                    {
                       parent.leftChild = current.leftChild;
                    }
                else
                {
                    parent.rightChild = current.leftChild;
                }
            }

            // If there is no left child then node is substituted by right subtree
            else if (current.leftChild == null)
            {
                if (current == root)
                {
                    root = current.rightChild;
                }
                    else if (isLeftChild)
                    {
                        parent.leftChild = current.rightChild;
                    }
                else
                {
                    parent.rightChild = current.rightChild;
                }
            }

            // If there are two child then node is substituted by successor
            else
            {
                // Find successor for deleting node (current)
                Node successor = getSuccessor(current);

                // Link parent of current with intermediary
                if (current == root)
                {
                    root = successor;
                }
                    else if (isLeftChild)
                    {
                        parent.leftChild = successor;
                    }
                else
                {
                    parent.rightChild = successor;
                }

                successor.leftChild = current.leftChild;

                return true;
            }

        return false;
    }

    /*
        * Method returns node that is located after delNode.
        * To do this, he goes to the right child, then
        * he is looking throw the left chain of this node, for
        * searching for successor.
     */

    private Node getSuccessor(Node delNode)
    {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;

        while (current != null)
        {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != delNode.rightChild)
        {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }

        return successor;
    }

    public Node getMin()
    {
        Node current = root;
        Node last = null;

        while (current != null)
        {
            last = current;
            current = current.leftChild;
        }

        return last;
    }

    public Node getMax()
    {
        Node current = root;
        Node last = null;

        while (current != null)
        {
            last = current;
            current = current.rightChild;
        }

        return last;
    }
}