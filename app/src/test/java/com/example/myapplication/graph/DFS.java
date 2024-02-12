package com.example.myapplication.graph;

import org.junit.Test;

import java.util.LinkedList;

public class DFS {
    @Test
    public void dfsTest() {
        DFSGraph g = new DFSGraph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println(
                "Following is Depth First Traversal "
                        + "(starting from vertex 2)");

        // Function call
        g.dfs(2);
    }
}

class DFSGraph {
    // number of vertexes
    private int v;
    private final LinkedList<Integer>[] adjacent;

    DFSGraph(int v) {
        this.v = v;
        adjacent = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adjacent[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adjacent[v].add(w);
    }

    void dfs(int root) {
        boolean[] visited = new boolean[v];
        dfsRecursive(root, visited);
    }

    private void dfsRecursive(int root, boolean[] visited) {
        visited[root] = true;
        System.out.print(root + " ");
        for (int current : adjacent[root]) {
            if (!visited[current]) {
                dfsRecursive(current, visited);
            }
        }
    }
}
