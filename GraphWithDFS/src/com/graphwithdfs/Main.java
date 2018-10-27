/*
    *   This app demonstrates the usage
    * of the undirected Graph. Here you can see
    * the DFS algorithm (a.k.a Depth-search first)
    * based on the Stack structure.
    *   According to Wiki the DFS is an algorithm
    * for traversing or searching tree or graph
    * data structures. The algorithm starts
    * at the root node (selecting some arbitrary
    * node as root node in the case of a graph)
    * and explores as far as possible along each
    * branch before backtracking.
    *
    * @author Shazam2morrow
    * @date 25 October, 2018
    *
    *   P.S.
    *       You are free to use this app for educational purposes.
    *       Best of luck!
 */

package com.graphwithdfs;

public class Main
{
    public static void main(String[] args)
    {
        /* Small example */
        Graph theGraph = new Graph();

        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addVertex('F');
        theGraph.addVertex('G');

        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(2, 4);
        theGraph.addEdge(0, 3);
        theGraph.addEdge(0, 5);
        theGraph.addEdge(5, 6);

        theGraph.dfs();
    }
}

/*
    * Stack is used in the DFS algorithm for
    * storing visited nodes from the Graph.
    * It is a very basic implementation of the
    * stack based on the array. Sometimes better
    * to use the LinkedList data structure as a
    * physical storage for a stack.
 */

class Stack
{
    private final int SIZE = 15;
    private int[] stack;
    private int top;

    public Stack()
    {
        stack = new int[SIZE];
        top = -1;
    }

    public void push(int j)
    {
        stack[++top] = j;
    }

    public int pop()
    {
        return stack[top--];
    }

    public int peek()
    {
        return stack[top];
    }

    public boolean isEmpty()
    {
        return (top == -1);
    }
}

class Vertex
{
    public char name;
    public boolean wasVisited;

    public Vertex(char label)
    {
        name = label;
        wasVisited = false;
    }
}

/*
    * There are two ways of representing
    * links between two vertices. The first
    * way is to use Matrix of adjacency.
    * Physically it is two-dimensional array
    * where every row and column have one of the
    * following value: true or false. True means
    * that two vertices are adjacent, false that
    * they are not. The second way is to use List
    * of adjacency. Where every array cell is the Linked
    * List.
    *   (If we talk about undirected graph the half of the
    *   adjMatrix will be the same as the first one. So we don't
    *   need store the whole array, the half of the matrix will
    *   be enough. However it can be complicated to create
    *   a triangle array in some programing languages.)
 */

class Graph
{
    private final int SIZE = 15;
    private Vertex[] vertexList;
    private boolean[][] adjMatrix;
    private int nVerts;
    private Stack aStack;

    public Graph()
    {
        vertexList = new Vertex[SIZE];
        adjMatrix = new boolean[SIZE][SIZE];
        nVerts = 0;
        aStack = new Stack();

        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                adjMatrix[i][j] = false;
            }
        }
    }

    public void addVertex(char label)
    {
        vertexList[nVerts++] = new Vertex(label);
    }

    /*
        * Adds an edge between two vertices
     */

    public void addEdge(int start, int end)
    {
        adjMatrix[start][end] = true;
        adjMatrix[end][start] = true;
    }

    public void displayVertex(int v)
    {
        System.out.println(vertexList[v].name);
    }

    public void dfs()
    {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        aStack.push(0);

        while (!aStack.isEmpty())
        {
            int v = getAdjUnvisitedVertex(aStack.peek());

            if (v == -1)
            {
                aStack.pop();
            }
            else
            {
                vertexList[v].wasVisited = true;
                displayVertex(v);
                aStack.push(v);
            }
        }

        for (int k = 0; k < nVerts; k++)
        {
            vertexList[k].wasVisited = false;
        }
    }

    private int getAdjUnvisitedVertex(int vertex)
    {
        for (int j = 0; j < nVerts; j++)
        {
            if (adjMatrix[vertex][j] && !vertexList[j].wasVisited)
            {
                return j;
            }
        }
        return -1;
    }
}
