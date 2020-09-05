package com.lkj.BubbleSort;

/** 冒泡排序
冒泡排序每一轮比较的次数是：n-1、n-2、...、1，因此，对于长度为n的数组，总的比较次数是：n(n-1)/2 = (n^2-n)/2，
 因此，在最坏的情况下，每一轮都需要进行交换，最坏时间复杂度是：O(n^2)；
 在最好的情况下，我们在优化冒泡排序的时候，加入标记吗，第一轮结束接知道数组已经排序，不需要继续排序，最好的时间复杂度是：O(n)；
 平均时间复杂度是：O(n^2)，空间复杂度是：O(1)，没有用到多余的空间。
 冒泡排序是比较排序，它是一种稳定的排序！
 */
public class BubbleSort<T extends Comparable<T>>
{
    public T[] bubbleSort(T[] arr)
    {
        if(arr == null || arr.length <= 1)
            return arr;

        //外部循环，比较的次数是 arr.length-1
        for (int i = 0; i < arr.length-1 ; i++)
        {
            //内部循环，比较到 arr.length-1-i位置的元素，即 j+1 的最大值为 arr.length-1-i
            //此时比较次数为 ：arr.length-1-i
            for (int j = 0; j < arr.length-1-i ; j++)
            {
                if(arr[j].compareTo(arr[j+1]) > 0)
                {
                    T temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
}
