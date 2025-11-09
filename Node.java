public class Node<T> {
    private T value;
    private Node<T> nextNode;

    public Node(T value) {
        this.value = value;
        this.nextNode = null;
    }

    public T getValue() {
        return value;
    }

    public void setNext(Node<T> n) {
        this.nextNode = n;
    }

    public boolean hasNext() {
        if (this.nextNode == null) {
            return false;
        }
        return true;
    }

    public Node<T> getNext() {
        return this.nextNode;
    }
}
