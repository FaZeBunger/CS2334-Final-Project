public class LinkedListTests {
    public static void main(String[] args) {
        testIllegalGet();
        testAdd();
        testInsert();
        testRemove();
        System.out.println("All tests passed succesfully!");
    }

    public static void testIllegalGet() {
        MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
        try {
            ll.get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Passed testIllegalGet");
            return;
        }
        System.out.println("Failed testIllegalGet");
    }

    public static void testAdd() {
        MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
        Node<Integer> head = ll.getHead();
        assert head == null;

        ll.add(0);
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);

        boolean passed = true;
        int val = 0;
        Node<Integer> curr_node = ll.getHead();
        while (val <= 5) {
            if (val != curr_node.getValue()) {
                System.out.println(curr_node.getValue() + " should equal to " + val + "but is not.");
                passed = false;
            }
            curr_node = curr_node.getNext();
            val++;
        }

        if (passed)
            System.out.println("Passed testAdd!");
    }

    public static void testInsert() {
        MyLinkedList<Integer> ll = new MyLinkedList<Integer>();

        ll.add(0);
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.insert(3, 6);

        int val = ll.get(3);
        if (val != 6) {
            System.out.println("Index 3 should have been 6 but was: " + val);
        } else {
            System.out.println("Passed testInsert!");
        }
    }

    public static void testRemove() {
        MyLinkedList<Integer> ll = new MyLinkedList<Integer>();

        ll.add(0);
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);

        int removed_value = ll.remove(3);

        int val = ll.get(3);
        if (val != 4) {
            System.out.println("Index 3 should have been 4 but was: " + val);
        } else {
            System.out.println("Passed testRemove!");
        }

    }

}
