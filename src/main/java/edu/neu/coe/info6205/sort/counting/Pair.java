package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySortUtils.HuskySortable;

public class Pair implements Comparable<Pair> {
    String chineseName;
    String pinyin;
    private long huskycode = 0L;

    public Pair(String chineseName, String pinyin) {
        this.chineseName = chineseName;
        this.pinyin = pinyin;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(Pair T) {
        return pinyin.compareTo(T.getPinyin());
    }

//    @Override
//    public long huskyCode() {
//        if (huskycode == 0L)
//            huskycode = HuskyCoderFactory.asciiToLong(pinyin);
//        return huskycode;
//    }
}
