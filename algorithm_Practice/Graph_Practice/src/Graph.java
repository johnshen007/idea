// Java program to print BFS traversal from a given source vertex. 
// BFS(int s) traverses vertices reachable from s. (图的广度优先搜索遍历)
import java.io.*;
import java.util.*;

// This class represents a directed graph using adjacency list 
// representation 
class Graph
{
    private int V;   // No. of vertices 
    private LinkedList<Integer> adj[]; //Adjacency Lists (邻接表，此处用链表型数组表示)

    // Constructor 
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v]; //创建长度为v类型为链表的数组
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph (即向第v个顶点添加指向第w个顶点的有向路径(边))
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }

    // prints BFS traversal from a given source s 
    void BFS(int s)
    {
        // Mark all the vertices as not visited(By default 
        // set as false) 
        boolean visited[] = new boolean[V];

        // Create a queue for BFS

        // 核心技巧，利用队列的入出队特点实现先兄弟节点，后子节点顺序入队(访问)

        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it 
        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it 
            s = queue.poll();
            System.out.print(s+" ");

            // Get all adjacent vertices of the dequeued vertex s 
            // If a adjacent has not been visited, then mark it 
            // visited and enqueue it 
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    // Driver method to 
    public static void main(String args[])
    {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

        g.BFS(2);
    }
}
// This code is contributed by Aakash Hasija 