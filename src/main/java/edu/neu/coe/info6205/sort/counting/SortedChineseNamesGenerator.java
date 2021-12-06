package edu.neu.coe.info6205.sort.counting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SortedChineseNamesGenerator {

    public static void main(String args[]){
        String[] temp =  generateNames(1000000);

    }

    public static String[] generateNames(int size){
        int i = 0;
        String[] randomChineseNames = new String[size];

        File file = new File(
                "/Users/vikasshastry/IdeaProjects/INFO6205/src/main/java/edu/neu/coe/info6205/sort/counting/sortedChinese.txt");
        try {
            BufferedReader br
                    = new BufferedReader(new FileReader(file));
            String name;

            while(i<size){

                name = br.readLine();
                if(name == null){
                    br = new BufferedReader(new FileReader(file));
                    name = br.readLine();
                }

                randomChineseNames[i] = name;
                i++;
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return randomChineseNames;

    }

}
