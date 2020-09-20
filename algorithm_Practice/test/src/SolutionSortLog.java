import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 根据耗时长短给log日志中的进程记录排序，(耗时相同，创建日期早的在前）
 */
public class SolutionSortLog {

    /**
     *
     * @param srcPath 根据输入的文件路劲按行读取log记录，用String[]一一保存，并调用Comparator实现
     *                两条记录间比较的自定义排序；
     */
    public static void sortLog(String  srcPath){
       String strtmp = new String();
       ArrayList<String> recordLineList =new ArrayList();
       File f = new File(srcPath);
        try (BufferedReader fr = new BufferedReader(new FileReader(f))) {
            while ((strtmp = fr.readLine()) != null&& strtmp !="") {
                recordLineList.add(strtmp);
            }
            fr.close();

            /**
             * 此处用lamda表达式内部类调用getTimeAndDate方法得到，单条记录中的耗时和日期的String形式，
             * 将其分别转成double和date(->long)，比较
             */
            Comparator<String> objectComparator = (String o1, String o2) -> {
                String strTime =getTimeAndDate(o1)[0];
                double doubleTime = Double.parseDouble(strTime);

                String strTime2 =getTimeAndDate(o2)[0];
                double doubleTime2 = Double.parseDouble(strTime2);

                if(doubleTime==doubleTime2){
                    String strDate =getTimeAndDate(o1)[1];
                    String strDate2 =getTimeAndDate(o2)[1];
                    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss,SSS" );
                    try {
                        Date date = sdf.parse(strDate);
                        Date date2 = sdf.parse(strDate2);
                        if(date.getTime()==date2.getTime()) return 0;
                        int flag = date.getTime()-date2.getTime()>0?1:-1;
                        return flag ;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                return (int)Math.ceil(doubleTime -doubleTime2);
            };

            String [] recordLineArr = new String [recordLineList.size()] ;
            for (int i = 0; i <recordLineArr.length ; i++) {
                recordLineArr[i]=recordLineList.get(i);
            }

            System.out.println("排序前：");
            for (String s:recordLineArr) {
                System.out.println(s);
            }
            Arrays.parallelSort( recordLineArr,objectComparator);

            System.out.println("排序后：");
            for (String s:recordLineArr) {
                System.out.println(s);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param str 日志单行记录的字符串表达
     * @return String[2] ，String[0]：分离出的耗时(strTime) , String[1]：分离出的创建日期(strDate)
     */
    public static String[] getTimeAndDate(String str){
        String[] resultArr = new String[2];
        String strLine = str;
        String[] strArry =  strLine.trim().split(" ");
        StringBuffer sb = new StringBuffer();
        int count=0;
        for (int i = 0; i < strArry.length; i++) {
            if(strArry[i]!=" "&&strArry[i].length()>0) {
                sb.append(strArry[i]).append("$");
                count++;
            }
            if(count==2) sb.deleteCharAt(sb.length()-1).append(" ");
        }
        strArry = sb.deleteCharAt(sb.length()-1).toString().split("\\$");
        String strTime =strArry[strArry.length-1].trim().split("\\(s\\)")[0];
        String strDate =strArry[1];
        resultArr[0]=strTime;
        resultArr[1]=strDate;
        return  resultArr;
    }

    public static void main(String[] args) {
        sortLog("d:/log.txt");
    }

}
