package edu.neu.coe.info6205.sort.counting;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class RandomChineseNamesGeneratorTest {
    @Test
    public void test1(){
        int n=10;
       assertEquals(10,RandomChineseNamesGenerator.generateNames(n).length);
    }

    @Test
    public void test2(){
        int n=0;
        assertEquals(0,RandomChineseNamesGenerator.generateNames(n).length);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void test3(){
        int n=-10;
        assertEquals(0,RandomChineseNamesGenerator.generateNames(n).length);
    }
}
