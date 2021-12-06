package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.counting.Pair;
import edu.neu.coe.info6205.sort.counting.RandomChineseNamesGenerator;
import edu.neu.coe.info6205.sort.counting.Runner;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.ConfigTest;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class InsertionSortMSDTest {
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
    public void sort() {
        int n =10;
        the_array_of_Pair(n);
        InsertionSortMSD.sort(pairArr,0,pairArr.length-1,0);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
    }

    @Test
    public void sort1() {
        int n =1000;
        the_array_of_Pair(n);
        InsertionSortMSD.sort(pairArr,0,pairArr.length-1,0);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
    }

    @Test
    public void sort2() {
        int n =10000;
        the_array_of_Pair(n);
        InsertionSortMSD.sort(pairArr,0,pairArr.length-1,0);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
    }

    @Test
    public void sor3() {
        int n =100000;
        the_array_of_Pair(n);
        InsertionSortMSD.sort(pairArr,0,pairArr.length-1,0);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
    }

    @Test
    public void sort4() {
        int n =1000000;
        the_array_of_Pair(n);
        InsertionSortMSD.sort(pairArr,0,pairArr.length-1,0);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
    }

    @Test (expected =  NullPointerException.class)
    public void testInsertionSortMSD(){
        InsertionSortMSD.sort(null,0,pairArr.length-1,0);
    }

    @Test (expected =  ArrayIndexOutOfBoundsException.class)
    public void testInsertionSortMSD1(){
        int n =10;
        the_array_of_Pair(n);
        InsertionSortMSD.sort(pairArr,0,pairArr.length,0);
    }

}