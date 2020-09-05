package com.lkj.InsertSort;

/** 插入排序
对于有n个元素的数组，插入排序每一轮比较的次数为 1、2、...、n-2、n-1.
 在最差的情况下，整个数组是倒序的，我们每一个元素都需要比较到最前，因此比较的次数是：n(n-1)/2 = (n^2-n)/2，
 最差的时间复杂度是：O(n^2)
 最好的情况下，数组已经排序完毕，每一个数字只需与前面的数字以比较就知道不需要排序，因此时间复杂度是：O(n)
 空间复杂度：O(1)
 */
public class InsertSort<T extends Comparable<T>>
{
    public T[] insertSort(T[] arr)
    {
        if(arr == null || arr.length <= 1)
            return arr;

        /*
        对于插入排序，我们从第二个元素开始，向前比较，寻找该元素插入的位置，知道最后一个元素 arr[arr.length-1]。
        第一个元素没有前一个元素，不需要比较！
         */
        for (int i = 1; i <= arr.length-1 ; i++)
        {
            T current = arr[i];//记录当前位置的元素，不能只记录下标，否则在覆盖的过程中当前位置元素值丢失
            int pre = i-1;//记录前一个位置的下标
            while(pre>=0 && current.compareTo(arr[pre])<0)
            {
                /*
                如果当前位置的元素仍然小于pre位置，我们将 arr[pre+1] 设置为 arr[pre]，
                随后pre-1，继续向前比较，直到不满足两个条件其中一个条件！
                 */
                arr[pre+1] = arr[pre];
                pre--;
            }
            //当pre<0或者 current >arr[pre]的时候，current元素应该插入pre+1位置
            arr[pre+1] = current;
        }
        /**
         * 想象一下整个过程，我们使用current存储i位置值的方法，可以使得我们每次只需将当前位置的值设置为前一个位置，
         * 而不需要每次都交换当前位置与前一个位置的值（交换有3步！）
         * 最后再将找到的插入位置 pre+1 的值设置为arr[current]即可
         */
        return arr;
    }
}
