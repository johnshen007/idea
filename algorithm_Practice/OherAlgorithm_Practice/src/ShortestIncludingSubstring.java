
//ShortestCoverSubstring最小覆盖子串（最短包含子串）
public class ShortestIncludingSubstring {

    public String minWindow(String stringS,String stringT ) {
        char[] charArrayS = stringS.toCharArray();
        char[] charArrayT = stringT.toCharArray();

        int[] winFreq = new int[128];
        int[] tFreq = new int[128];

        int left = 0;
        int right = 0;
        int sLen = charArrayS.length;
        int tLen = charArrayT.length;
        int distance = 0;//窗口中匹配的字符数与T串字符数的差

        String desStr =new String("");
        int minLen = sLen+1;

        for (int i = 0; i <tLen ; i++) {
            tFreq[charArrayT[i]]++;
        }

        if(sLen == 0||tLen ==0||sLen < tLen){
            return "";
        }

        while (right < sLen){
            char charRight = charArrayS[right];
            winFreq[charRight]++;

            if (tFreq[charRight] == winFreq[charRight]) {
                distance++;
            }

            while(distance == tLen){
                if(minLen>stringS.substring(left,right+1).length()){
                    desStr = stringS.substring(left,right+1);
                    minLen =desStr.length();
                }

                char charLeft= charArrayS[left];
                if(winFreq[charLeft]== tFreq[charLeft]){
                    distance--;
                }
                winFreq[charLeft]--;
                left++;
            }

            right++;

        }
        return desStr;
    }
}
