
import java.util.Scanner;

public class getYearsInString {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String  s = sc.nextLine();
        String s1="2156abc2032 And millionaires will hold 46% of total wealth by 2019, the report says. This ratio is likely to increase in 2020.";
        getYear(s1);

    }
    private   static void getYear(String s){
        String[] strArr = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <strArr.length ; i++) {
            String stmp = strArr[i].trim().replace(".","").replace(",","");

            String str1=getNumber(stmp);
            String[] sArr = str1.split(" ");
            for (int j = 0; j <sArr.length ; j++) {
                if(sArr.length!=0) {
                    try {
                        int num = Integer.parseInt(sArr[j].trim());
                        if (num >= 1000 && num <= 3999) {
                            sb.append(num).append(" ");
                        }
                    } catch (Exception e) {
                    }
                }
            }


        }
        String strResult = sb.toString();
        System.out.println(strResult);
    }
    private static String getNumber(String stmp){
        StringBuffer sb1 = new StringBuffer();
        for (int j = 0; j <stmp.length(); j++) {
            if(stmp.charAt(j)>='0'&&stmp.charAt(j)<='9'){
                sb1.append(stmp.charAt(j));
            }else{
                sb1.append(" ");
            }

        }
        return  sb1.toString();
    }

}
//    And millionaires will hold 46% of total wealth by 2019, the report says. This ratio is likely to increase in 2020.