package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.counting.Pair;

/**
 * This is a basic implementation of insertion sort.
 * It does not extend Sort, nor does it employ any optimizations.
 */
public class InsertionSortMSD {

    public static void sort(Pair[] a, int lo, int hi, int d) {
         for(int i=lo+1; i<=hi; i++){
            int j=i;
            while(j>lo && less(a[j].getPinyin(), a[j-1].getPinyin(),d)) {
                swap(a,j-1,j);
                j--;
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        String a = v.substring(d);
        String b = w.substring(d);
        Boolean c = a.compareTo(b) < 0;
        return c;
    }

    private static void swap(Object[] a, int j, int i) {
        Object temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
}
