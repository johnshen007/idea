package com.company.jdbctemplate;

import com.company.domain.Emp;
import com.company.utils.*;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sun.misc.Regexp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class JdbcTemplateDemo2 {

    //Junit单元测试，可以让方法独立执行


    //1. 获取JDBCTemplate对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 1. 修改某条记录的属性值
     */
   // @Test
    public void test1(String pid, int pid_value ,String n , int n_value ,int B_id_value  ){
        //手动实现动态SQL即表名和字段名不固定
        String sql = "update listdetalils set "+ pid +" = ?,"+ n +" = ?";
        sql += "  where B_id = ? ";
        //3. 执行sql
        int count = template.update(sql,  pid_value , n_value ,B_id_value );//返回更新的行数
        System.out.println(count);
    }

    /**
     * 2. 添加一条记录
     */
   // @Test
    public void test2(String pid ,String n , int B_id_value , int pid_value , int n_value){
        //手动实现动态SQL即表名和字段名不固定
        String sql ="insert into listdetalils(B_id," + pid + "," + n +")";

         sql += "  values( ?,?,?) ";
        int count = template.update(sql,  B_id_value  ,pid_value , n_value );//返回更新的行数
        System.out.println(count);
    }

    /**
     * 3.查询记录是否存在
     */
   // @Test
    public boolean test3( int B_id_value){
        String sql = "select B_id from bill where B_id = ? ";
        String str = template.queryForObject(sql,String.class, B_id_value);
        return(Objects.equals(B_id_value,str));
    }

    /**
     * 4.查询指定id的记录，将其封装为Map集合
     * 注意：这个方法查询的结果集长度只能是1
     */
    @Test
    public void test4(){
        String sql = "select * from bill where B_id = ? ";
        Map<String, Object> map = template.queryForMap(sql, 1);
        System.out.println(map);
        int B_id_value = Integer.parseInt(map.get("B_id").toString());
        String[] strings ;
        strings = map.get("Lists").toString().split("\\+");
        int len = strings.length;
        for (int i = 0; i < len; i++) {
            char suffix = (char) ('A'+ i);
            String pid = "Product" + String.valueOf(suffix);
            String n = "Count" + String.valueOf(suffix);
            int pid_value = Integer.parseInt(strings[i].split("\\*")[0]);
            int n_value =Integer.parseInt(strings[i].split("\\*")[1]);
            if(test3(B_id_value))
                continue;

            if(i==0)
                test2(pid ,n ,B_id_value,pid_value, n_value);

            else{
                test1(pid, pid_value ,n , n_value ,B_id_value );
            }

        }


    }

    /**
     * 5. 查询所有记录，将其封装为List,取lists列值，插入listdetails表
     */
    @Test
    public void test5(){
        String sql = "select * from bill";
        List<Map<String, Object>> list = template.queryForList(sql);

        for (int j = 0; j < list.size(); j++) {
            Map<String, Object> map = list.get(j);
            int B_id_value = Integer.parseInt(map.get("B_id").toString());
            Boolean bl = Pattern.matches("^\\d+\\*\\d+(\\+\\d+\\*\\d+)*$",map.get("Lists").toString());
            if(!bl){
                System.out.println("B_id = "+ B_id_value+" lists表单格式错误");
                continue;
            }
            String[] strings ;
            strings = map.get("Lists").toString().split("\\+");
            int len = strings.length;
            for (int i = 0; i < len; i++) {
                char suffix = (char) ('A'+ i);
                String pid = "Product" + String.valueOf(suffix);
                String n = "Count" + String.valueOf(suffix);
                int pid_value = Integer.parseInt(strings[i].split("\\*")[0]);
                int n_value =Integer.parseInt(strings[i].split("\\*")[1]);
                if(test3(B_id_value))
                    continue;

                if(i==0)
                    test2(pid ,n ,B_id_value,pid_value, n_value);

                else{
                    test1(pid, pid_value ,n , n_value ,B_id_value );
                }

            }

        }
    }

    /**
     * 6. 查询所有记录，将其封装为Emp对象的List集合
     */

    //@Test
    public void test6(){
        String sql = "select * from emp";
        List<Emp> list = template.query(sql, new RowMapper<Emp>() {

            @Override
            public Emp mapRow(ResultSet rs, int i) throws SQLException {
                Emp emp = new Emp();
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                return emp;
            }
        });


        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    /**
     * 6. 查询所有记录，将其封装为Emp对象的List集合
     */

    //@Test
    public void test6_2(){
        String sql = "select * from emp";
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    /**
     * 7. 查询总记录数
     */

    //@Test
    public void test7(){
        String sql = "select count(id) from emp";
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);
    }

}
