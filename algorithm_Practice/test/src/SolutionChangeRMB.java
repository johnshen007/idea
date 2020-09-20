import java.util.ArrayList;
import java.util.Scanner;

/**
 * 商品5元，限购一个，只用5,10,20的钱，求是否能完成找零
 * 输入是按顺序排队给钱金额的数组；
 */
public class SolutionChangeRMB {
    public static void main(String[] args) {
//        Scanner sc =new Scanner(System.in);
//        String str = sc.nextLine();
        int iArr[] = new int[]{5,5,5,20,5};
        System.out.println(canBeChange(iArr));
    }
    private static  boolean canBeChange(int[] iArr){
        ArrayList<Integer> AL = new ArrayList<>();
        for (int i = 0; i <iArr.length ; i++) {
            if(i==0){
                if(iArr[0]!=5) return  false;
                else{
                    AL.add(iArr[i]);
                    continue;
                }
            }
            AL = change(AL, iArr[i]);
            if(AL==null) return  false;
        }
        return  true;
    }
    private static ArrayList<Integer> change(ArrayList<Integer> iArr ,int k){
        if(iArr==null) return  null;
        if(k==5){
            ArrayList<Integer> AL1= (ArrayList<Integer>)iArr.clone();
            AL1.add(5);
            return AL1;
        }
        if(k==10){
            ArrayList<Integer> AL1= (ArrayList<Integer>)iArr.clone();
            if(AL1.contains(5)){
               AL1.remove(AL1.indexOf(5));
               AL1.add(10);
               return AL1 ;
            }
            return null;
        }

        if(k==20){
            ArrayList<Integer> AL1= (ArrayList<Integer>) iArr.clone();
            if(AL1.contains(10)&&AL1.contains(5)){
               AL1.remove(AL1.indexOf(10));
               AL1.remove(AL1.indexOf(5));
               AL1.add(20);
               return AL1 ;
            }
            if (!AL1.contains(10)&&AL1.contains(5)) {
                int p = 0;
                for (Integer al : AL1) {
                    if (al.equals(5)) {
                        p++;
                    }
                }
                if(p>=3) {
                    for (int i = 0; i <3 ; i++) {
                         AL1.remove(AL1.indexOf(5));
                    }
                    AL1.add(20);
                    return  AL1;
                }

            }
            return null;

        }
        return  null;

    }

}
