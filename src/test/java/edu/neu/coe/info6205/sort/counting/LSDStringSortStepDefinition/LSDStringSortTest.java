package edu.neu.coe.info6205.sort.counting.LSDStringSortStepDefinition;


import edu.neu.coe.info6205.sort.counting.ChineseToPinyinConverter;
import edu.neu.coe.info6205.sort.counting.LSDStringSort;
import edu.neu.coe.info6205.sort.counting.Pair;
import edu.neu.coe.info6205.sort.counting.RandomChineseNamesGenerator;
import edu.neu.coe.info6205.sort.counting.Runner;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LSDStringSortTest {

    private LSDStringSort ls;
    private Pair[] pairArr;
    private Pair[] expected;

    @Before
    public void init() {
        ls = new LSDStringSort();
    }


    public void the_array_of_Pair(int length) {
        String[] string2 = RandomChineseNamesGenerator.generateNames((int)(length));
       Pair[] pairs = Runner.getPairs(string2);
        pairArr = new Pair[pairs.length];
        expected = new Pair[pairs.length];
        System.arraycopy(pairs,0,pairArr,0,pairs.length);
        System.arraycopy(pairs,0,expected,0, pairs.length);
    }
    @Test
    public void testLSDStringSort(){
        int n = 10;
        the_array_of_Pair(n);
        ls.sort(pairArr,0,pairArr.length-1);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
        stringSortingInvarianceCheck(0, pairArr.length-1);
    }

    @Test
    public void testLSDStringSort1(){
        int n = 100;
        the_array_of_Pair(n);
        ls.sort(pairArr,0,pairArr.length-1);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
        stringSortingInvarianceCheck(0, pairArr.length-1);
    }

    @Test
    public void testLSDStringSort2(){
        int n = 10000;
        the_array_of_Pair(n);
        ls.sort(pairArr,0,pairArr.length-1);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
        stringSortingInvarianceCheck(0, pairArr.length-1);
    }

    @Test
    public void testLSDStringSort3(){
        int n = 100000;
        the_array_of_Pair(n);
        ls.sort(pairArr,0,pairArr.length-1);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
        stringSortingInvarianceCheck(0, pairArr.length-1);
    }
    @Test
    public void testLSDStringSort4(){
//
        Pair[] tempPairArr =  {new Pair("刘持平","liuchiping"),new Pair("刘持平","liuchiping"),
                new Pair("刘持平","liuchiping"),new Pair("刘持平","liuchiping"),
                new Pair("刘持平","liuchiping"),new Pair("刘持平","liuchiping")};
        pairArr = new Pair[tempPairArr.length];
        expected = new Pair[tempPairArr.length];
        System.arraycopy(tempPairArr,0,pairArr,0,tempPairArr.length);
        System.arraycopy(tempPairArr,0,expected,0,tempPairArr.length);
        ls.sort(pairArr,0,pairArr.length-1);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
        stringSortingInvarianceCheck(0, pairArr.length-1);
    }

    @Test (expected =  NullPointerException.class)
    public void testLSDStringSort5(){

        ls.sort(null,0,pairArr.length-1);
    }
    @Test (expected =  ArrayIndexOutOfBoundsException.class)
    public void testLSDStringSort6(){
        int n = 1;
        the_array_of_Pair(n);
        ls.sort(pairArr,0,pairArr.length);
    }


    public boolean stringSortingInvarianceCheck(int rangeStart, int rangeEnd) {
        for (int i = rangeStart + 1; i <= rangeEnd; i++) {
            if (pairArr[i - 1].compareTo(pairArr[i]) > 0) return false;
        }
        return true;
    }

}
