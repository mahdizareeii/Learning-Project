package com.example.myapplication.JetPack.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentDataBase extends RoomDatabase {

    private static final String DB_NAME = "student.db";
    private static volatile StudentDataBase studentDataBase;

    public static synchronized StudentDataBase getInstance(Context context) {
        if (studentDataBase == null) {
            studentDataBase = create(context);
        }
        return studentDataBase;
    }

    private static StudentDataBase create(Context context) {
        return Room.databaseBuilder(context, StudentDataBase.class, DB_NAME).build();
        //return Room.databaseBuilder(context, StudentDataBase.class, DB_NAME).allowMainThreadQueries().build();
        //the return can be above code but not correct
    }

    public abstract StudentDao getStudentDao();
}
