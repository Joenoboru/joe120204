package com.example.user.joe120204.data;

import android.content.Context;
import android.util.Log;

import com.example.user.joe120204.data.Student;
import com.example.user.joe120204.data.StudentDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 2016/12/2.
 */

public class StudentDAOFileImpl implements StudentDAO {
    ArrayList<Student> data;
    Context context;

    public StudentDAOFileImpl(Context context)
    {
        this.context = context;
        data = getList();
    }
    @Override
    public ArrayList<Student> getList() {
        Gson gson = new Gson();
        String filedata = readData();

        data = gson.fromJson(filedata, new TypeToken<ArrayList<Student>>(){}.getType());
        if (data == null)
        {
            data = new ArrayList<>();
        }
        return data;
    }

    @Override
    public void add(Student s) {
        data.add(s);
        Gson gson = new Gson();
        writeFile(gson.toJson(data, new TypeToken<ArrayList<Student>>(){}.getType()));
    }

    @Override
    public Student getItem(int ID) {
        for (Student s : data)
        {
            if (s.ID == ID)
            {
                return s;
            }
        }
        return null;
    }

    @Override
    public void delete(int ID) {
        int loc = -1;
        for (int i=0;i<data.size();i++)
        {
            if (data.get(i).ID == ID)
            {
                loc = i;
                break;
            }
        }
        if (loc != -1)
        {
            data.remove(loc);
        }
        Gson gson = new Gson();
        writeFile(gson.toJson(data, new TypeToken<ArrayList<Student>>(){}.getType()));
    }
    @Override
    public void update(Student s) {
        int loc = -1;
        for (int i=0;i<data.size();i++)
        {
            if (data.get(i).ID == s.ID)
            {
                loc = i;
                break;
            }
        }
        if (loc != -1)
        {
            data.get(loc).name = s.name;
            data.get(loc).tel = s.tel;
            data.get(loc).addr = s.addr;
        }
        Gson gson = new Gson();
        writeFile(gson.toJson(data, new TypeToken<ArrayList<Student>>(){}.getType()));
    }

    public String readData()
    {
        char[] buffer = new char[1];
        FileReader fr = null;
        StringBuilder sb = new StringBuilder();
        File file = new File(context.getFilesDir() + "/" + "mydata.txt");

        try {
            fr = new FileReader(file);
            while (fr.read(buffer)!= -1) {
                sb.append(new String(buffer));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("READFILE", sb.toString());
        return sb.toString();
    }

    public void writeFile(String s)
    {
        FileOutputStream fOut = null;
        try {

            fOut = context.openFileOutput("mydata.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);  // 寫入資料
            osw.write(s);
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}