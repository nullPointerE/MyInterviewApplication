package com.example.myapplication.list;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ExpirePriorityQueueTest {
    @Test
    public void expirePriorityQueue() {
        Store s = new Store(5);
        s.set("C", 3, 10, 10001);
        s.get("C");
        s.time = 900;
        s.set("F", 10, 5, 5001);
        s.set("G", 9, 5, 5004);
        s.set("H", -1, 5, 5009);
        s.set("I", 1, 5, 5011);
        s.set("C", 1, 5, 5021);
        s.get("C");
        s.get("D");
    }
}


class Store {
    int size;

    int time = 0;
    PriorityQueue<Item> pq;
    HashMap<String, Item> map;

    Store(int size) {
        this.size = size;
        pq = new PriorityQueue<>(size, new ItemComparator());
        map = new HashMap<>();
    }

    void set(String key, int value, int priority, int expiration) {
        Item newItem = new Item(key, value, priority, expiration + time);
        //check existing key
        Item existingItem = map.get(key);
        if (existingItem != null && existingItem.value == value) {
            // remove existing item from queue
            pq.remove(existingItem);
        }
        pq.add(newItem);
        map.put(key, newItem);
        // remove the extra items
        while (pq.size() > size) {
            Item item = pq.poll();
            if (item != null && item.key != null) {
                map.remove(item.key);
            }
        }
    }

    void get(String key) {
        Item item = map.get(key);
        if (item != null && item.expiration > time) {
            System.out.println(key + " " + item.value);
        } else {
            System.out.println(key + " null");
        }

    }
}

class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        if (item1.expiration < item2.expiration) {
            return -1;
        } else if (item1.expiration > item2.expiration) {
            return 1;
        } else {
            if (item1.priority < item2.priority) {
                return 1;
            } else if (item1.priority > item2.priority) {
                return -1;
            }
        }
        return 0;
    }
}

class Item {
    String key;
    int value;
    int priority;
    int expiration;

    Item(String key, int value, int priority, int expiration) {
        this.key = key;
        this.value = value;
        this.priority = priority;
        this.expiration = expiration;
    }
}
