package com.example.myapplication.JetPack.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    //Queries
    @Query("SELECT * FROM tbl_student")
    LiveData<List<Student>> getAllStudentLiveData();

    @Query("SELECT * FROM tbl_student")
    List<Student> getAllStudent();

    @Query("SELECT * FROM tbl_student WHERE id=:id")
    Student getStudentById(int id);

    @Query("SELECT * FROM tbl_student WHERE student_id=:studentId")
    Student getStudentByStudentId(String studentId);

    @Query("SELECT * FROM tbl_student WHERE name=:name")
    List<Student> getStudentByName(String name);

    @Query("SELECT * FROM tbl_student WHERE last_name=:lastName")
    List<Student> getStudentByLastName(String lastName);
    //Queries

    @Insert
    void insertStudent(Student... student);

    @Update
    void updateStudent(Student... student);

    @Delete
    void deleteStudent(Student... student);

}
