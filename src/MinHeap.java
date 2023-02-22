import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.NoSuchElementException;

//From https://medium.com/@ankur.singh4012/implementing-min-heap-in-java-413d1c20f90d, used under academic fair use guideline
/**************************************************
 * A min-heap is a complete binary tree in which
 * the value in each internal node is smaller than
 * or equal to the values in the children of that
 * node. Mapping the elemetns of a heap into an
 * array is trivial: if a node is stored an index
 * k, then its left child is stored at index 2k+1
 * and its right child at index 2k + 2
 *
 * @author Kevin Trinh
 *
 ***************************************************/

public class MinHeap {

    private int capacity;
    private int size = 0;
    private int[] heap;

    MinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
    }

    /**
     * getter method for the left child
     *
     * @param parentIndex
     * @return left child
     */
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**
     * getter method for right child
     *
     * @param parentIndex
     * @return
     */
    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /**
     * getter method for the parent in the heap
     * @param childIndex
     * @return parent position
     */
    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * checks to see if the parent has a left child
     *
     * @param index
     * @return boolean
     */
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    /**
     * checks to see if the parent has a left child
     * @param index
     * @return boolean
     */
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    /**
     * checks to see if there is a parent
     * @param index
     * @return boolean
     */
    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    /**
     * sets the data for the left child
     * @param parentIndex
     * @return value of left child
     */
    private int leftChild(int parentIndex) {
        return heap[getLeftChildIndex(parentIndex)];
    }

    /**
     * sets the data for the right child
     *
     * @param parentIndex
     * @return data for right child
     */
    private int rightChild(int parentIndex) {
        return heap[getRightChildIndex(parentIndex)];
    }

    /**
     * sets the data for the parent
     * @param childIndex
     * @return value of parent
     */
    private int parent(int childIndex) {
        return heap[getParentIndex(childIndex)];
    }

    /**
     * this will swap two nodes
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2) {
        int element = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = element;
    }

    /**
     * checks to see if there is enough space in the heap
     * if not it doubles the capacity to ensure space
     */
    private void ensureCapacity() {
        if (size == capacity) {
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity = capacity * 2;
        }
    }

    /**
     * if the heap is not empty it will show
     * the first index in the heap
     * @return
     */
    private int peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    /**
     * this method will add a new element to the heap
     * @param item
     */
    public void add(int item) {
        ensureCapacity();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    /**
     * used when we have to add a new element
     */
    private void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && parent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    /**
     * when we need to remove an element
     */
    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallestChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallestChildIndex = getRightChildIndex(index);
            }

            if (heap[index] < heap[smallestChildIndex]) {
                break;
            } else {
                swap(index, smallestChildIndex);
            }
            index = smallestChildIndex;
        }
    }

    /**
     * printing the element in a heap
     */
    void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
    }

    /**
     * getter method for the heap
     * @return heap
     */
    int[] getHeap() {
        return heap;
    }
}


