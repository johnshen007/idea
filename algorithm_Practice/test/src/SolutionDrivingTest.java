
import java.util.ArrayList;

import java.util.Scanner;

public class SolutionDrivingTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String  s = sc.nextLine();
        System.out.println(drivingTest(s));

    }
    private  static String  drivingTest(String s){
        if(s.contains("A")||s.contains("B")||s.contains("C")||s.contains("D")||s.contains("E")||s.contains("F")){
            return  "Collision";
        }
        if(s.contains("-1")) return "Out of path";
        char[] charArr = s.toCharArray();
        ArrayList<Character> set = new ArrayList<>();
        for (int i = 0; i <charArr.length ; i++) {
            if(set.get(set.size()-1)!=charArr[i])
            set.add(charArr[i]);
        }
        String s1=set.toString();

        if(s1.substring(0,36).equals("0,1,2,3,4,3,2,1,5,6,5,7,8,9,8,7,5,1,0")){
            return "Success";
        }
        return "Bad path";

    }
}
//        0,1,2,3,4,3,2,1,5,6,5,7,8,9,8,7,5,1,0
