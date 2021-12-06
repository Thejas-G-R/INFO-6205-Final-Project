package edu.neu.coe.info6205.sort.counting;

import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class DualPivotQuickSortChineseTest {
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
    public void sort1() {
        int n =10;
        the_array_of_Pair(n);
     new DualPivotQuickSortChinese().sort(pairArr);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i].getPinyin(),pairArr[i].getPinyin());
        }
    }

    @Test
    public void sort2() {
        int n =1000;
        the_array_of_Pair(n);
        new DualPivotQuickSortChinese().sort(pairArr);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i].getPinyin(),pairArr[i].getPinyin());
        }
    }

    @Test
    public void sort3() {
        int n =10000;
        the_array_of_Pair(n);
        new DualPivotQuickSortChinese().sort(pairArr);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i].getPinyin(),pairArr[i].getPinyin());
        }
    }

    @Test
    public void sort4() {
        int n =100000;
        the_array_of_Pair(n);
        new DualPivotQuickSortChinese().sort(pairArr);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i].getPinyin(),pairArr[i].getPinyin());
        }
    }

    @Test (expected =  NullPointerException.class)
    public void testDualPivot(){
        new DualPivotQuickSortChinese().sort(null);
    }
}
