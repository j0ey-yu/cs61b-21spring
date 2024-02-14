package deque;

public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel; // Point to itself to create a circular structure
        sentinel.next = sentinel; // Point to itself to create a circular structure
        size = 0;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        Node first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;
        first.next = null; // Eliminate the reference from the removed node
        first.prev = null; // Eliminate the reference from the removed node
        size--;
        return first.item;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        Node last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        last.next = null; // Eliminate the reference from the removed node
        last.prev = null; // Eliminate the reference from the removed node
        size--;
        return last.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) return node.item;
        return getRecursiveHelper(node.next, index - 1);
    }

    // Uncomment and implement iterator and equals methods if needed

    // public Iterator<T> iterator() { ... }

    // public boolean equals(Object o) { ... }
}

