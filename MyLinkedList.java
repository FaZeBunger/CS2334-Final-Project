/**
 * Notes: Nodes are stored using 0-based indexing.
 **/
public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public MyLinkedList() {
        this.head = null;
        this.tail = this.head;
    }

    public Node<T> getHead() {
        return this.head;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Prints all values in order
     **/
    public void print() {
        Node<T> curr_node = this.head;

        int idx = 0;
        while (curr_node != null) {
            System.out.println("Value: " + curr_node.getValue() + " Idx: " + idx);
            curr_node = curr_node.getNext();
            idx++;
        }
    }

    /**
     * Appends to the end of the list
     **/
    public void add(T value) {
        Node<T> newNode = new Node<T>(value);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        this.size++;
    }

    /**
     * Checks whether a given index is valid for this LinkedList.
     **/
    private boolean validIndex(int idx) {
        if (idx < this.size && idx >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets the value at index n
     * 
     * @param n index to get value of
     * @throws IndexOutOfBoundsException if (index < 0 || index >= size())
     **/
    public T get(int n) throws IndexOutOfBoundsException {

        if (n < 0 || n >= this.size)
            throw new IndexOutOfBoundsException();

        Node<T> curr_node = this.head;
        for (int i = 0; i < n; i++) {
            curr_node = curr_node.getNext();
        }
        return curr_node.getValue();
    }

    /**
     * Inserts a new node with some value at index n, shifting all other values to
     * the right.
     *
     * @param n     index to insert at
     * @param value value to initialize new node with.
     **/
    public void insert(int n, T value) {
        if (n < 0 || n > this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> new_node = new Node<T>(value);

        if (n == 0) {
            new_node.setNext(this.head);
            this.head = new_node;
            if (this.size == 0) {
                this.tail = new_node;
            }
        } else {
            Node<T> curr_node = this.head;
            for (int i = 0; i < n - 1; i++) {
                curr_node = curr_node.getNext();
            }
            new_node.setNext(curr_node.getNext());
            curr_node.setNext(new_node);
            if (n == this.size) {
                this.tail = new_node;
            }
        }
        this.size++;
    }

    /**
     * Removes the node at a specified index in the LinkedList.
     * 
     * @param index the index at which to remove the node.
     * @return The value of the removed node.
     * @throws IndexOutOfBoundsException if (index < 0 || index >= size())
     **/
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            String msg = String.format("Cannot remove from index %d in a list of size %d.", index, this.size);
            throw new IndexOutOfBoundsException(msg);
        }

        T removedValue;

        if (index == 0) {
            removedValue = this.head.getValue();
            this.head = this.head.getNext();
            if (this.head == null) {
                this.tail = null;
            }
        } else {
            Node<T> previousNode = this.head;
            for (int i = 0; i < index - 1; i++) {
                previousNode = previousNode.getNext();
            }
            Node<T> removedNode = previousNode.getNext();
            removedValue = removedNode.getValue();
            previousNode.setNext(removedNode.getNext());
            if (previousNode.getNext() == null) {
                this.tail = previousNode;
            }
        }

        this.size--;
        return removedValue;
    }
}
