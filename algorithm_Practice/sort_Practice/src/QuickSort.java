
import java.util.Objects;

public class QuickSort {
    private  static int i,j,refNum;

    /*
    * int [] a :待排序的int数组a
    * int left :排序范围左边界left(数组下标)
    * int right :排序范围右边界right(数组下标)
    * */

    public static void  sort(int [] a ,int left ,int right){
       if(Objects.isNull(a) || a.length == 0){
           System.out.println("待排序的数组或范围不能为空！！！");
           return;
       }

       if(left > right|| left < 0 || right >a.length - 1)
           return;

      i = left;
      j = right;
       refNum = a[left];

       while(i < j) {
           while(a[j] >= refNum && i < j)
               j--;
           while(a[i] <= refNum && i < j)
               i++;
           if (i < j)
               swap(a,i,j);
       }
       swap(a,left,i);

       sort(a , left ,i-1);
       sort(a , i+1 ,right);

    }

    //默认整个数组范围排序
    public static void  sort(int [] a ){
        sort(a,0,a.length-1);
    }

    private static void swap(int[] a,int i, int i1) {
        int temp = a[i] ;
        a[i]= a[i1];
        a[i1] = temp;
    }
}
