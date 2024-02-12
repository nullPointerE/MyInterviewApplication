package com.example.myapplication.list;

import org.junit.Test;


public class DeleteList {
    @Test
    public void test() {
        Node head = new Node(12);
        LinkedList list = new LinkedList();
        list.head = head;
        list.head.next = new Node(15);
        list.head.next.next = new Node(10);
        list.head.next.next.next = new Node(11);
        list.head.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next = new Node(6);
        list.head.next.next.next.next.next.next = new Node(2);
        list.head.next.next.next.next.next.next.next = new Node(3);

        System.out.println("Given Linked List :");
        list.printList(head);
        System.out.println(" ");

        // Let us delete the node with value 10
        System.out.println("Deleting node :" + head.next.next.data);
        list.deleteNode(head, head.next.next);

        System.out.println("Modified Linked list :");
        list.printList(head);
        System.out.println(" ");

        // Let us delete the first node
        System.out.println("Deleting first Node");
        list.deleteNode(head, head);
        System.out.println("Modified Linked List");
        list.printList(head);

    }
}

class LinkedList {
    Node head;

    public void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println(" ");
    }

    public void deleteNode(Node head, Node deleteNode) {
        if (head == deleteNode) {
            if (head.next == null) {
                System.out.println("Can't delete");
                return;
            }
            head.data = head.next.data;
            head.next = head.next.next;
        } else {
            Node pre = head;
            while (pre.next != null && pre.next != deleteNode) {
                pre = pre.next;
            }
            if(pre.next == null){
                // deleteNode not exist
                return;
            } else {
                pre.next = pre.next.next;
            }
        }
    }

}
