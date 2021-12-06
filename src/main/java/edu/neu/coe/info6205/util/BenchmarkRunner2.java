package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BenchmarkRunner2 {

    public static void main(String[] args ){

        //This variable is the length of the arrays that are being tested.
        int length = 100;
        //This variable is used to select the type of array.
        int type;
        //This loop runs the benchmark for the 4 different types of arrays.
        for(type = 1; type<=4; type++){
            //This loop increments the length of the arrays by the doubling method.
            for(length = 100; length <= 25600; length = length*2) {
                //Run the benchmark for the current selection.
                runBenchmarks(type, length);
            }
            System.out.println("---------------------------------------------------------------------------------------------------");
        }

    }

    public static void runBenchmarks(int type,int n) {

        InsertionSort<Integer> sortFunction = new InsertionSort<Integer>();
        Consumer<Integer[]> consumerOfFunction = (t)->{sortFunction.sort(t, 0,t.length);};
        String nameOfArray = null;
        Supplier<Integer[]> supplierOrdered = () -> orderedArray(n);
        Supplier<Integer[]> supplierPartial = () -> partialOrderArray(n);
        Supplier<Integer[]> supplierRandom = () -> randomArray(n);
        Supplier<Integer[]> supplierReversed = () -> reversedArray(n);
        Supplier<Integer[]> supplier = supplierReversed;

        switch(type) {
            case 1:
                supplier = supplierOrdered;
                nameOfArray = "Ordered Array";
                break;
            case 2:
                supplier = supplierPartial;
                nameOfArray = "PartiallyOrdered Array";
                break;
            case 3:
                supplier = supplierRandom;
                nameOfArray = "Random Array";
                break;
            case 4:
                supplier = supplierReversed;
                nameOfArray = "Reversed Array";
                break;
        }

        Benchmark_Timer<Integer[]> test =new Benchmark_Timer<Integer[]>("InsertionSort: ",consumerOfFunction);
        double meantimetaken = test.runFromSupplier(supplier, 20);
        System.out.println("Array: "+ nameOfArray + " Length: " + n + " time: " + meantimetaken);
    }

    public static Integer[] orderedArray(int n)
    {
        Integer[] integers = new Integer[n];
        for(int i=0;i<n;i++)
            integers[i]=i+10;
        return integers;
    }

    public static Integer[] reversedArray(int n)
    {
        Integer[] integers = new Integer[n];
        int count =1;
        for(int i=0;i<n;i++)
        {
            integers[i]=n+10-count;
            count++;
        }
        return integers;
    }

    public static Integer[] partialOrderArray(int n) {
        Random rand = new Random();
        Integer[] integers = new Integer[n];
        for (int i = 0; i < n / 4; i++)
            integers[i] = i + 1;
        for (int i = n / 4; i < n * 3 / 4; i++)
            integers[i] = rand.nextInt(n);
        for (int i = n * 3 / 4; i < n; i++)
            integers[i] = i + 1;
        return integers;
    }

    public static Integer[] randomArray(int n)
    {
        Random rand= new Random();
        Integer[] integers = new Integer[n];
        for(int i=0;i<n;i++)
            integers[i]=rand.nextInt(n);
        return integers;
    }

}
