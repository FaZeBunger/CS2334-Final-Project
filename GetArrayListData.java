import java.util.function.Supplier;
import java.util.Random;


public class GetArrayListData {

	static final int DATA_SIZE = 50_000;
    static final int NUM_RAND_LOOKUPS = 100;

    public static void main(String[] args) {
        // Generate both lists using a single generic method
        MyArrayList<Double> doubleList = generateRandomList(DATA_SIZE, DataGenerator::generateDouble);
        MyArrayList<String> stringList = generateRandomList(DATA_SIZE, DataGenerator::generateString);

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
     * Calculates the time taken to traverse an array list from start to end.
     * This is a generic method declared with <E> before the return type.
     * 
     * @param list The array list remove values from
     * @param <E>  The type of elements in the list.
     * @return The time in nanoseconds for the traversal.
     */
    public static <E> long getInsertionTime(MyArrayList<E> list, Supplier<E> dataGenerator) {
        Random random = new Random();

        long loop_start_time = System.nanoTime();
        list.add(dataGenerator.get()); // Append to end
        list.add(0, dataGenerator.get()); // Insert to beginning
        list.add(random.nextInt(DATA_SIZE), dataGenerator.get()); // Insert somewhere random in the list
        long loop_end_time = System.nanoTime();

        return loop_end_time - loop_start_time;
    }

    /**
     * Calculates the time taken to traverse an array list from start to end.
     * This is a generic method declared with <E> before the return type.
     * 
     * @param list The array list remove values from
     * @param <E>  The type of elements in the list.
     * @return The time in nanoseconds for the traversal.
     */
    public static <E> long getRemovalTime(MyArrayList<E> list) {
        Random random = new Random();

        long loop_start_time = System.nanoTime();
        list.remove(0); // Remove first
        list.remove(list.getSize() - 1); // Remove last
        list.remove(random.nextInt(list.getSize())); // Remove somewhere random in the list
        long loop_end_time = System.nanoTime();

        return loop_end_time - loop_start_time;
    }

    /**
     * Calculates the time taken to traverse an array list from start to end.
     * This is a generic method declared with <E> before the return type.
     * 
     * @param list The array list to do random lookups on.
     * @param <E>  The type of elements in the list.
     * @return The time in nanoseconds for the traversal.
     */
    public static <E> long getRandomTime(MyArrayList<E> list) {
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
     * Calculates the time taken to traverse an array list from start to end.
     * This is a GENERIC METHOD, declared with <E> before the return type.
     * 
     * @param list The array list to traverse.
     * @param <E>  The type of elements in the list.
     * @return The time in nanoseconds for the traversal.
     */
    public static <E> long getSequentialTime(MyArrayList<E> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < list.getSize(); i++) {
            list.get(i);
        }
        long endTime = System.nanoTime();

        
        return endTime - startTime;
    }

    /**
     * Generates a MyArrayList of a specified size with random data.
     * This is a GENERIC METHOD that uses a Supplier to create data elements.
     * 
     * @param size          The number of elements to add to the list.
     * @param dataGenerator A function that provides the data for each new element.
     * @param <T>           The type of data to generate.
     * @return A new MyArrayList filled with random data.
     */
    public static <T> MyArrayList<T> generateRandomList(int size, Supplier<T> dataGenerator) {
        MyArrayList<T> arraylist = new MyArrayList<T>();
        for (int i = 0; i < size; ++i) {
            arraylist.add(dataGenerator.get());
        }
        return arraylist;
    }
}

