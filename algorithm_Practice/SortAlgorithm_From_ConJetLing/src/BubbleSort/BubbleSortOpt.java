package BubbleSort;
/**
使用一个布尔型标志 Flag，对冒泡排序进行优化，代码如下
 */
public class BubbleSortOpt<T extends Comparable<T>>
{
    public T[] bubbleSort(T[] arr)
    {
        if (arr == null || arr.length <= 1) return arr;

        boolean flag = false;

        //flag=false，表示上一轮冒泡没有排序完毕，需要继续排序
        for (int i = 0; i < arr.length-1 && !flag ; i++)
        {
            /*
            在每一轮冒泡之前，将标志设置为true，意思是这个数组已经排序完毕。
            如果冒泡中有元素的交换出现，说明数组还没有排序完毕，我们将flag设置为false，下一轮还需继续冒泡！
            如果没有出现元素交换，说明数组已经排序完毕，不需要继续交换，那么flag=true，下一轮的冒泡不会继续进行！
             */
            flag = true;
            for (int j = 0; j < arr.length-1-i ; j++)
            {
                if(arr[j].compareTo(arr[j+1]) > 0)
                {
                    flag = false;
                    T temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return arr;
    }
}
