
import java.util.Scanner;

	 /*设str1长度为N，str2长度为M。如果N<M，那么结果必然为0。

	其余情况使用一个哈希表map来辅助。

	先遍历一遍str2，生成哈希表对于字符的统计，其数值的具体意义就是str1目前还欠str2字符串key字符value个。
		
    需要定义四个变量：left: str1中子串左边界；right: str1中子串右边界；match：对于所有的str2中的字符来说，
    str1欠str2的字符总个数；minLen: 最小子串的长度。初始时，left=0，right=0，match可由遍历str2的时候得到，
    minLen为32位整数最大值
		
    通过right变量从左到右遍历str1，设当前位置right==i，有几种情况：
			
	首先在map中把str[i]字符的value减1。如果减完之后，map[str[i]]的value大于等于0，说明str1归还了一个str[i]，
	match也对应的-1。
				
    在map中把str[i]字符的value减1，如果减完之后，map[str[i]]的value小于0，说明这个字符是目前str2不需要的，所以match不变。
				
    直到某次会将match减为0时，说明str1把需要归还的字符都还完了，但此时对应的子串并不是一定最短的，因为可能有些字符归还的
    比较多余，此时开始向右移动left。
				
    初始时left=0，把str[0]对应字符拿回来后，要在map[str[0]]位置+1，如果map[str[0]]位置没加1之前是负数，说明可以要回来。
    然后继续向右移动left，直到出现map[str[left]]为0时，不能再减了，否则就又亏欠了。此时出现了一个子串满足条件，更新minLen
    为right-left+1。至此，使得出现满足条件的子串的第一个right出现，但还是要往右扩，观察后面有没有更小的值。
				
    后面的其余子串肯定比之前的子串left靠右，因为之前子串left对应的最短子串已经找到，对maxLen已经更新。所以此时令left++，
    同时让对应字符在map中也+1，说明又出现了亏欠str2，对应得，match也+1。然后继续right往右扩，直到match继续归0，重复这个过程，
    直到right到达str1的末尾。
				
    如果从始至终没有更新过maxLen，说明没有匹配成功过，返回0。

时间复杂度: O(N)*/





public class ShortestIncludingString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();
        int minlength = minLength(str1, str2);
        System.out.println(minlength);
    }
    public static int minLength(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length()) {
            return 0;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < chas2.length; i++) {
            map[chas2[i]]++;
        }
        int left = 0;
        int right = 0;
        int match = chas2.length;
        int minLen = Integer.MAX_VALUE;
        while (right < chas1.length) {
            map[chas1[right]]--;
            if (map[chas1[right]] >= 0) {
                match--;
            }
            if (match == 0) {
                while (map[chas1[left]] < 0) {
                    map[chas1[left++]]++;
                }
                minLen = Math.min(minLen, right - left + 1);
                match++;
                map[chas1[left++]]++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
