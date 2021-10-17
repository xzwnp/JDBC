package com.zwXiao.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * com.zwXiao.utool
 *
 * @author xzwnp
 * 2021/9/25
 * 16:14
 * @Description ：参数管理器
 * Steps：
 */
public class ConfigManger {
    //使用配置文件访问类的好处：修改配置参数时只需要修改配置文件，不需要修改代码，增强程序的可移植性
    private static final Properties prop = new Properties();
    //初始化配置文件读取类
    static {
        InputStream io = ConfigManger.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *     读取配置文件
     */
    public static String getProp(String key){
        return prop.getProperty(key);
    }
    public static void main(String[] args) {
        System.out.println("driver="+ConfigManger.getProp("driver"));
        System.out.println("url="+ConfigManger.getProp("url"));
        System.out.println("user="+ConfigManger.getProp("user"));
    }
}
