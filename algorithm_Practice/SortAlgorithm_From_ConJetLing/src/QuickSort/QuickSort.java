package QuickSort;
/** 快速排序
 对于有n个元素的数组，最好的情况是每一次区间都刚刚好对半分，那么一个要分 logn 次区间，
 对于第一次分的区间，需要进行 (n-1) 次对比；对于第二次分的区间，需要进行（n-3）次对比....
 对于第 logn 次分的区间，只需要1次对比
 那么实际上需要对比：(1+(n-1))logn / 2 = nlogn/2次，即最好的时间复杂度是：O(nlogn)

 最差的情况下，pivot右边的元素逐次增大或者减小，如：5,4,3,2,1 ，这时需要花费区间的次数是 （n-1），
 而每一次对比的次数是：(n-1)、(n-2)、...、1，总的对比次数是：n(n-1)/2，时间复杂度是：O(n^2)

空间复杂度：O(logn)，递归logn次占用的栈空间
 */
public class QuickSort<T extends Comparable<T>>
{
    public T[] quickSort(T[] arr)
    {
        if (arr == null || arr.length <= 1) return arr;

        quickSort(arr , 0 ,arr.length-1);
        return arr;
    }

    //用于对数组 arr 从left到right 区间进行快速排序的方法
    private void quickSort(T[] arr , int left , int right)
    {
        /** 最后可能出现只有一个元素 left=right，或者有2个元素，再次进行递归，下一轮就会出现 left>right的情况
        如对 3,2,1,4 序列进行排序，假设取mid=3，分出来，左区间是 2,1、右区间是 3.
         此时，右区间只有一个元素，停止递归；对于左区间，先从1,2中选取mid，然后进行下一轮的递归，
         不管mid选取1还是2，总有一个区间中 left=right，只有一个元素，另一个区间 left>right，对于left>right，也需要结束递归。
         因此，存在left=right与left>right的情况！！

         因此，对于left>=right，我们手动结束递归，对于left<right。进行递归快排。
         其实也可以不对left>=right处理，我们只对left<right进行处理，这样遇到 left>=right也不会进行递归，而是自然结束
         */
        if(left >= right)
            return;//这里也可以不手动结束
        //left只可能
        if(left<right)
        {//找到中轴元素下标，并将比中轴元素大的元素放在其右边，比中轴元素小的放在其左边
            int mid = partition(arr , left , right);
            quickSort(arr , left , mid-1);
            quickSort(arr , mid+1 , right);
        }
    }

    //确定区间 left到right 的中轴元素，比中轴元素大的元素放在其右边，比中轴元素小的放在其左边
    private int partition(T[] arr , int left , int right)
    {
        T pivot = arr[left];//去区间左边第一个元素为中轴元素
        //接下来，从left+1开始，知道right，进行元素的移换
        int start = left+1;
        int end = right;
        /**
        分析下面为什么不会出现 start=end 的情况。
         对于一个序列 ：pivot，1,1,2,2,2（0,1,2,3,4,5） ，我们用1代表小于等于pivot，用2代表大于pivot，对下面第一个循环，
         start会增长到 第一个2的时候停止，此时 start=3(下标)，end会递减到第二个1，此时end=2（下标），
         也就是无论如何 start都会增长到第一个大于pivot才会停止循环，end都会递减到第一个小于pivot的元素，才会停止循环，
         即start不会等于end，我们只需要判断 start>end ，此时就可以结束外循环！

         此时end在最后一个小于等于pivot的位置，将pivot与end位置互换即可！

         特殊情况：
         pivot,1,1,1,1(0,1,2,3,4)：start初始值为 1，增加到start=5，因为end初始值为4，且end不会变化，那么此时start>end；
         pivot,2,2,2,2(0,1,2,3,4)：start初始值为 1，不变，因为end初始值为4，end减少到0，那么此时start>end，同样直接结束；

         结论：start永远不会等于end，最后的结束情况就是 start>end

         为什么 start 与 end 在相等的时候还需要移动？
         这时因为如果在 start=end 的时候就停止，我们不知道在start=end 的位置值是大于pivot还是小于pivot，
         有可能是 start 由于 start=end 位置值大于pivot停止下来，而end随后移动到这里；
         也有可能是end由于 start=end 位置值小于pivot停止下来，而start随后移动到这里。
         因此我们在 start=end 的时候，还要让 start或者end 根据 start=end 位置的值进行移动，这样到 start>end
         的时候，start一定移动到大序列的第一个位置，end一定移动到小序列的最后一个位置。
         */
        while(true)
        {
            //从左到右遍历，直到找到一个大于等于中轴元素 pivot 的元素，停止遍历
            while (start<=end && arr[start].compareTo(pivot) <= 0)
                start++;
            //从右到左遍历，知道找到一个小于于中轴元素 pivot 的元素，停止遍历
            while (start<=end && arr[end].compareTo(pivot) > 0)
                end--;

            //当出现 start>end 的时候，说明大于小于pivot的2部分元素分类完毕，结束循环
            //如果 start<=end，就可以交换start与end位置的元素（事实上不会出现i=j的情况）
            if(start>end)
                break;

            //交换 start位置元素（左边大于pivot）与end位置元素（右边小于pivot）
            swap(arr , start , end);
        }
        //交换 pivot（即arr的left位置元素）与end位置元素
        swap(arr, left ,end);
        //交换后，中轴元素在end位置，且左边元素全部小于等于中轴元素，右边元素全部大于中轴元素
        return end;
    }

    private void swap(T[] arr , int i , int j)
    {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
