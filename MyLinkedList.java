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
        this.size++;
        Node<T> newNode = new Node<T>(value);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }

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
     * Gets the value at some index in the LinkedList
     * 
     * @params index the index at which to remove the node.
     * @throws IndexOutOfBoundsException if (index < 0 || index >= size())
     **/
    public T remove(int index) throws IndexOutOfBoundsException {
        Node<T> curr_node = this.head;

        if (!validIndex(index)) {
            String msg = String.format("LinkedList of size %d has no value at index: %d", this.size, index);
            throw new IndexOutOfBoundsException(msg);
        }

        int idx = 0;
        while (curr_node.hasNext()) {
            if (idx == index - 1) {
                Node<T> removed_node = curr_node.getNext();
                Node<T> new_next = removed_node.getNext();

                removed_node.setNext(null);
                curr_node.setNext(new_next);
                return removed_node.getValue();
            }
            curr_node = curr_node.getNext();
            idx++;
        }

        String msg = String.format("LinkedList has no value at index: %d", index);
        throw new IndexOutOfBoundsException(msg);
    }
}
