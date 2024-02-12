package com.example.myapplication.list;

import org.junit.Test;

public class InsertSortedList {
    @Test
    public void insertSortedListTest() {
        InertLinkedList llist = new InertLinkedList();
        llist.sortedInsert(new Node(5));
        llist.sortedInsert(new Node(10));
        llist.sortedInsert(new Node(7));
        llist.sortedInsert(new Node(3));
        llist.sortedInsert(new Node(1));
        llist.sortedInsert(new Node(9));
        System.out.println("Created Linked List");
        llist.printList();
    }
}

class InertLinkedList {
    Node head;

    void sortedInsert(Node newNode) {
        if (head == null || head.data >= newNode.data) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.data < newNode.data) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}