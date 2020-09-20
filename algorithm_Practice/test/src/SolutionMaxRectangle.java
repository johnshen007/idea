import java.io.IOException;

public class SolutionMaxRectangle {
    public static void main(String[] args) throws IOException {
        int ans=getMaxArea(4,1,6,9,1,4,9,6);
        // int ans=getMaxArea(0,0,1,1,9,9,10,10);
        System.out.println(ans);
    }

    public static int getMaxArea (int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        // write code here
        char [][] zheng=new char[10][10];
        for(int i=0;i<10;i++){//横坐标
            for(int j=0;j<10;j++){//纵坐标
                if((i>=Math.min(x1,x2)&&i<=Math.max(x1,x2))&&(j>=Math.min(y1,y2)&&j<=Math.max(y1,y2))){
                    zheng[i][j]='0';
                }else
                if((i>=Math.min(x3,x4)&&i<=Math.max(x3,x4))&&(j<=Math.max(y3,y4)&&j>=Math.min(y3,y4))){
                    zheng[i][j]='0';
                }
                else{
                    zheng[i][j]='1';
                }
            }
        }
        int ans=maximalRectangle(zheng);
        return ans;
    }
    public static  int maximalRectangle(char[][] matrix) {
        if(matrix.length==0)return 0;
        int rows=matrix.length;
        int cols=matrix[0].length;
        int[][] dp=new int[rows][cols];
        int max=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(matrix[i][j]=='1'){
                    if(j==0)dp[i][j]=1;
                    else dp[i][j]=1+dp[i][j-1];
                    int width=dp[i][j];
                    for(int k=i;k>=0;k--){
                        width=Math.min(width,dp[k][j]);
                        max=Math.max(max,width*(i-k+1));
                    }
                }
            }
        }
        return max;
    }
}
