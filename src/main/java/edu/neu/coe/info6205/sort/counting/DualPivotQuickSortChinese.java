package edu.neu.coe.info6205.sort.counting;

//Code taken from https://www.javacodegeeks.com/2013/06/quicksorting-3-way-and-dual-pivot.html

public class DualPivotQuickSortChinese {
    public void sort(Pair[] input) {
        sort(input, 0, input.length - 1);
    }

    private void sort(Pair[] input, int lowIndex, int highIndex) {

        if (highIndex <= lowIndex) return;

        Pair pivot1 = input[lowIndex];
        Pair pivot2 = input[highIndex];

        if (pivot1.compareTo(pivot2) > 0) {
            swap(input, lowIndex, highIndex);
            pivot1 = input[lowIndex];
            pivot2 = input[highIndex];
        } else if (pivot1 == pivot2) {
            while (pivot1 == pivot2 && lowIndex < highIndex) {
                lowIndex++;
                pivot1 = input[lowIndex];
            }
        }

        int i = lowIndex + 1;
        int lt = lowIndex + 1;
        int gt = highIndex - 1;

        while (i <= gt) {

            if (less(input[i], pivot1)) {
                swap(input, i++, lt++);
            } else if (less(pivot2, input[i])) {
                swap(input, i, gt--);
            } else {
                i++;
            }

        }

        swap(input, lowIndex, --lt);
        swap(input, highIndex, ++gt);

        sort(input, lowIndex, lt - 1);
        sort(input, lt + 1, gt - 1);
        sort(input, gt + 1, highIndex);

    }

    Boolean less(Pair v, Pair w) {
        return v.compareTo(w) < 0;
    }

    void swap(Pair[] arr, int i, int j) {
        Pair temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
