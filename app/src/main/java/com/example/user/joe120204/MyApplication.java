package com.example.user.joe120204;

import android.app.Application;

import com.example.user.joe120204.data.StudentDAO;

/**
 * Created by user on 2016/12/5.
 */
//建立一個class並繼承Application且在manifests標明使用此class, 這樣在myapplication宣告的變數便為全域變數
public class MyApplication extends Application {
    public StudentDAO dao;
}
