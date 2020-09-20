
public class SolutionMaxCandies {
public  static  int MaxCanies(int[]candies, int[] coins,int n){
    int num =0;
    int left = 0;
    int right = left+n-1;
    while(right<candies.length) {
//        int[] coinstmp = Arrays.copyOf(coins,coins.length);
//        for (int j = 0; j <coinstmp.length ; j++) {
//            if(j>=left&&j<=right) coinstmp[j]=0;
//        }
        int tmp =0;
        for (int i = 0; i < candies.length; i++) {
            if(i>=left&&i<=right) tmp +=candies[i];
            else if(coins[i]==0){
                tmp +=candies[i];
            }
        }
        num = Math.max(num,tmp);
        left++;
        right++;

    }
    return num;


}

    public static void main(String[] args) {
        int[] ints = new int[]{3,5,7,2,8,8,15,3};
        int[] ints1 = new int[]{1,0,1,0,1,0,1,0};
        int n = 3;
        System.out.println(MaxCanies(ints,ints1,n));

    }

}
