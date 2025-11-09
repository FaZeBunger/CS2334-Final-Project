import java.util.function.Supplier;
import java.util.Random;

public class GetLinkedListData {
    static final int DATA_SIZE = 10_000;
    static final int NUM_RAND_LOOKUPS = 100;

    public static void main(String[] args) {
        // Generate both lists using a single generic method
        MyLinkedList<Double> doubleList = generateRandomList(DATA_SIZE, DataGenerator::generateDouble);
        MyLinkedList<String> stringList = generateRandomList(DATA_SIZE, DataGenerator::generateString);

        // Get traversal time for both lists
        long doubleSequentialTime = getSequentialTime(doubleList);
        long stringSequentialTime = getSequentialTime(stringList);

        System.out.println("Time to traverse " + DATA_SIZE + " Doubles: " + doubleSequentialTime + " ns");
        System.out.println("Time to traverse " + DATA_SIZE + " Strings: " + stringSequentialTime + " ns");

        // Get traversal time for both lists
        long doubleRandomTime = getRandomTime(doubleList);
        long stringRandomTime = getRandomTime(stringList);

        System.out.println("Random Lookup Time " + DATA_SIZE + " Doubles: " + doubleRandomTime + " ns");
        System.out.println("Random Lookup Time " + DATA_SIZE + " Strings: " + stringRandomTime + " ns");

        // Get traversal time for both lists
        long doubleRemovalTime = getRemovalTime(doubleList);
        long stringRemovalTime = getRemovalTime(stringList);

        System.out.println("Random Removal Time " + DATA_SIZE + " Doubles: " + doubleRemovalTime + " ns");
        System.out.println("Random Removal Time " + DATA_SIZE + " Strings: " + stringRemovalTime + " ns");

        // Get traversal time for both lists
        long doubleInsertionTime = getInsertionTime(doubleList, DataGenerator::generateDouble);
        long stringInsertionTime = getInsertionTime(stringList, DataGenerator::generateString);

        System.out.println("Random Insertion Time " + DATA_SIZE + " Doubles: " + doubleInsertionTime + " ns");
        System.out.println("Random Insertion Time " + DATA_SIZE + " Strings: " + stringInsertionTime + " ns");
    }

    /**
     * Calculates the time taken to traverse a linked list from head to tail.
     * This is a generic method declared with <E> before the return type.
     * 
     * @param list The linked list remove values from
     * @param <E>  The type of elements in the list.
     * @return The time in nanoseconds for the traversal.
     */
    public static <E> long getInsertionTime(MyLinkedList<E> list, Supplier<E> dataGenerator) {
        Random random = new Random();

        long loop_start_time = System.nanoTime();
        list.add(dataGenerator.get()); // Append to end
        list.insert(0, dataGenerator.get()); // Insert to begining
        list.insert(random.nextInt(DATA_SIZE), dataGenerator.get()); // Insert somewhere random in the list
        long loop_end_time = System.nanoTime();

        return loop_end_time - loop_start_time;
    }

    /**
     * Calculates the time taken to traverse a linked list from head to tail.
     * This is a generic method declared with <E> before the return type.
     * 
     * @param list The linked list remove values from
     * @param <E>  The type of elements in the list.
     * @return The time in nanoseconds for the traversal.
     */
    public static <E> long getRemovalTime(MyLinkedList<E> list) {
        Random random = new Random();

        long loop_start_time = System.nanoTime();
        list.remove(0); // Remove first
        list.remove(list.getSize() - 1); // Remove last
        list.remove(random.nextInt(list.getSize())); // Remove somewhere random in the list
        long loop_end_time = System.nanoTime();

        return loop_end_time - loop_start_time;
    }

    /**
     * Calculates the time taken to traverse a linked list from head to tail.
     * This is a generic method declared with <E> before the return type.
     * 
     * @param list The linked list to do random lookups on.
     * @param <E>  The type of elements in the list.
     * @return The time in nanoseconds for the traversal.
     */
    public static <E> long getRandomTime(MyLinkedList<E> list) {
        Random random = new Random();

        long loop_start_time = System.nanoTime();
        for (int i = 0; i < NUM_RAND_LOOKUPS; ++i) {
            int idx = random.nextInt(DATA_SIZE);
            E val = list.get(idx);
        }
        long loop_end_time = System.nanoTime();

        return loop_end_time - loop_start_time;
    }

    /**
     * Calculates the time taken to traverse a linked list from head to tail.
     * This is a GENERIC METHOD, declared with <E> before the return type.
     * 
     * @param list The linked list to traverse.
     * @param <E>  The type of elements in the list.
     * @return The time in nanoseconds for the traversal.
     */
    public static <E> long getSequentialTime(MyLinkedList<E> list) {
        long startTime = System.nanoTime();
        Node<E> current = list.getHead();
        while (current != null) {
            current = current.getNext();
        }
        long endTime = System.nanoTime();

        return endTime - startTime; // Corrected time calculation
    }

    /**
     * Generates a MyLinkedList of a specified size with random data.
     * This is a GENERIC METHOD that uses a Supplier to create data elements.
     * 
     * @param size          The number of elements to add to the list.
     * @param dataGenerator A function that provides the data for each new node.
     * @param <T>           The type of data to generate.
     * @return A new MyLinkedList filled with random data.
     */
    public static <T> MyLinkedList<T> generateRandomList(int size, Supplier<T> dataGenerator) {
        MyLinkedList<T> linkedlist = new MyLinkedList<T>();
        for (int i = 0; i < size; ++i) {
            linkedlist.add(dataGenerator.get());
        }
        return linkedlist;
    }
}
