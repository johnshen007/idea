import java.util.*;



public class Main {


    public static  String replaceWord(ArrayList<String> l , String content, String strTarget){
        for(int i=0 ;i<l.size();i++){
            String str =l.get(i);
            content = content.replaceAll(str,strTarget);
        }
        return content;

    }


    public static void recur(String str, String  cur ,ArrayList<String> result){
        if(str.length()==0){
            if(!result.contains(cur)){
                result.add(cur);
            }
        }

        for (int i = 0; i < str.length() ; i++) {
            recur(str.substring(0,i)+str.substring(i+1,str.length()),cur+str.charAt(i),result);
        }
    }


    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        String str3 = sc.nextLine();
        ArrayList<String> result =new ArrayList<>();
        recur(str1,"",result);
        String s= replaceWord(result,str2,str3);
        System.out.println(s);
    }
}