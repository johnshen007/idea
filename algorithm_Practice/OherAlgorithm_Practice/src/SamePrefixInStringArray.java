import java.util.*;
//根据输入String[] strs和String strTest，输出strs数组中与strTest具有公共前缀长度前三的字符
public class SamePrefixInStringArray {

    private static ArrayList<LinkedList> Array = new ArrayList();
    public static void compareAndAddTOArray(String[] strs , String strTest) {

        if (strs == null || strs.length == 0) {
            System.out.println("");
        }
        Arrays.sort(strs);

        for (int i = 0; i < strTest.length(); i++) {
           Array.clear();
           String strtmp=strTest.substring(0,i+1);
           String prefix = strtmp;
            int count = strs.length;
            for (int j = 0; j < count; j++) {
                 compareAndAddTOArray(prefix, strs[j]);
                if (prefix.length() == 0) {
                    break;
                }
            }

            int k = 3;
            for (int t = Array.size()-1; t >=0;t--) {
                LinkedList linkedList = Array.get(t);
                Iterator<String> iterator = linkedList.iterator();

                while( iterator.hasNext()) {
                    k--;
                    String key = iterator.next();
                    System.out.print(key+"  ");
                    if(k==0){
                        System.out.println(" "+strtmp);
                        break;
                    }
                }
                if(k==0) break;

            }


        }

    }

    public static void compareAndAddTOArray(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }

        while (Array.size()-1 < index) {
            LinkedList<String> list = new LinkedList<>();
            Array.add(list);
        }

        if(Array.get(index).size()==0){
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            Array.set(index,list);
        }
        else{
            LinkedList<String> list =Array.get(index);
            list.add(str2);
            Array.set(index,list);
        }

    }


    public static void main(String[] args) {
        String[] strs = new String[]{"lexin","lgin","lexld","lexig","ldaf","lax","lea","lexan"};
        String strTest = new String("lexin");
        compareAndAddTOArray(strs,strTest);

    }
}