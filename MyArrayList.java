/*
 Notes:
    Default internal length of ArrayList arrays is 10 ArrayLists grow by a factor of 1.5 ArrayLists do not shrink unless the trimToSize() method is called 
 */

import java.util.Arrays;

public class MyArrayList<T> {
    /** Internal array. Will store objects of type T */
    private Object[] array;

    /** Tracks how much of internal array is being used */
    private int size;

    /** Factor array should grow by */
    private static final double GROWTH_FACTOR = 1.5;

    /** Default length of internal array */
    private static final int DEFAULT_INIT_CAPACITY = 10;

    /**
     * Default constructor. Constructs ArrayList with {@link #DEFAULT_INIT_CAPACITY}
     */
    public MyArrayList() {
        this(DEFAULT_INIT_CAPACITY);
    }

    /**
     * Overloaded constructor
     * 
     * @param initialCapacity initial length of internal array
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    public MyArrayList(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Initial capacity cannot be less than 0");
        array = new Object[initialCapacity];
        size = 0;
    }

    /**
     * Efficient method that clears and populates array with only new randomly
     * generated strings. Does not
     * log any data.
     * Warning: Can only be used if type T is the String class. Function will not
     * check to see if T is valid.
     * 
     * @param quantity amount of strings to generate
     */
    public void populateRandomStrings(int quantity) {
        array = new Object[quantity];
        size = quantity;

        for (int i = 0; i < array.length; i++) {
            array[i] = DataGenerator.generateString();
        }
    }

    /**
     * Efficient method that clears and populates array with only new randomly
     * generated strings. Does not
     * log any data.
     * Warning: Can only be used if type T is the Double class. Function will not
     * check to see if T is valid.
     * 
     * @param quantity amount of doubles to generate
     */
    public void populateRandomDoubles(int quantity) {
        array = new Object[quantity];
        size = quantity;

        for (int i = 0; i < array.length; i++) {
            array[i] = DataGenerator.generateDouble();
        }
    }

    /**
     * Adds element to end of the list
     * 
     * @param el element to add
     */
    public void add(T el) {
        add(size, el);
    }

    /**
     * Inserts element at specified index. Shifts element currently at that index
     * and all subsequent elements to the right
     * 
     * @param index index at which element is to be inserted
     * @param el    element to be inserted
     * @throws IndexOutOfBoundsException if index is out of range
     *                                   {@code (index < 0 || index > size())}
     */
    public void add(int index, T el) throws IndexOutOfBoundsException {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index is outside range (index < 0 || index > size())");

        if (size >= array.length)
            grow();

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = el;
        size++;

    }

    /**
     * Returns the element at the specified index
     * 
     * @param index index of element to return
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
     *                                   index >= size())
     */

    @SuppressWarnings("unchecked")
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index is outside range (index , 0 || index >= size())");


        T output = (T) array[index];

        return output;
    }

    /**
     * Removes the element at the specified index. Shifts remaining elements to the
     * left.
     * 
     * @param index index of element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
     *                                   index >= size())
     */

    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index is outside range (index , 0 || index >= size())");

        @SuppressWarnings("unchecked")
        T removedElement = (T) array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[size - 1] = null;
        size--;


        return removedElement;
    }

    /**
     * Get number of elements in list
     * 
     * @return number of elements in list
     */
    public int size() {
        return size;
    }

    /**
     * Helper method that grows the internal array by {@link #GROWTH_FACTOR}
     */
    private void grow() {
        int newLength;

        if (array.length == 0 || array.length == 1)
            newLength = array.length + 1;
        else
            newLength = (int) (array.length * GROWTH_FACTOR);

        array = Arrays.copyOf(array, newLength);
    }

    /**
     * Prints all values in internal array with indexes inside the range
     * {@code (index >= 0 && index < size())}
     */
    public void print() {
        System.out.println(formatArrayString(0, size));
    }

    /**
     * Prints the following object data: entire internal {@link #array} contents,
     * internal {@link #array}
     * length, value {@link #size} property, and values of {@link #GROWTH_FACTOR}
     * and {@link #DEFAULT_INIT_CAPACITY}
     * static constants
     */
    public void debugPrint() {
        System.out.println(
                "array contents: " + formatArrayString(0, array.length) +
                        "\narray length: " + array.length +
                        "\nArrayList size: " + size +
                        "\ngrowth factor: " + GROWTH_FACTOR +
                        "\ndefault initial capacity: " + DEFAULT_INIT_CAPACITY);
    }

    /**
     * Helper method that generates and returns formatted string representation of
     * array elements within provided index bounds
     * 
     * @param startIndex inclusive starting index of elements to format
     * @param endIndex   exclusive ending index of elements to format
     * @return string representation of internal array
     * @throws IndexOutOfBoundsException if either index parameter is out of range
     *                                   {@code (index < 0 || index > array.length)}
     */
    private String formatArrayString(int startIndex, int endIndex) throws IndexOutOfBoundsException {
        if (startIndex < 0 || startIndex > array.length)
            throw new IndexOutOfBoundsException(
                    "startIndex is outside range (startIndex < 0 || startIndex > array.length)");

        if (endIndex < 0 || endIndex > array.length)
            throw new IndexOutOfBoundsException("endIndex is outside range (endIndex < 0 || endIndex > array.length)");

        String output = "[";

        for (int i = startIndex; i < endIndex; i++) {
            output += array[i] + ", ";
        }

        if (output.length() > 1)
            output = output.substring(0, output.length() - 2);

        output += "]";

        return output;
    }
}
