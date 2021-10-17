package com.zwXiao.Classes;

/**
 * com.zwXiao.Classes
 *
 * @author xzwnp
 * 2021/9/25
 * 17:28
 * @Description ：
 * Steps：
 */
public class CourseInfo {
    private long courseCode;
    private String courseName;
    private int hours;

    public CourseInfo(long courseCode, String courseName, int hours) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.hours = hours;
    }
    //相应的get/set方法已省略

        @Override
    public String toString() {
        return "课程编号："+this.courseCode+" 课程名:"+this.courseName+" 学时："+this.hours;
    }

    public long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(long courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


}
