

public class QuickSort_test {
    public static void main(String[] args) {
        int [] a = {1, 3, 9, 0, 5, 7, 6, 2, 4, 3};
        QuickSort.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]+" ");
        }

    }
}
