package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.mymentoapp.model.Login;

import java.util.List;

@Dao
public interface LoginDao {

    @Insert
    void insertDetails(Login data);

    @Query("select * from login_details")
    LiveData<List<Login>> getDetails();

    @Query("delete from login_details")
    void deleteAll();

}
