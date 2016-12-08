package com.example.user.joe120204.data;

/**
 * Created by user on 2016/12/2.
 */
//裝載資料用
public class Student {
    public int ID;
    public String name;
    public String tel;
    public String addr;
    //建構
    public Student(int i, String n, String t, String a)
    {
        ID = i;
        name = n;
        tel = t;
        addr = a;
    }

    //Override java.object中的toString方法
    @Override
    public String toString()
    {
        return "" + ID + "," + name;
    }
}
