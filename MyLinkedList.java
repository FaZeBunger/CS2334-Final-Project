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
        while (curr_node.hasNext()) {
            curr_node = curr_node.getNext();
            System.out.println("Value: " + curr_node.getValue() + " Idx: " + idx);
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

        int curr_idx = 0;
        Node<T> curr_node = this.head;
        if (!validIndex(n)) {
            String msg = String.format("LinkedList of size %d has no value at index: %d", this.size, n);
            throw new IndexOutOfBoundsException(msg);
        }
        while (curr_node.hasNext()) {
            if (curr_idx == n) {
                return curr_node.getValue();
            }
            curr_idx++;
            curr_node = curr_node.getNext();
        }

        // This should never happen.
        return null;
    }

    /**
     * Inserts a new node with some value at index n, shifting all other values to
     * the right.
     *
     * @param n     index to insert at
     * @param value value to initialize new node with.
     **/
    public void insert(int n, T value) {
        Node<T> new_node = new Node<T>(value);
        Node<T> curr_node = this.head;

        // Find the index before n, and set its next to our new node.
        int idx = 0;
        while (curr_node.hasNext()) {
            if (idx == n - 1) {
                Node<T> next_node = curr_node.getNext();
                new_node.setNext(next_node);
                curr_node.setNext(new_node);
            }
            curr_node = curr_node.getNext();
            idx++;
        }
    }

    /**
     * Removes the node at a specified index in the LinkedList.
     * 
     * @param index the index at which to remove the node.
     * @return The value of the removed node.
     * @throws IndexOutOfBoundsException if (index < 0 || index >= size())
     **/
    public T remove(int index) throws IndexOutOfBoundsException {
        if (!validIndex(index)) {
            String msg = String.format("Cannot remove from index %d in a list of size %d.", index, this.size);
            throw new IndexOutOfBoundsException(msg);
        }

        T removedValue;

        // Case 1: Removing the head of the list.
        if (index == 0) {
            removedValue = this.head.getValue();
            this.head = this.head.getNext();
            // If the list is now empty, the tail must also be null.
            if (this.head == null) {
                this.tail = null;
            }
        } else {
            // Case 2: Removing from the middle or the end.
            // Find the node *before* the one we want to remove.
            Node<T> previousNode = this.head;
            for (int i = 0; i < index - 1; i++) {
                previousNode = previousNode.getNext();
            }

            Node<T> removedNode = previousNode.getNext();
            removedValue = removedNode.getValue();

            // Unlink the removed node.
            previousNode.setNext(removedNode.getNext());

            // Case 2a: If we just removed the tail, update the tail reference.
            if (previousNode.getNext() == null) {
                this.tail = previousNode;
            }
        }

        this.size--;
        return removedValue;
    }
}
