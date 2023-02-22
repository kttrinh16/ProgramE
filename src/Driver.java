import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

//https://www.geeksforgeeks.org/smallest-power-of-2-greater-than-or-equal-to-n/

public class Driver {

    //hashing function to get the unique key
    public static int hash(int k, int t) {
        int h = ((k >> 16) ^ k) * 0x119df1f3;
        h = ((h >> 16) ^ h) * 0x119df1f3;
        return ((h >> 16) ^ h) % t;
    }

    static int nextPowerOf2(int n) {
        int p = 1;
        if (n > 0 && (n & (n - 1)) == 0)
            return n;

        while (p < n)
            p <<= 1;

        return p;
    }


    public static void main(String[] args) {

        //initialize variable for the file reader
        File inputFile;
        String inputFileName;

        try {

            //file scanner
            Scanner s = new Scanner(System.in);
            System.out.println("Please enter a File name from the working directory: ");
            inputFileName = s.next();
            inputFile = new File(inputFileName);
            Scanner fileScanner = new Scanner(inputFile);


            //loops through the file and counts the size
            int heapSize = 0;
            while (fileScanner.hasNext()) {
                heapSize++;
                fileScanner.next();
            }
            int dashAmount = heapSize - 1; //decreases the amount of --- needed
            s.close();
            fileScanner.close();


            //reopens scanner to build a heap
            fileScanner = new Scanner(inputFile);
            MinHeap heap = new MinHeap(heapSize);
            System.out.println("Building Min Heap");
            int n = 0;

            //loops through the file and creates the min heap and adds the dashes to the empty spaces
            while (fileScanner.hasNext()) {
                heap.add(Integer.parseInt(fileScanner.next()));
                heap.printHeap();
                //loop for the dashes
                for (int i = dashAmount; i >= 0; i--) {
                    System.out.print("--- ");
                }
                dashAmount--;
                System.out.println();

                if (heapSize % 20 == 0) {
                    n = n + 20;
                }
                System.out.print("");
            }

            System.out.println(); //console spacing

            //sorting the min heap
            int[] heap2 = heap.getHeap();
            HeapSort sortedHeap = new HeapSort();
            System.out.println("Sorting the Min Heap from greatest to least");
            sortedHeap.sort(heap2);

            fileScanner.close();

            System.out.println(); //console spacing

            //calculating the smallest power of 2 for the table size
            int min = heapSize + 5;
            int hashSize = nextPowerOf2(min);


            fileScanner = new Scanner(inputFile);


            //hashing the min heap
            Hashtable<Integer, Integer> hashTable = new Hashtable<Integer, Integer>();
            while (fileScanner.hasNext()) {
                int c = 0; //counter for the number of collision
                int value = Integer.parseInt(fileScanner.next());
                int key = hash(value, hashSize);

                while (hashTable.containsKey(key)) {
                    c++;
                    key++;
                }
                hashTable.put(key, value);

                System.out.println("Key " + value + " hashes to position " + key + " after " + c + " collisions.");

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
