import java.util.ArrayList;
import java.util.Scanner;

/**
 * 走出地图，先后输入组数(几个矩阵，每一组先给出行列，再逐行给出值)、行数和列数、每行的值...
 */
public class SolutionPrinceAndPrincess {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 =sc.nextLine();
        ArrayList<Object> AL = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(s1); i++) {
            String s2= sc.nextLine();
            String[] s2Arr = s2.split(" ");
            int n = Integer.parseInt(s2Arr[0]);
            int m = Integer.parseInt(s2Arr[1]);
            char[][] chars = new char[n][m];
            for (int j = 0; j < n; j++) {
                String s3= sc.nextLine();
                char[] s3Arr= s3.toCharArray();
                for (int k = 0; k <s3Arr.length ; k++) {
                    chars[j][k]= s3Arr[k];
                }

            }
            AL.add( chars);
        }

        for (int i = 0; i <AL.size() ; i++) {
            if (uniquePathsWithObstacles((char[][])AL.get(i))){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }

    }

    public static boolean uniquePathsWithObstacles(char[][] cs) {
        if(cs.length ==0 || cs[0].length == 0) return false;
        int[][] obstacleGrid=new int[cs.length][cs[0].length];
        int xs=0;int ys=0;int xe=0;int ye=0;
        for(int i=0;i<cs.length;i++){
            for(int j=0;j<cs[0].length;j++){
                if(cs[i][j]=='E'){
                    xe=cs.length-i-1;
                    ye=j;
                    obstacleGrid[cs.length-i-1][j]=0;
                }else if(cs[i][j]=='S'){
                    xs=cs.length-i-1;
                    ys=j;
                    obstacleGrid[cs.length-i-1][j]=0;
                }else if(cs[i][j]=='.'){
                    obstacleGrid[cs.length-i-1][j]=0;
                }else{
                    obstacleGrid[cs.length-i-1][j]=1;
                }
            }
        }
        int m = Math.abs(xs-xe)+1,n = Math.abs(ys-ye)+1;
        int[][] dp = new int[m+1][n+1];
        dp[1][0] = 1;
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                if(obstacleGrid[i-1][j-1] == 1){
                    continue;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m][n]>0?true:false;
    }

}
