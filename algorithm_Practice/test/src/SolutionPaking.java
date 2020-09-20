
/**
 *停车问题：park表示一排停车位，park[i]=0表示无车
 * park[i]=1表示有车，求能每辆车必须有间隔一个空位，求能放下n辆车吗？
 */
public class SolutionPaking {
    public static boolean parking (int[] park , int n){
        for (int i = 0; i < park.length; i++) {
            if(park[i]==0) {
                if(park.length==1||park.length==2)  return true;
                if(park.length==2&&park[0]==1)  return false;
                if (i == 0  &&park[i + 1] == 0) {
                    n--;
                    park[i]=1;
                    continue;
                }
                else if(i == park.length - 1 && park[i - 1] == 0) {
                    n--;
                    park[i]=1;
                    continue;
                }

                else if(park[i + 1] == 0&& park[i - 1] == 0){
                    n--;
                    park[i]=1;
                    continue;
                }
                if(n==0){
                    return  true;
                }

            }
        }
        if(n==0){
            return  true;
        }else  {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] park= new int[]{};
        int n =1;
        if(parking(park,n)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
