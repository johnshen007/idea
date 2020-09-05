package com.lkj.CountSort;

/** 计数排序
计数排序的时间复杂度是：O(n+k)，其中k是我们创建的新的数组的长度
 空间复杂度是：O(k)，其中k是我们创建的新的数组的长度

 计数排序不是比较排序，排序的速度快于任何比较排序算法。
 由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），
 这使得计数排序对于数据范围很大的数组，需要大量时间和内存。

 计数排序要求输入的数据必须是有确定范围的整数。
 */
public class CountSort
{
    public int[] countSort(int[] arr)
    {
        if (arr == null || arr.length <= 1) return arr;

        //先求得arr最大值与最小值
        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length ; i++)
        {
            if(arr[i] < min)
                min = arr[i];
            if(arr[i] > max)
                max = arr[i];
        }

        //顶以一个临时数组，数组长度是 max-min+1
        int[] tempArr = new int[max-min+1];

        //遍历arr的每一个元素，将元素作为新数组的下标，每一个tempArr下标对应元素的值代表这个下标在arr数组中出现的次数
        for (int i = 0; i < arr.length ; i++)
        {
            //这里注意，arr元素的值是从 min到max，而tempArr下标从0到max-min+1，
            //因此，我们在给tempArr赋值的时候，注意下标取 arr[i]-min
            tempArr[arr[i]-min]++;
        }

        int index = 0;
        //将新数组统计的值，重新赋予旧的数组
        for (int i = 0; i < tempArr.length ; i++)
        {
            while(tempArr[i] > 0)
            {
                arr[index++] = i + min;//注意，tempArr下标i对应arr中的值应该是 i+min
                tempArr[i]--;
            }
        }

        return arr;
    }
}
