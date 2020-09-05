package com.lkj.BucketSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/** 桶排序
对于桶排序，时间花费最多的是在 遍历多有桶并对每一个桶进行排序。
 假如排序数组的元素个数为n，均匀分布在个数为m的桶中，那么每个桶中的元素个数为 n/m，
 因此每一个桶使用快排的时间复杂度是 ： (n/m)*log(n/m)，那么m个桶的总时间就是 m*(n/m)*log(n/m) = nlog(n/m)
 那么这一段的时间复杂度是：O(nlog(n/m))。
 而其他段都是花费：O(n) 的时间，因此总的时间是：O(n)+O(nlog(n/m))，当m接近n的时候，log(n/m)=0，那么此时时间复杂度是：O(n)
 总结：桶排序的时间复杂度是：O(n+k),k=n*log(n/m)

 空间复杂度：O(n+k)，其中k为桶的个数，即我们需要k个桶，k个桶里面又存放n个数据，占用的空间是：O(n+k)

 如果相对于同样的N，桶数量M越大，其效率越高，最好的时间复杂度达到O(N)。
 当然桶排序的空间复杂度为O(N+M)，如果输入数据非常庞大，而桶的数量也非常多，则空间代价无疑是昂贵的。

 参考：https://baike.baidu.com/item/%E6%A1%B6%E6%8E%92%E5%BA%8F/4973777?fr=kg_qa
 */
public class BucketSort
{
    public int[] bucketSort(int[] arr)
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

        int elementNum = max - min + 1;//需要存放的可能的元素类型数（同一个类型元素可能有多个，类型指的是元素的值）
        //下面，每个桶存放元素大小的间隔是5，计算需要的桶的个数
        //比如对于数据大小在 25-52 的数据，需要6个组，分别存放 25-29、30-34、35-39、40-44、45-49、50-52的数据
        int bucketNum = 0;
        if(elementNum%5 == 0)
            bucketNum = elementNum/5;
        else
            bucketNum = elementNum/5+1;

        //创建 bucketNum 个桶，桶存放于ArrayList
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>(bucketNum);

        //初始化每一个桶
        for (int i = 0; i < bucketNum ; i++)
        {
            bucketList.add(new LinkedList<Integer>());
        }

        //将元素存储到对应的桶，(下标-min)/5 代表存放的桶的标号
        for (int i = 0; i < arr.length ; i++)
        {
            bucketList.get((arr[i]-min)/5).add(arr[i]);
        }

        //对每一个桶内的元素进行排序
        for (int i = 0; i < bucketNum ; i++)
        {
            Collections.sort(bucketList.get(i));//这种排序的时间复杂度是 nlogn
        }

        int index = 0;
        //最后，将排序后的所有桶的元素全部放回 arr
        for (int i = 0; i < bucketNum ; i++)
        {
            for (int j = 0; j < bucketList.get(i).size(); j++)
            {
                arr[index++] = bucketList.get(i).get(j);
            }
        }

        return arr;
    }
}
