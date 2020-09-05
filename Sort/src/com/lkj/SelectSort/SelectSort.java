package com.lkj.SelectSort;

/** 选择排序
无论什么时候，当数组长度为n时，选择排序需要对比的次数为 n-1、n-2、...、1，
 因此，选择排序时间复杂度在最好，最差，平均情况下，都为：O(n^2)
 空间复杂度是：O(1)，没有占用额外的空间
 */
public class SelectSort<T extends Comparable<T>>
{
    public T[] selectSort(T[] arr)
    {
        if(arr == null || arr.length<=1)
            return arr;

        /*
        对于 arr.length 个数字，需要选择 arr.length-1 次才能完成排序，
        那么i从 0-arr.length-2，即i < arr.length-1
         */
        for (int i = 0; i < arr.length-1 ; i++)
        {
            //将这一轮选择最小值的初始值设置为arr[i]，这一轮选择也是从 arr[i]开始的
            int min = i;
            /*
            内循环，从i比较到 arr.length-1，其中i的最大值为 arr.length-2
             */
            for (int j = i; j < arr.length ; j++)
            {
                if(arr[j].compareTo(arr[min]) < 0)
                    min = j;
            }
            //找到这一轮的最小值后，将最小值与arr[i]位置互换
            T temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}
