/*最长无重复子串*/
public class LongestUnrepeatedSubstring {

        public static int lengthOfLongestSubstring(String s) {
            char[] charArrayS = s.toCharArray();
            int[] winFreq = new int[128];

            int left = 0;
            int right = 0;
            int sLen = charArrayS.length;

            int maxLen = 0;
            while(right<sLen){
                char charRight = charArrayS[right] ;
                winFreq[charRight]++;
                if(winFreq[charRight]==1) maxLen = Math.max(maxLen, right-left+1);
                while(winFreq[charRight]>1) {
                    char charLeft = charArrayS[left];
                    winFreq[charLeft]--;
                    left++;
                }
                right++;
            }
            return  maxLen;
        }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

}
