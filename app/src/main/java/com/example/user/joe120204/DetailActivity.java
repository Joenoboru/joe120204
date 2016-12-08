package com.example.user.joe120204;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.user.joe120204.data.Student;

public class DetailActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4;
    MyApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        app = (MyApplication) getApplication();

    }
    @Override
    protected void onResume() {
        super.onResume();
        int ID = getIntent().getIntExtra("ID", 0);
        Student s = app.dao.getItem(ID);
        tv1.setText(String.valueOf(s.ID));
        tv2.setText(s.name);
        tv3.setText(s.tel);
        tv4.setText(s.addr);
    }

    public void clickBack(View v)
    {
        finish();
    }
    public void clickDelete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setTitle("確認刪除");
        builder.setMessage("請問是否要刪除本筆資料");
        builder.setPositiveButton("刪除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                app.dao.delete(Integer.valueOf(tv1.getText().toString()));
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }
    public void clickEdit(View v)
    {
        Intent it = new Intent(DetailActivity.this, EditActivity.class);
        it.putExtra("ID", tv1.getText().toString());
        it.putExtra("name", tv2.getText().toString());
        it.putExtra("tel", tv3.getText().toString());
        it.putExtra("addr", tv4.getText().toString());
        startActivity(it);
    }


}
