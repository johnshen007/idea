package com.lkj.RadioSort;

import java.util.ArrayList;
import java.util.LinkedList;

/** 基数排序
时间复杂度：每一位都要操作 n 个元素，这些数里面最大有k位，那么时间复杂度为：O(kn)
 空间复杂度：花费10个桶，且这些桶一共放置n个元素，那么空间复杂度就是：O(n+k)，k为桶的个数
 */
public class RadioSort
{
    public int[] radioSort(int[] arr)
    {
        if (arr == null || arr.length <= 1) return arr;
        
        //1、首先，找到最大值，并计算最大值的位数
        int max = arr[0];
        for (int i = 1; i < arr.length ; i++)
        {
            if(arr[i] > max)
                max = arr[i];
        }
        
        int numBit = 1;
        while(max / 10 > 0)
        {
            numBit++;
            max /= 10;
        }
        
        //2、创建10个桶并初始化
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>(10);
        for (int i = 0; i < 10 ; i++)
        {
            bucketList.add(new LinkedList<>());
        }
        
        //3、进行循环，分别依据每一位的大小将元素放入桶里，再取出放回arr
        for (int i = 1; i <= numBit ; i++)
        {
            //先将每一个元素比较 i 位，并将元素放入相应的桶里
            for (int j = 0; j < arr.length ; j++)
            {
                bucketList.get((arr[j] / (int)Math.pow(10 , i-1)) % 10).add(arr[j]);
            }

            //接下来，按顺序将每一个桶里的元素放回arr
            int index = 0;
            for (int j = 0; j < 10 ; j++)
            {
                for (int k = 0; k < bucketList.get(j).size() ; k++)
                {
                    arr[index++] = bucketList.get(j).get(k);
                }
                //重要！记得遍历完一个桶后，将桶的的元素清空，因为后面还要再次将元素放入桶里
                bucketList.get(j).clear();
            }
        }

        return arr;
    }
}
