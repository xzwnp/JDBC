package com.zwXiao.Classes;

import java.sql.*;
import java.util.ArrayList;

/**
 * com.zwXiao.Classes
 *
 * @author xzwnp
 * 2021/9/27
 * 14:39
 * @Description ：实现课程增删改查及课程对象的创建
 * Steps：
 */
public class CourseInfoManger {
    /**
     * 查询所有课程信息并自动创建对象
     *
     * @param connection 连接
     */
    public static void readAndCreate(Connection connection) throws SQLException {
        String str = "select * from courseInfo";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(str); //结果集对象，保存查询到的结果
        ArrayList<CourseInfo> al = new ArrayList<>(10); //用于保存所有创建的课程对象
        while (rs.next()) {
            //创建课程对象
            CourseInfo courseInfo = new CourseInfo(rs.getLong("Code"),
                    rs.getString("courseName"), rs.getInt("hours"));
            System.out.println(courseInfo);
            al.add(courseInfo);
        }
        System.out.println(al);
        //关闭
        statement.close();
        rs.close();
    }

    /**
     * 静态方法。实现课程插入操作
     *
     * @param connection 连接
     * @param courseCode 课程编号
     * @param courseName 课程名
     * @param hours      学时
     * @throws SQLException
     */
    public static void insert(Connection connection, long courseCode, String courseName, int hours) throws SQLException {
        String insertStr = "insert into courseInfo(Code,courseName,Hours) values(?,?,?)";
        PreparedStatement pst = connection.prepareStatement(insertStr);
        pst.setLong(1, courseCode);
        pst.setString(2, courseName);
        pst.setInt(3, hours);
        pst.executeUpdate();
        pst.close();
    }

    public static void insert(Connection connection, CourseInfo ci) throws SQLException {
        CourseInfoManger.insert(connection, ci.getCourseCode(), ci.getCourseName(), ci.getHours());
    }

    /**
     * 静态方法。实现更新操作。
     *
     * @param connection 连接
     * @param courseCode 课程编号
     * @param courseName 课程名
     * @param hours      学时
     * @throws SQLException
     */
    public static void update(Connection connection, long courseCode, String courseName, int hours) throws SQLException {
        String updateStr = "update courseInfo set courseName = ?,hours = ? where Code = ?";
        PreparedStatement pst = connection.prepareStatement(updateStr);
        pst.setString(1, courseName);
        pst.setInt(2, hours);
        pst.setLong(3, courseCode);
        int a = pst.executeUpdate();
        //根据返回值判断是否更新成功
        if (a != 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败！");
        }
        pst.close();
    }

    public static void update(Connection connection, CourseInfo ci) throws SQLException {
        CourseInfoManger.update(connection, ci.getCourseCode(), ci.getCourseName(), ci.getHours());
    }

    public static void delete(Connection connection, long courseCode) throws SQLException {
        String deleteStr = "delete from courseInfo where Code = ?";
        PreparedStatement pst = connection.prepareStatement(deleteStr);
        pst.setLong(1, courseCode);
        int a = pst.executeUpdate();
        //根据返回值判断是否删除成功
        if (a != 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败！");
        }
        pst.close();

    }

    public static void delete(Connection connection, String courseName) throws SQLException {
        String deleteStr = "delete from courseInfo where CourseName = ?";
        PreparedStatement pst = connection.prepareStatement(deleteStr);
        pst.setString(1, courseName);
        pst.close();
    }

}
