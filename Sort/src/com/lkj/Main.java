package com.lkj;

import com.lkj.BubbleSort.BubbleSort;
import com.lkj.BubbleSort.BubbleSortOpt;
import com.lkj.BucketSort.BucketSort;
import com.lkj.CountSort.CountSort;
import com.lkj.HeapSort.HeapSort;
import com.lkj.InsertSort.InsertSort;
import com.lkj.MergeSort.MergeSort;
import com.lkj.QuickSort.QuickSort;
import com.lkj.RadioSort.RadioSort;
import com.lkj.SelectSort.SelectSort;
import com.lkj.ShellSort.ShellSort;
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
