package com.lkj.MergeSort;

/** 归并排序
 对于有n个元素的数组，归并排序同样会递归 logn 次，每一次，其实都要对上一层排序的 right-left+1 个结果进行合并，
 算上这一层所有的递归，每一次递归其实合并merge过程要对比 n个元素。
 那么，归并排序的时间复杂度是：O(nlogn)

 归并的空间复杂度就是那个临时的数组和递归时压入栈的数据占用的空间：n + logn；所以空间复杂度为: O(n)
 */
public class MergeSort<T extends Comparable<T>>
{
    public T[] mergeSort(T[] arr)
    {
        if(arr == null || arr.length <= 1)
            return arr;

        mergeSort(arr , 0 , arr.length-1);
        return arr;
    }

    //对数组arr从下标 left 到 right 处的元素进行排序
    private void mergeSort(T[] arr , int left , int right)
    {
        /*
        当 left=right 的时候，表示只有一个元素，不需要进行合并，那么我们对 left=right 的情况就不进行操作，直接 return 结束递归即可。
        这里也可能出现 left>right 的情况，这种情况直接结束递归即可！
        其实对于 left>=right 的情况，我们不进行处理，也不会进入递归，会自动结束
       对于 left<right 的情况进行递归操作，这样到达 left=right 的上一层递归，自然会合并只有一个元素的情况。
         */
        if(left >= right)
            return;//只有一个元素直接结束递归，表示不需要合并（注意必须结束递归！）

        if(left < right)
        {
            int mid = left + (right-left)/2;
            mergeSort(arr , left , mid);
            mergeSort(arr , mid+1 , right);
            //在获取 left到mid 与 mid+1到right 段的排序结果后，我们需要将这两段也进行合并！
            merge(arr , left , mid , right);
        }
    }
    //对left到mid 与 mid到right 这两段已经排序的段，再进行合并排序
    private void merge(T[] arr , int left , int mid , int right)
    {
        //注意，这里不能直接从Object转换，而要从Comparable转换为T
        T[] newArr = (T[]) new Comparable[right - left+1];//创建一个新的数组用于暂存合并结果

        int leftStartIndex = left ;//左边段开始的坐标
        int rightStartIndex = mid+1;//右边段开始的坐标
        int newArrIndex = 0;//新数组开始的坐标

        //左右两段都没有遍历完
        while (leftStartIndex <= mid && rightStartIndex <= right )
        {
            //找到较小的一个，将其值设置到新数组，并将新数组与这一段的下标都+1
            if(arr[leftStartIndex].compareTo(arr[rightStartIndex]) < 0)
                newArr[newArrIndex++] = arr[leftStartIndex++];
            else
                newArr[newArrIndex++] = arr[rightStartIndex++];
        }

        //当左边段还没有完，但是右边段完了，上面跳出循环的时候，leftStartIndex <= mid，且rightStartIndex > right
        while(leftStartIndex <= mid)
            newArr[newArrIndex++] = arr[leftStartIndex++];
        //右边段同理
        while (rightStartIndex <= right)
            newArr[newArrIndex++] = arr[rightStartIndex++];

        //最后记得将新数组的元素放入原来数组的相应位置
        for (int i = 0; i < newArr.length ; i++)
        {
            arr[left++] = newArr[i];
        }
    }
}
