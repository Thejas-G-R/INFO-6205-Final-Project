package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Config;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MSDStringSortTest {
//String[] input = {"刘持平", "洪文胜", "樊辉辉", "苏会敏", "高民政", "曹玉德", "袁继鹏", "舒冬梅", "杨腊香", "许凤山", "王广风", "黄锡鸿", "罗庆富", "顾芳芳", "宋雪光", "王诗卉"};
//    String[] expected = {"曹玉德", "樊辉辉", "高民政", "顾芳芳", "洪文胜", "黄锡鸿", "刘持平", "罗庆富", "舒冬梅", "宋雪光", "苏会敏", "王广风", "王诗卉", "许凤山", "杨腊香", "袁继鹏"};
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
        MSDStringSort.sort(pairArr);
    Arrays.sort(expected);
    for (int i = 0; i < pairArr.length; i++) {
        assertEquals(expected[i],pairArr[i]);
    }
    }

    @Test
    public void sort1() {
        int n =1000;
        the_array_of_Pair(n);
        MSDStringSort.sort(pairArr);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
    }

    @Test
    public void sort2() {
        int n =10000;
        the_array_of_Pair(n);
        MSDStringSort.sort(pairArr);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
    }

    @Test
    public void sor3() {
        int n =100000;
        the_array_of_Pair(n);
        MSDStringSort.sort(pairArr);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
    }

    @Test
    public void sort4() {
        int n =1000000;
        the_array_of_Pair(n);
        MSDStringSort.sort(pairArr);
        Arrays.sort(expected);
        for (int i = 0; i < pairArr.length; i++) {
            assertEquals(expected[i],pairArr[i]);
        }
    }

    @Test (expected =  NullPointerException.class)
    public void testLSDStringSort5(){
        MSDStringSort.sort(null);
    }

}