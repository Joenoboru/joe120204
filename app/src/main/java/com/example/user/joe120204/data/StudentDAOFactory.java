package com.example.user.joe120204.data;

import android.content.Context;

/**
 * Created by user on 2016/12/8.
 */


public class StudentDAOFactory {
    public static StudentDAO getStudentDAO(Context context, DAOSource s)
    {
        switch (s)
        {
            case MEMORY:
                return new StudentDAOMemoryImpl();
            case FILE:
                return new StudentDAOFileImpl(context);
            case DB:
                return new StudentDAODBImpl(context);
        }
        return null;
    }
}
