package com.example.user.joe120204;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.joe120204.data.DAOSource;
import com.example.user.joe120204.data.Student;
import com.example.user.joe120204.data.StudentDAO;
import com.example.user.joe120204.data.StudentDAODBImpl;
import com.example.user.joe120204.data.StudentDAOFactory;
import com.example.user.joe120204.data.StudentDAOFileImpl;
import com.example.user.joe120204.data.StudentDAOMemoryImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //將student資料在listview顯示,但會遇到一個問題,main新增的student資料與main2新增的student資料是相互獨立無
    //無法相互影響,將會使得main2新增的資料無法在main顯示,
    ArrayList<String> data;
    ListView lv;
    ArrayAdapter<String> adapter;
    MyApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        data = new ArrayList<>();
        //DAO模式(設計模式)
        //利用class-myapplication宣告一全域變數
        app = (MyApplication) getApplication();
        //app.dao = new StudentDAOMemoryImpl();將student資料儲存在手機記憶體
        //app.dao = new StudentDAOFileImpl(MainActivity.this);//將student資料儲存在手機內部空間
        //app.dao = new StudentDAODBImpl(MainActivity.this);//在手機建立sqlite資料庫儲存student資料
        //透過更改getstudentDAO的數字,調整資料存放的方式(Memory or file or DB)
        app.dao = StudentDAOFactory.getStudentDAO(MainActivity.this, DAOSource.DB);
        //app.dao.add(new Student(1, "Bob", "123", "aa123"));
        //app.dao.add(new Student(2, "Mary", "345", "bb345"));
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<Student> lst = app.dao.getList();
                Student s = lst.get(position);
                Intent it = new Intent(MainActivity.this, DetailActivity.class);
                it.putExtra("ID", s.ID);
                startActivity(it);
            }
        });
    }
    //當main2返回main後能夠顯示新增的資料,若無onResume這段,將不會顯示新增的資料
    @Override
    protected void onResume() {
        super.onResume();
        List<Student> lst = app.dao.getList();
        data.clear();
        for (Student s : lst)
        {
            Log.d("DATA", s.toString());
            data.add(s.toString());
        }
        adapter.notifyDataSetChanged();//若資料改變後,讓adapter內容也改變
    }
    //新增右上角的...選單
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("ADD");
        return super.onCreateOptionsMenu(menu);
    }
    //設定ADD點選後的動作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(it);
        return super.onOptionsItemSelected(item);
    }
}
