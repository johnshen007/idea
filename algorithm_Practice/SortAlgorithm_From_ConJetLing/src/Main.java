import BubbleSort.BubbleSort;
import BubbleSort.BubbleSortOpt;
import BucketSort.BucketSort;
import CountSort.CountSort;
import HeapSort.HeapSort;
import InsertSort.InsertSort;
import MergeSort.MergeSort;
import QuickSort.QuickSort;
import RadioSort.RadioSort;
import SelectSort.SelectSort;
import ShellSort.ShellSort;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {47,36,28,49,51,25,31,44,51,38,39,26,48,42,41,33,32,46,27};
        Integer[] arr1 = {7,8,8,8,8};

        new RadioSort().radioSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
