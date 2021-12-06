package edu.neu.coe.info6205.sort.counting;

public class LSDStringSort {

    public static final int ASCII_RANGE = 256;

    /**
     * findMaxLength method returns maximum length of all available strings in an array
     *
     * @param strArr It contains an array of String from which maximum length needs to be found
     * @return int Returns maximum length value
     */
    public static int findMaxLength(Pair[] strArr) {
        int maxLength = strArr[0].getPinyin().length();
        for (Pair str : strArr)
            maxLength = Math.max(maxLength, str.getPinyin().length());
        return maxLength;
    }

    /**
     * charAsciiVal method returns ASCII value of particular character in a String.
     *
     * @param str          String input for which ASCII Value need to be found
     * @param charPosition Character position of which ASCII value needs to be found. If character
     *                     doesn't exist then ASCII value of null i.e. 0 is returned
     * @return int Returns ASCII value
     */
    public static int charAsciiVal(String str, int charPosition) {
        if (charPosition >= str.length()) {
            return 0;
        }
        return str.charAt(charPosition);
    }

    /**
     * charSort method is implementation of LSD sort algorithm at particular character.
     *
     * @param strArr       It contains an array of String on which LSD char sort needs to be performed
     * @param charPosition This is the character position on which sort would be performed
     * @param from         This is the starting index from which sorting operation will begin
     * @param to           This is the ending index up until which sorting operation will be continued
     */
    public static void charSort(Pair[] strArr, int charPosition, int from, int to) {
        int[] count = new int[ASCII_RANGE + 2];
        Pair[] result = new Pair[strArr.length];

        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i].getPinyin(), charPosition);
            count[c + 2]++;
        }

        // transform counts to indices
        for (int r = 1; r < ASCII_RANGE + 2; r++)
            count[r] += count[r - 1];

        // distribute
        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i].getPinyin(), charPosition);
            result[count[c + 1]++] = strArr[i];
        }

        // copy back
        if (to + 1 - from >= 0) System.arraycopy(result, 0, strArr, from, to + 1 - from);
    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     * @param from   This is the starting index from which sorting operation will begin
     * @param to     This is the ending index up until which sorting operation will be continued
     */
    public static void sort(Pair[] strArr, int from, int to) {
        int maxLength = findMaxLength(strArr);
        for (int i = maxLength - 1; i >= 0; i--)
            charSort(strArr, i, from, to);
    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     */
    public static void sort(Pair[] strArr) {
        sort(strArr, 0, strArr.length - 1);
    }
}
