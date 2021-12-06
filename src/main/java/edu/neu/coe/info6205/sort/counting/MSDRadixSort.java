package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.elementary.InsertionSortMSD;

public class MSDRadixSort {

    // A utility function to get the digit
    // at index d in a integer

    private static final int cutoff = 3;

    public static int digit_at(long x, int d)
    {
        return (int)(x / Math.pow(10, d - 1)) % 10;
    }

    // The main function to sort array using
    // MSD Radix Sort recursively
    public static void MSD_sort(long[] arr, int lo, int hi, int d)
    {

        // recursion break condition
        if (hi <= lo || d < 1) {
            return;
        }

        if (hi <= lo + cutoff){
            //InsertionSortMSD.sort(arr, lo, hi, d);
            return;
        }

        int[] count = new int[10+2];
        long[] temp = new long[arr.length];

        // temp is created to easily swap strings in arr[]
        //unordered_map<int, int> temp;

        // Store occurrences of most significant character
        // from each integer in count[]
        for (int i = lo; i <= hi; i++) {
            int c = digit_at(arr[i], d);
            count[c+2]++;
        }

        // Change count[] so that count[] now contains actual
        //  position of this digits in temp[]
        for (int r = 0; r < 10 + 1; r++)      // Transform counts to indices.
            count[r + 1] += count[r];

        // Build the temp
        for (int i = lo; i <= hi; i++) {
            int c = digit_at(arr[i], d);
            temp[count[c+1] + lo] = arr[i];
            count[c+1]++;  // Reducing count[] to adjust the next location of particular digit
        }

//        for (int i = lo; i <= hi; i++) {
//            int c = digit_at(arr[i], d);
//            temp[count++] = arr[i];
//        }

        // Copy all integers of temp to arr[], so that arr[] now
        // contains partially sorted integers
//        for (int i = lo; i <= hi; i++)
//            arr[i] = temp[i - lo];

        if (hi + 1 - lo >= 0) System.arraycopy(temp, lo, arr, lo, hi + 1 - lo);

        // Recursively MSD_sort() on each partially sorted
        // integers set to sort them by their next digit
        for (int r = 0; r < 10; r++)
            MSD_sort(arr, lo + count[r], lo + count[r + 1] - 1,
                    d - 1);
    }

    // function find the largest integer
    public static long getMax(long[] arr, int n)
    {
        long mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // Main function to call MSD_sort
    public static void radixsort(long[] arr, int n)
    {
        // Find the maximum number to know number of digits
        long m = getMax(arr, n);

        // get the length of the largest integer
        int d = (int)Math.floor(Math.log10(Math.abs(m))) + 1;

        // function call
        MSD_sort(arr, 0, n - 1, d);
    }

    // Driver Code
    public static void main(String args[])
    {
        // Input array
        long[] arr = { 9330, 9950, 718, 8977, 6790,
                95, 9807, 741, 8586, 5710, 916, 8822, 612, 111, 222, 987654, 9330, 9950, 718, 8977, 6790,
                95, 9807, 741, 8586, 5710, 916, 8822, 612, 111, 222, 987654 };

        // Size of the array
        int n = arr.length;

        System.out.println("Unsorted array : ");

        // Print the unsorted array
        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i] + " ");
        }

        // Function Call
        radixsort(arr, n);

        System.out.println("Sorted array : ");

        // Print the sorted array
        for(int i=0; i<arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }

}
