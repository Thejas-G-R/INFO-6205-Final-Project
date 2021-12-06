package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.counting.huskySort.sort.BaseHelper;
import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySortUtils.Coding;
import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySortUtils.HuskyCoder;
import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.sort.counting.huskySort.util.PrivateMethodInvoker;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PureHuskySortTest {

    private final BaseHelper<String> helper = new BaseHelper<>("dummy helper");

    @Test
    public void testSortString1() {
        String[] xs = {"Hello", "Goodbye", "Ciao", "Willkommen"};
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.unicodeCoder, false, false);
        sorter.sort(xs);
        assertTrue("sorted", helper.sorted(xs));
    }

    @Test
    public void testSortString2() {
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        final int N = 1000;
        helper.init(N);
        final String[] xs = helper.random(String.class, r -> r.nextLong() + "");
        sorter.sort(xs);
        assertTrue("sorted", helper.sorted(xs));
    }

    @Test
    public void testSortString3() {
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        final int N = 1000;
        helper.init(N);
        final String[] xs = helper.random(String.class, r -> {
            int x = r.nextInt(1000000000);
            final BigInteger b = BigInteger.valueOf(x).multiply(BigInteger.valueOf(1000000));
            return b.toString();
        });
        sorter.sort(xs);
        assertTrue("sorted", helper.sorted(xs));
    }

    @Test
    public void testSortString4() {
        String[] xs = {"Hello", "Goodbye", "Ciao", "Willkommen"};
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        sorter.sort(xs);
        assertTrue("sorted", helper.sorted(xs));
    }

    @Test
    public void testSortString5() {
        String[] xs = {"Hello", "Goodbye", "Ciao", "Welcome"};
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        sorter.sort(xs);
        assertTrue("sorted", helper.sorted(xs));
    }

    @Test
    public void testSortString6() {
        // order:       453922  252568   145313   673679   181452   31014   988329   659494    923995   890721   744769   293165   520163   199395   669978   765753
        String[] xs = {"刘持平", "洪文胜", "樊辉辉", "苏会敏", "高民政", "曹玉德", "袁继鹏", "舒冬梅", "杨腊香", "许凤山", "王广风", "黄锡鸿", "罗庆富", "顾芳芳", "宋雪光", "王诗卉"};
        PureHuskySort<String> sorter = new PureHuskySort<>(HuskyCoderFactory.chineseEncoder, false, false);
        sorter.sort(xs);
        System.out.println(Arrays.toString(xs));
        // order:           31014   145313   181452   199395   252568   293165   453922  520163   659494    669978   673679  744769   765753   890721   923995    988329
        String[] sorted = {"曹玉德", "樊辉辉", "高民政", "顾芳芳", "洪文胜", "黄锡鸿", "刘持平", "罗庆富", "舒冬梅", "宋雪光", "苏会敏", "王广风", "王诗卉", "许凤山", "杨腊香", "袁继鹏"};
        assertArrayEquals(sorted, xs);
//        assertTrue("sorted", helper.sorted(xs));
    }

    @Test
    public void testFloorLg() {
        PrivateMethodInvoker privateMethodInvoker = new PrivateMethodInvoker(PureHuskySort.class);
        assertEquals(Integer.valueOf(1), privateMethodInvoker.invokePrivate("floor_lg", 3));
        assertEquals(Integer.valueOf(2), privateMethodInvoker.invokePrivate("floor_lg", 5));
    }

    private Pair[] pairArr;
    private Pair[] expected;
    public void the_array_of_Pair(int length) {
        String[] string2 = RandomChineseNamesGenerator.generateNames((int)(length));
        Pair[] pairs = Runner.getPairs(string2);
        pairArr = new Pair[pairs.length];
        expected = new Pair[pairs.length];
        System.arraycopy(pairs,0,pairArr,0,pairs.length);
        System.arraycopy(pairs,0,expected,0, pairs.length);
    }

    @Test
    public void testChineseSort(){
        int n =10;
        the_array_of_Pair(n);
        ArrayList<String> chineseNamesArrList = new ArrayList<>();
        ArrayList<String> pinyinNamesArrList = new ArrayList<>();
        for (Pair pair : pairArr) {
            chineseNamesArrList.add(pair.getChineseName());
            pinyinNamesArrList.add(pair.getPinyin());
        }
        String[] pinyinNames = new String[chineseNamesArrList.size()];
        String[] chineseNames  = new String[chineseNamesArrList.size()];
        pinyinNames = pinyinNamesArrList.toArray(pinyinNames);
        chineseNames = chineseNamesArrList.toArray(chineseNames);

        PureHuskySort<String> sorter = new PureHuskySort<>();
        sorter.sort( chineseNames, pinyinNames, true);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i].getPinyin(),pinyinNames[i]);
        }
    }

    @Test
    public void testChineseSort1(){
        int n =100;
        the_array_of_Pair(n);
        ArrayList<String> chineseNamesArrList = new ArrayList<>();
        ArrayList<String> pinyinNamesArrList = new ArrayList<>();
        for (Pair pair : pairArr) {
            chineseNamesArrList.add(pair.getChineseName());
            pinyinNamesArrList.add(pair.getPinyin());
        }
        String[] pinyinNames = new String[chineseNamesArrList.size()];
        String[] chineseNames  = new String[chineseNamesArrList.size()];
        pinyinNames = pinyinNamesArrList.toArray(pinyinNames);
        chineseNames = chineseNamesArrList.toArray(chineseNames);

        PureHuskySort<String> sorter = new PureHuskySort<>();
        sorter.sort( chineseNames, pinyinNames, true);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i].getPinyin(),pinyinNames[i]);
        }
    }

    @Test
    public void testChineseSort2(){
        int n =1000;
        the_array_of_Pair(n);
        ArrayList<String> chineseNamesArrList = new ArrayList<>();
        ArrayList<String> pinyinNamesArrList = new ArrayList<>();
        for (Pair pair : pairArr) {
            chineseNamesArrList.add(pair.getChineseName());
            pinyinNamesArrList.add(pair.getPinyin());
        }
        String[] pinyinNames = new String[chineseNamesArrList.size()];
        String[] chineseNames  = new String[chineseNamesArrList.size()];
        pinyinNames = pinyinNamesArrList.toArray(pinyinNames);
        chineseNames = chineseNamesArrList.toArray(chineseNames);

        PureHuskySort<String> sorter = new PureHuskySort<>();
        sorter.sort( chineseNames, pinyinNames, true);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i].getPinyin(),pinyinNames[i]);
        }
    }

    @Test
    public void testChineseSort3(){
        int n =10000;
        the_array_of_Pair(n);
        ArrayList<String> chineseNamesArrList = new ArrayList<>();
        ArrayList<String> pinyinNamesArrList = new ArrayList<>();
        for (Pair pair : pairArr) {
            chineseNamesArrList.add(pair.getChineseName());
            pinyinNamesArrList.add(pair.getPinyin());
        }
        String[] pinyinNames = new String[chineseNamesArrList.size()];
        String[] chineseNames  = new String[chineseNamesArrList.size()];
        pinyinNames = pinyinNamesArrList.toArray(pinyinNames);
        chineseNames = chineseNamesArrList.toArray(chineseNames);

        PureHuskySort<String> sorter = new PureHuskySort<>();
        sorter.sort( chineseNames, pinyinNames, true);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i].getPinyin(),pinyinNames[i]);
        }
    }

    @Test (expected = NullPointerException.class)
    public void testChineseSort4(){

        PureHuskySort<String> sorter = new PureHuskySort<>();
        sorter.sort( null, null, true);
    }
}
