package com.example.user.joe120204.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/12/2.
 */

public class StudentDAODBImpl implements StudentDAO {
    SQLiteDatabase db;
    MyDBHelper helper;

    public StudentDAODBImpl(Context context)
    {
        helper = new MyDBHelper(context);
        db = helper.getWritableDatabase();
    }

    @Override
    public List<Student> getList() {
        ArrayList<Student> mylist = new ArrayList<>();
        Cursor c = db.query("phone", new String[] {"ID", "SName", "tel", "addr"}, null,null,null,null,null);
        if (c.moveToFirst())
        {
            mylist.add(new Student(c.getInt(0), c.getString(1), c.getString(2), c.getString(3)));
        }
        while (c.moveToNext())
        {
            mylist.add(new Student(c.getInt(0), c.getString(1), c.getString(2), c.getString(3)));
        }

        return mylist;
    }

    @Override
    public void add(Student s) {
        ContentValues cv = new ContentValues();
        cv.put("SName", s.name);
        cv.put("tel", s.tel);
        cv.put("addr", s.addr);
        db.insert("phone", null, cv);
    }

    @Override
    public Student getItem(int ID) {
        Cursor c = db.query("phone", new String[] {"ID", "SName", "tel", "addr"},
                "ID=?",new String[] {String.valueOf(ID)},null,null,null);
        Student rt = null;
        if (c.moveToFirst())
        {
            rt = new Student(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
        }
        return rt;
    }
    @Override
    public void delete(int ID) {
        db.delete("phone","ID=?",new String[] {String.valueOf(ID)});
    }
    @Override
    public void update(Student s) {
        ContentValues cv = new ContentValues();
        cv.put("SName", s.name);
        cv.put("tel", s.tel);
        cv.put("addr", s.addr);
        db.update("phone", cv, "ID=?", new String[] {String.valueOf(s.ID)});
    }
}
