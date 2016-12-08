package com.example.user.joe120204;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.user.joe120204.data.Student;

/**
 * Created by user on 2016/12/5.
 */

public class EditActivity extends AppCompatActivity {
    EditText ed5, ed6, ed7, ed8;
    MyApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ed5 = (EditText) findViewById(R.id.editText5);
        ed6 = (EditText) findViewById(R.id.editText6);
        ed7 = (EditText) findViewById(R.id.editText7);
        ed8 = (EditText) findViewById(R.id.editText8);
        app = (MyApplication) getApplication();
        Intent it = getIntent();
        ed5.setText(it.getStringExtra("ID"));
        ed6.setText(it.getStringExtra("name"));
        ed7.setText(it.getStringExtra("tel"));
        ed8.setText(it.getStringExtra("addr"));
    }
    public void clickCancel(View v)
    {
        finish();
    }
    public void clickUpdate(View v)
    {
        Student s = new Student(Integer.valueOf(ed5.getText().toString()),
                ed6.getText().toString(), ed7.getText().toString(),
                ed8.getText().toString());
        app.dao.update(s);
        finish();
    }
}

