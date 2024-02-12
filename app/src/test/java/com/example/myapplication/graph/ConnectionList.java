package com.example.myapplication.graph;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConnectionList {

    @Test
    public void testConnectionList() {
        ListGraph listGraph = new ListGraph();
        listGraph.addNodes(new String[]{"a->b", "r->s", "b->c", "x->c", "q->r", "y->x", "w->z", "c->a"});
        System.out.println(listGraph.isConnected(new String[]{"a", "q", "w"})); //false
        System.out.println(listGraph.isConnected(new String[]{"a", "c", "r"})); //true
        System.out.println(listGraph.isConnected(new String[]{"y", "z", "a", "r"})); // true
        System.out.println(listGraph.isConnected(new String[]{"a", "w"})); // false
    }

    public static class ListGraph {

        Map<String, String> nextMap = new HashMap<>();

        public void addNodes(String[] connections) {
            for (String connection : connections) {
                String[] fromTo = connection.split("->");
                if (fromTo.length == 2) {
                    nextMap.put(fromTo[0], fromTo[1]);
                }
            }
        }

        public boolean isConnected(String[] heads) {
            Set<String> visited = new HashSet<>();
            for (String head : heads) {
                Set<String> isLoop = new HashSet<>();
                String next = head;
                while (next != null && !isLoop.contains(next)) {
                    if (visited.contains(next)) {
                        return true;
                    } else {
                        visited.add(next);
                    }
                    isLoop.add(next);
                    next = nextMap.get(next);
                }
            }
            return false;
        }
    }
}

