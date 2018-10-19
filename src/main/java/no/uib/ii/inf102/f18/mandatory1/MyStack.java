package no.uib.ii.inf102.f18.mandatory1;

import java.util.Iterator;

/**
 * @author Torstein Strømme
 */
public class MyStack<E> implements Iterable<E>{
    private Node head;
    private int size;

    private class Node {
        private Node next;
        private E payload;

        private Node(E item) {
            this.payload = item;
            this.next = null;
        }
    }

    public E peek() {
        return this.head.payload;
    }

    public E pop() {
        E res = this.head.payload;
        this.head = this.head.next;
        this.size--;
        return res;
    }

    public void push(E item) {
        Node newNode = new Node(item);
        newNode.next = this.head;
        this.size++;
        this.head = newNode;
    }

    public int size() {
        return this.size;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node here = MyStack.this.head;

            @Override
            public boolean hasNext() {
                return this.here != null;
            }

            @Override
            public E next() {
                E res = this.here.payload;
                this.here = this.here.next;
                return res;
            }
        };
    }
}
