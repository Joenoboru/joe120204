package com.example.user.joe120204.data;

import java.util.List;

/**
 * Created by user on 2016/12/2.
 */

public interface StudentDAO {
    public List<Student> getList();
    public void add(Student s);
    public Student getItem(int ID);
    public void delete(int ID);
    public void update(Student s);
}
