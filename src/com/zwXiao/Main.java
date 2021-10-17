package com.zwXiao;

import com.zwXiao.Classes.CourseInfo;
import com.zwXiao.Classes.CourseInfoManger;
import com.zwXiao.util.ConfigManger;
import org.junit.Test;

import java.sql.*;


/**
 * @author xzwnp
 */
public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(ConfigManger.getProp("url"), ConfigManger.getProp("user"), ConfigManger.getProp("password"));
            //开始执行查询
            Statement st = connection.createStatement();
            //Statement类有两个常用方法：execute()可以执行增删改查语句，返回值是boolean类型
            //想要接收select查询到的结果，使用方法executeQuery();
            String str = "select * from courseInfo";
            //result用来接收查询到的结果
            ResultSet rs = st.executeQuery(str);
            while (rs.next()) {
                System.out.println("课程号：" + rs.getString("Code"));
                System.out.println(";");
                System.out.println("课程名：" + rs.getString("CourseName"));
                System.out.println(";");
                System.out.println("学时：" + rs.getString("Hours"));
                System.out.println("。");
            }
            rs.close();
            st.close();
            String insertStr = "insert into courseInfo(Code,courseName,Hours) values(?,?,?)";
//            String insertStr = "insert into courseInfo(Code,courseName,Hours) values(107,'线性代数',54);";
            PreparedStatement pst = connection.prepareStatement(insertStr);
            pst.setString(1, "106");
            pst.setString(2, "线性代数");
            pst.setInt(3, 54);
            pst.executeUpdate();
//            pst.executeUpdate(insertStr);
//            String updateStr = "update courseInfo set Hours = 54 where CourseName = '计算机基础'";
            String deleteStr = "delete from courseInfo where Code = ?";
//            PreparedStatement pst2 = connection.prepareStatement(insertStr);
//            pst2.setString(1,"107");
//            int a = pst2.executeUpdate();
//            System.out.println(a);
//            st.execute(deleteStr);

            //最后不要忘了关闭结果集对象、陈述对象、连接
            pst.close();
//            pst2.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void test() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(ConfigManger.getProp("url"), ConfigManger.getProp("user"), ConfigManger.getProp("password"));
            //测试查询操作，并用读取到的结果自动创建类
            CourseInfoManger.readAndCreate(connection);
            //测试插入操作
            CourseInfo digitalCircuit = new CourseInfo(120,"数字电路",36);
            CourseInfoManger.insert(connection,digitalCircuit);
            //测试更新操作
//            CourseInfoManger.update(connection,new CourseInfo(110,"软件工程",54));
            CourseInfoManger.update(connection, 110, "软件工程", 54);
            //测试删除操作
            //编号为106的课程不存在，会删除失败
            CourseInfoManger.delete(connection,106);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }

    }
}