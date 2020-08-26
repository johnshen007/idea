

import java.util.ArrayList;
import java.util.Objects;

public class Solution {
	//只比较传两个,return true表示要换序
	public static boolean CompareStr(String[] strs){
		char[] chararray1;
		char[] chararray2;
		char[] lmin;
		chararray1=strs[0].toCharArray();
		chararray2=strs[1].toCharArray();
		lmin=chararray1.length<=chararray2.length? chararray1:chararray2;
		for (int i = 0;i< lmin.length;i++) {
			if(!Objects.equals(chararray1[i],chararray2[i])){
				if(chararray1[i]<chararray2[i]){
					return true;
				}
				return false;
			}
			if(chararray1.length!=chararray2.length){
				String str1 = strs[0]+ strs[1];
				String str2 = strs[1]+ strs[0];
				String[] strs_1 ={str1,str2};
				return Solution.CompareStr(strs_1);
			}
		}
		return false;
	}
	public static void swap(ArrayList al,ArrayList alConst,int k){
		char temp1 ;
		int temp2 ;
		temp1 = (char) al.get(k);
		al.set(k, al.get(k - 1));
		al.set(k - 1, temp1);
		temp2 = (int) alConst.get(k);
		alConst.set(k, alConst.get(k - 1));
		alConst.set(k - 1, temp2);
	}

    public String largestNumber(int[] nums) {
		StringBuffer sbf = new StringBuffer();
		ArrayList al = new ArrayList();
		ArrayList alConst = new ArrayList();
		for (int i = 0; i < nums.length; i++) {
			al.add(String.valueOf(nums[i]).charAt(0));
			alConst.add(i);
		}
		for (int i = 0; i <al.size(); i++) {
			for (int j = al.size()-1; j >i; j--) {

				if ((char) al.get(j) > (char) al.get(j - 1)) {
					Solution.swap(al,alConst,j);
				}
				if (Objects.equals(al.get(j).toString(), al.get(j - 1).toString())) {
					String[] strs = {String.valueOf(nums[(int) alConst.get(j-1)]), String.valueOf(nums[(int) alConst.get(j)])};
					if (Solution.CompareStr(strs)) {
						Solution.swap(al,alConst,j);
					}

				}
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if(nums[(int) alConst.get(0)]==0){
				sbf.append(0);
				break;
			}

			sbf.append(String.valueOf(nums[(int) alConst.get(i)]));
		}

		return sbf.toString();
	}



	public static void main(String[] args) {
	int[] nums = {10,2,410,1,412,};
	 Solution solution = new Solution();
	 System.out.println(solution.largestNumber(nums));}


}