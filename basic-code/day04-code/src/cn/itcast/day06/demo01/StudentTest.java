package cn.itcast.day06.demo01;

public class StudentTest {
    public static void main(String[] args) {
        Student one = new Student("迪丽热巴",20);
        System.out.print("姓名："+one.getName()+"\n"+"年龄："+one.getAge());
        System.out.println("\n================");
        Student two = new Student();
        two.setName("赵丽颖");
        two.setAge(18);
        System.out.print("姓名："+two.getName()+"\n"+"年龄："+two.getAge());
    }
}
