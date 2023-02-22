

//From https://www.geeksforgeeks.org/java-program-for-heap-sort/ used under fair academic guidelines

/*************************************
 * Heap sort is a comparison - based sorting
 * technique based on Binary heap data structure.
 * It is simular to the selection sort where we
 * first find the minimum element and place the
 * minimum element at the beginning. Repeat the
 * same process for the remaining elements.
 *
 * @author Kevin Trinh
 */

public class HeapSort {
    public void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
            printArray(arr);
        }
    }


    //heapify from Geeks for Geeks
    //https://www.geeksforgeeks.org/heap-sort-for-decreasing-order-using-min-heap/

    /**
     * heapify will shift the heap size
     * @param arr
     * @param n
     * @param i
     */
    static void heapify(int arr[], int n, int i) {
        int smallest = i; // Initialize smallest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is smaller than root
        if (l < n && arr[l] < arr[smallest])
            smallest = l;

        // If right child is smaller than smallest so far
        if (r < n && arr[r] < arr[smallest])
            smallest = r;

        // If smallest is not root
        if (smallest != i) {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, smallest);
        }
    }

    /**
     * A utility function to print array of size n
     * */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}