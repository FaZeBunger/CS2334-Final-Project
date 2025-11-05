/**
 * Uses 0-based indexing.
 * 
 * TODO: Add the insert method
 **/
public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = -1;

    public MyLinkedList() {
        this.head = null;
        this.tail = this.head;
    }

    /**
     * Prints all values in order
     **/
    public void print() {
        Node<T> curr_node = this.head;
        while (curr_node.hasNext()) {
            curr_node = curr_node.getNext();
            System.out.println("Value: " + curr_node.getValue() + " Idx: " + curr_node.getIdx());
        }
    }

    /**
     * Appends to the end of the list
     **/
    public void add(T value) {
        this.size++;
        Node<T> newNode = new Node<T>(value, this.size);
        if (size == 0 || this.head == null) {
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
     * Gets the value at some index in the LinkedList
     **/
    public T get(int index) {
        int curr_idx = 0;
        Node<T> curr_node = this.head;
        if (!validIndex(index)) {
            String msg = String.format("LinkedList of size %d has no value at index: %d", this.size, index);
            throw new IndexOutOfBoundsException(msg);
        }
        while (curr_node.hasNext()) {
            if (curr_idx == index) {
                return curr_node.getValue();
            }
            curr_idx++;
            curr_node = curr_node.getNext();
        }

        // This should never happen.
        return null;
    }

    public void insert(int index) {
    }

    /**
     * Gets the value at some index in the LinkedList
     **/
    public void remove(int index) {
        Node<T> curr_node = this.head;

        if (!validIndex(index)) {
            String msg = String.format("LinkedList of size %d has no value at index: %d", this.size, index);
            throw new IndexOutOfBoundsException(msg);
        }

        while (curr_node.hasNext()) {
            if (curr_node.getIdx() + 1 == index) {
                Node<T> removed_node = curr_node.getNext();
                Node<T> new_next = removed_node.getNext();

                removed_node.setNext(null);
                curr_node.setNext(new_next);
                return;
            }
            curr_node = curr_node.getNext();
        }

        String msg = String.format("LinkedList has no value at index: %d", index);
        throw new IndexOutOfBoundsException(msg);
    }
}
