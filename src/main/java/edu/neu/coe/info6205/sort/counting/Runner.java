package edu.neu.coe.info6205.sort.counting;

import com.ibm.icu.text.Collator;
import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.sort.counting.huskySort.sort.simple.TimSort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.CollationKey;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Runner {

    public static void BenchmarkHuskySort(String[] shuffledChineseNames){
        String[] shuffledPinyinNames = new String[shuffledChineseNames.length];
        for(int i = 0; i < shuffledChineseNames.length; i++){
            shuffledPinyinNames[i] = ChineseToPinyinConverter.getPinyinString(shuffledChineseNames[i]);
        }
        PureHuskySort<String> sorter = new PureHuskySort<>();
        sorter.sort(shuffledChineseNames, shuffledPinyinNames, true);
    }

    public static void BenchmarkDualPivotQuickSort(String[] shuffledChineseNames){
        (new DualPivotQuickSortChinese()).sort(getPairs(shuffledChineseNames));
    }

    public static void BenchmarkMSDRadixSort(String[] shuffledChineseNames){
        MSDStringSort.sort(getPairs(shuffledChineseNames));
    }

    public static void BenchmarkTimSort(String[] shuffledChineseNames){
        Pair[] pairs = getPairs(shuffledChineseNames);
        new TimSort<Pair>().sort(pairs,0, pairs.length);
    }

    public static void BenchmarkLSDRadixSort(String[] shuffledChineseNames){
        LSDStringSort.sort(getPairs(shuffledChineseNames));
    }

    public static Pair[] getPairs(String[] shuffledChineseNames){
        Pair[] pairs = new Pair[shuffledChineseNames.length];
        for(int i = 0; i < shuffledChineseNames.length; i++){
            pairs[i] = new Pair(shuffledChineseNames[i],ChineseToPinyinConverter.getPinyinString(shuffledChineseNames[i]));
        }
        return pairs;
    }

    public static String[] getShuffledChineseWords(float multiplier){
        //String[] xs = {"刘持平", "洪文胜", "樊辉辉", "苏会敏", "高民政", "曹玉德", "袁继鹏", "舒冬梅", "杨腊香", "许凤山", "王广风", "黄锡鸿", "罗庆富", "顾芳芳", "宋雪光", "王诗卉"};
        //return xs;
        return RandomChineseNamesGenerator.generateNames((int)(1000000 * multiplier));
    }

    public static void main(String args[]) {

        for (float multiplier = 0.25f; multiplier < 8; multiplier *= 2){
            System.out.println((int)(1000000 * multiplier) + "\n");
            runBenchmarks(multiplier);
        }

    }

    public static void runBenchmarks(float multiplier) {

        Consumer<String[]> consumerOfFunction = (t) -> BenchmarkHuskySort(t);
        Supplier<String[]> supplier = () -> getShuffledChineseWords(multiplier);
        Benchmark_Timer<String[]> test = new Benchmark_Timer<String[]>("Husky Sort: ", consumerOfFunction);
        double meantimetaken = test.runFromSupplier(supplier, 20);
        System.out.println("time: " + meantimetaken);


        consumerOfFunction = (t) -> BenchmarkMSDRadixSort(t);
        supplier = () -> getShuffledChineseWords(multiplier);
        test = new Benchmark_Timer<String[]>("MSD Radix Sort: ", consumerOfFunction);
        meantimetaken = test.runFromSupplier(supplier, 20);
        System.out.println("time: " + meantimetaken);


        consumerOfFunction = (t) -> BenchmarkLSDRadixSort(t);
        supplier = () -> getShuffledChineseWords(multiplier);
        test = new Benchmark_Timer<String[]>("LSD Radix Sort: ", consumerOfFunction);
        meantimetaken = test.runFromSupplier(supplier, 20);
        System.out.println("time: " + meantimetaken);


        consumerOfFunction = (t) -> BenchmarkDualPivotQuickSort(t);
        supplier = () -> getShuffledChineseWords(multiplier);
        test = new Benchmark_Timer<String[]>("Dual Pivot Quick Sort: ", consumerOfFunction);
        meantimetaken = test.runFromSupplier(supplier, 20);
        System.out.println("time: " + meantimetaken);


        consumerOfFunction = (t) -> BenchmarkTimSort(t);
        supplier = () -> getShuffledChineseWords(multiplier);
        test = new Benchmark_Timer<String[]>("Tim    Sort: ", consumerOfFunction);
        meantimetaken = test.runFromSupplier(supplier, 20);
        System.out.println("time: " + meantimetaken);

    }
        





//      String[] shuffledChineseNamesPinyin = new String[shuffledChineseNames.length];

        //for generating pairs inside for loop
        //shuffledChineseNamesPinyin[i] = "" + collator.getCollationKey(shuffledChineseNames[i]).toByteArray();
        //shuffledChineseNamesLong[i] = Long.parseLong(String.valueOf(collator.getCollationKey(shuffledChineseNames[i]).toByteArray()));



        //Boolean x = ChineseToPinyinConverter.getPinyinString("艾涵").equals(ChineseToPinyinConverter.getPinyinString("艾晗"));

        //Use this for writing test cases
//        List<String> test = Arrays.asList(shuffledChineseNames);
//        List<String> result = sortchinese(test);
//
//        try {
//            File file = new File(
//                    "/Users/vikasshastry/IdeaProjects/INFO6205/src/main/java/edu/neu/coe/info6205/sort/counting/test.txt");
//
//            FileWriter yourWriter = new FileWriter(file);
//            for (String n : result)
//                yourWriter.write(n + "\n");
//            yourWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //for fixing MSDString sort problem
//        String[] chinese = { "艾涵", "艾寒松", "艾晗"};
//        String[] pinyin = new String[3];
//        Pair[] pair = new Pair[3];
////        for(int i = 0; i < chinese.length; i++){
////            pinyin[i] = ChineseToPinyinConverter.getPinyinString(chinese[i]);
////            pair[i] = new Pair(chinese[i],pinyin[i]);
////        }
//
//        pair[0] = new Pair("a","aihan");
//        pair[1] = new Pair("b","aihansong");
//        pair[2] = new Pair("c","aihan");
//
//        MSDStringSort.sort(pair);
//
//        try {
//            File file = new File(
//                    "/Users/vikasshastry/IdeaProjects/INFO6205/src/main/java/edu/neu/coe/info6205/sort/counting/profSortResult.txt");
//
//            FileWriter yourWriter = new FileWriter(file);
//            for (Pair n : pair)
//                yourWriter.write(n.pinyin + "\t" + "\n");
//            yourWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        //Collator collator = Collator.getInstance(Locale.SIMPLIFIED_CHINESE);


        //String[] shuffledChineseNamesLongString = new String[shuffledChineseNames.length];

//        Long maxVal = 0L;
//
//        for(int i = 0; i < shuffledChineseNames.length; i++){
//            shuffledChineseNamesLongString[i] = "" + collator.getCollationKey(shuffledChineseNames[i]).;
//                    //(ByteBuffer.wrap((collator.getCollationKey(shuffledChineseNames[i])).toByteArray())).getLong() ;
//            //maxVal = Math.max(maxVal, shuffledChineseNamesLong[i]);
//        }

//        DecimalFormat df = new DecimalFormat(maxVal.toString());
//
//        for(int i = 0; i < shuffledChineseNames.length; i++){
//            shuffledChineseNamesLongString[i] = df.format(shuffledChineseNamesLong[i]);
//        }

        //String[] sortedChineseNames = SortedChineseNamesGenerator.generateNames(1000000);

        //String[] sortedChineseNamesPinyin = new String[1000000];





        //Collections.sort(Arrays.asList(shuffledChineseNamesPinyin));

//        try {
//            File file = new File(
//                    "/Users/vikasshastry/IdeaProjects/INFO6205/src/main/java/edu/neu/coe/info6205/sort/counting/mySortResult.txt");
//
//            FileWriter myWriter = new FileWriter(file);
//            for (String n : shuffledChineseNamesPinyin)
//                myWriter.write(n + "\n");
//            myWriter.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //MSDStringSort.sort(pairs);
        //LSDStringSort.sort(pairs);

        //verifying if the sorting is correct
//        for(int i = 0; i < pairs.length; i++){
//            if(!pairs[i].pinyin.equals(shuffledChineseNamesPinyin[i])){
//                System.out.println("Not the same");
//                break;
//            }
//        }

//        try {
//            File file = new File(
//                    "/Users/vikasshastry/IdeaProjects/INFO6205/src/main/java/edu/neu/coe/info6205/sort/counting/profSortResult.txt");
//
//            FileWriter yourWriter = new FileWriter(file);
//            for (Pair n : pairs)
//                yourWriter.write(n.pinyin + "\t" + n.chineseName + "\n");
//            yourWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }





        //MSDStringSort.sort(shuffledChineseNamesPinyin);
        //System.out.println(shuffledChineseNamesPinyin);

//        try {
//            File file = new File(
//                    "/Users/vikasshastry/IdeaProjects/INFO6205/src/main/java/edu/neu/coe/info6205/sort/counting/mySortResult.txt");
//
//            FileWriter myWriter = new FileWriter(file);
////            for (String n : shuffledChineseNamesPinyin)
////                myWriter.write(n + "\n");
////            myWriter.close();
//
//            file = new File(
//                    "/Users/vikasshastry/IdeaProjects/INFO6205/src/main/java/edu/neu/coe/info6205/sort/counting/profSortResult.txt");
//
//            FileWriter yourWriter = new FileWriter(file);
//            for (String n : sortedChineseNamesPinyin)
//                yourWriter.write(n + "\n");
//            yourWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//    }

//    public static List<String> sortchinese(List<String> list)
//    {
//        Collator spCollator = Collator.getInstance(Locale.CHINESE);
//        list.sort(spCollator);
//        return list;
//    }

}
