public class Max_PrefixEqualSuffix_IN_String { //求一个字符串最大相同前后缀（非本身）所在的最短子串
    public static String longestHappyPrefix(String s) {

        if(s.length()<=1) return "";

        int n = s.length();
        int[] next = new int[n];
        next[0] = -1;
        int k = -1;
        int j = 0;

        while(j < n){
            if(k == -1 || s.charAt(j) == s.charAt(k)){
                ++j;
                ++k;

                if(j < n)
                next[j] = k;
            }
            else{
                k = next[k];
            }
        }
        return s.substring(0,next[n-1]);
    }

    public static void main(String[] args) {
        System.out.println(longestHappyPrefix("abcdabce"));
    }

}
