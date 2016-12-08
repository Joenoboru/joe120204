package com.example.user.joe120204.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/12/2.
 */

public class StudentDAOMemoryImpl implements StudentDAO {
    ArrayList<Student> data;
    public StudentDAOMemoryImpl()
    {
        data = new ArrayList<>();
    }
    //實作 StudentDAO的interface
    @Override
    public List<Student> getList() {
        return data;
    }
    //實作 StudentDAO的interface
    @Override
    public void add(Student s) {
        data.add(s);
    }

    @Override
    public Student getItem(int ID) {
        return null;
    }
    @Override
    public void delete(int ID) {

    }
    @Override
    public void update(Student s) {

    }
}
