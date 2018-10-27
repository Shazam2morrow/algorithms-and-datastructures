
/*
 *   This app demonstrates the usage
 * of the undirected Graph. Here you can see
 * the BFS algorithm (a.k.a Breadth-first search)
 * based on the Queue structure.
 *   According to Wiki, BFS is an algorithm for
 * traversing or searching tree or graph data
 * structures. It starts at the tree root
 * (or some arbitrary node of a graph, sometimes
 * referred to as a 'search key'), and explores
 * all of the neighbor nodes at the present depth
 * prior to moving on to the nodes at the next
 * depth level.
 *
 * @author Shazam2morrow
 * @date 25 October, 2018
 *
 *   P.S.
 *       You are free to use this app for educational purposes.
 *       Best of luck!
*/

package com.graphwithbfs;

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

        theGraph.bfs();
    }
}

/*
 * Queue is used in the BFS algorithm for
 * storing visited nodes from the Graph.
 * It is a very basic implementation of the
 * queue based on the array. Sometimes better
 * to use the LinkedList data structure as a
 * physical storage for a queue.
 */

class Queue
{
    private final int SIZE = 15;
    private int[] queue;
    private int front;
    private int rear;

    public Queue()
    {
        queue = new int[SIZE];
        front = 0;
        rear = -1;
    }

    public void insert(int j)
    {
        if (rear == SIZE - 1)
        {
            rear = -1;
        }
        queue[++rear] = j;
    }

    public int remove()
    {
        int temp = queue[front++];

        if (front == SIZE)
        {
            front = 0;
        }
        return temp;
    }

    public boolean isEmpty()
    {
        return ( rear + 1 == front || (front + SIZE - 1) == rear );
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
*/

class Graph
{
    private final int SIZE = 15;
    private Vertex[] vertexList;
    private boolean[][] adjMatrix;
    private int nVerts;
    private Queue aQueue;

    public Graph()
    {
        vertexList = new Vertex[SIZE];
        adjMatrix = new boolean[SIZE][SIZE];
        nVerts = 0;
        aQueue = new Queue();

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

    public void bfs()
    {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        aQueue.insert(0);

        int v2;

        while (!aQueue.isEmpty())
        {
           int v1 = aQueue.remove();

            while ( (v2 = getAdjUnvisitedVertex(v1)) != -1)
            {
                vertexList[v2].wasVisited = true;
                displayVertex(v2);
                aQueue.insert(v2);
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

