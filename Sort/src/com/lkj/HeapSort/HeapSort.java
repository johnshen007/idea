package com.lkj.HeapSort;

/** 堆排序
 无序数组建立堆最直接的方法是从左到右遍历数组进行上浮操作。一个更高效的方法是从右至左进行下沉操作，如果
 一个节点的两个孩子节点都已经是堆有序，那么进行下沉操作可以使得这个节点为根节点的堆有序。叶子节点不需要进行
 下沉操作，可以忽略叶子节点的元素，因此只需要遍历一半的元素即可。

 此处，第一个非叶子结点的下标是：parent(n-1)，即最后一个结点父结点的下标。
 下标为i的元素的父结点：parent(i) = i/2
 下标为i的元素的左右孩子结点：leftChild(i) = i*2+1，rightChild(i) = i*2+2

 复杂度分析：
 对于有n个元素的数组，第一个循环需要有 n/2 个元素下沉，每一次下沉的最大深度是：O(logn)，那么占用的时间是：nlogn/2.
 第二个循环有n-1个元素需要下沉，每一次下沉的最大深度是 log(n-i)，那么最后下沉的次数是：
 log1+log2+...+log(n-1) = log(n-1)!
 根据文章：https://blog.csdn.net/hzh_0000/article/details/80955511?utm_source=blogxgwz8
 我们证明 O(nlog(n)) = O(log(n!))，即他们是等价无穷大，那么堆排序的时间复杂度是：O(nlogn)。

 对于堆排序，所有情况下都需要对所有非叶子结点元素进行下沉，那么最好，最差，平均的情况都是：O(logn)
 空间复杂度：O(1)
 */
public class HeapSort<T extends Comparable<T>>
{
    public T[] heapSort(T[] arr)
    {
        if (arr == null || arr.length <= 1) return arr;

        int length = arr.length;
        //先从最后一个叶子结点开始，对所有父亲结点进行下沉，构建最大堆
        for (int i = length/2; i >=0 ; i--)
        {
            sink(arr , i , length-1);//对于每一个元素，最深可以下沉到length-1位置
        }

        //循环，将每一个堆的最大值移动到数组最后
        for (int i = length-1; i >= 1 ; i--)
        {
            //将堆顶最大值移动到此时数组的最后位置，此时当前最大值已经到数组末尾
            swap(arr , 0 , i);
            //随后，将堆顶的元素下沉，下沉的最大深度是 i-1（i位置已经被最大值占据），此时堆顶又是最大值！
            sink(arr , 0 , i-1);
        }

        return arr;
    }

    //对下标为 index 的元素进行下沉操作，最大考验下沉到 maxIndex 位置
    private void sink(T[] arr , int index , int maxIndex)
    {
        int parent = index;//父亲结点
        int child = index*2+1;//左孩子结点

        //当孩子结点还没有越界的时候，我们可以进行下沉操作
        while(child <= maxIndex)
        {
            /**
            如果右孩子结点存在，且右孩子结点值大于左孩子结点，那么叫child设置为右孩子结点下标，
             这样做是为了找到左右孩子结点最大值。
             这样交换的时候，父亲结点才能与左右孩子结点的最大值交换，这样交换后才能保证新的父亲结点大于等于左右孩子结点！
             */
            //
            if((child+1)<=maxIndex && arr[child+1].compareTo(arr[child])>0)
                child++;
            //如果父亲结点的值大于等于左右孩子结点中最大的值，说明该结点下沉到底，不需要继续下沉
            if(arr[parent].compareTo(arr[child])>=0)
                break;
            //否则，交换父亲结点与child的值
            swap(arr , parent , child);
            //将新的父亲结点值设置为child
            parent = child;
            //计算新的左孩子结点的值
            child = 2*parent+1;
        }

    }

    private void swap(T[] arr , int i , int j)
    {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
