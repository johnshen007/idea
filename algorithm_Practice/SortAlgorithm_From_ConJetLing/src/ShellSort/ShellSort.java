package ShellSort;
/** 希尔排序
 对于有n个元素的数组，希尔排序一共会分组 log2(n) 次，每一次分组，需要比较（n-gap）次，
 那么比较的次数是：(n-n/2)+(n-n/4)+...+1:logn 个 = nlogn -  (1+..+n/4+n/2)logn = (3n/4-1/4)logn
 那么希尔排序的时间复杂度是：O((3n/4-1/4)logn) = O(nlogn)
 即在平均、最好、最差的情况下，希尔排序时间复杂度都是：O(logn)级别
 空间复杂度：O(1)
 */
public class ShellSort<T extends Comparable<T>>
{
    public T[] shellSort(T[] arr)
    {
        if(arr == null || arr.length <= 1)
            return arr;

        int len = arr.length;
        /**
        当增量 gap 大于0的时候，持续进行分组排序（gap最小值为1）。
         需要注意的是，增量gap代表的是每一组相邻2个元素之间下标的差，也就是说，当前数组可以分为gap组。

         特别注意:对各个分组进行插入的时候并不是先对一个组排序完了再来对另一个组排序，而是轮流对每个组进行排序。

         比如对于10个元素，当gap=5的时候，分为5组，我们从第一组的第二个元素（下标为gap）开始进行插入排序，
         第一组的下标是 0,5、第二组下标是1,6、第三组下标是2,7、第四组下标是3,8、第二组下标是4,9.
         我们先从gap=5开始，先第一组插入排序（0,5），接着+1，到第二组插入排序（1,6）...到第五组插入排序（4,9）

         比如对于10个元素，当gap=2的时候，分为2组，我们从第一组的第二个元素（下标为gap）开始进行插入排序，
         第一组的下标是 0,2,4,6,8、第二组下标是1,3,5,7,9
         我们先从gap=2开始，先第一组插入排序（0,2），再第二组插入排序（1,3），再第一组插入排序（2,4），
         再第二组插入排序（2,4），...，再第一组插入排序（6,8），再第二组插入排序（7,9）。

         结论：
         1）对各个分组进行插入的时候并不是先对一个组排序完了再来对另一个组排序，而是轮流对每个组进行排序。
         2）我们从第一组的第二个元素开始对每一组插入排序，直到数组末尾（每一组的第一个元素不需要插入排序，这里参考只有一组时的插入排序）
         */
        int gap = len/2;
        while(gap>0)
        {
            for (int i = gap; i < len ; i++)
            {
                T current = arr[i];//记录当前位置元素
                int pre = i-gap;//记录这一组前一个位置的元素的位置
                //向前查找这一组的每一个元素（注意间隔是gap），直到不满足2个条件的其中一个
                // 下标小于0或者current>arr[pre]，此时将元素 current 插入pre+gap处
                while(pre>=0 && current.compareTo(arr[pre])<0)
                {
                    arr[pre+gap] = arr[pre];
                    pre -= gap;//注意移动的间隔是gap
                }
                //最后，记得将pre+gap位置设置为current
                arr[pre+gap] = current;
            }
            //这一轮的各个分组插入排序完毕后，将gap/2，进入下一轮
            gap /= 2;
        }
        return arr;
    }
}
