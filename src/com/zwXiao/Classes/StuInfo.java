package com.zwXiao.Classes;

import java.sql.Connection;

/**
 * com.zwXiao.Classes
 *
 * @author xzwnp
 * 2021/9/25
 * 17:30
 * @Description ：
 * Steps：
 */
public class StuInfo {
    public long stuNum;
    public String stuName;
    public boolean sex;
    public int age;

    public StuInfo(long stuNum, String stuName, boolean sex, int age) {
        this.stuNum = stuNum;
        this.stuName = stuName;
        this.sex = sex;
        this.age = age;
    }

    public static void insert(Connection connection,long stuNum, String stuName, boolean sex, int age){
        String str = "insert into StuInfo values(stuNum,,stuName,sex,age)";
        //PreparedStatement pst = connection.prepareStatement(str);
        //pst.setLong(stuNum);

    }
    public long getStuNum() {
        return stuNum;
    }

    public void setStuNum(long stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
