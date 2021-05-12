package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.mymentoapp.model.Register;

import java.util.List;

@Dao
public interface RegisterDao {

    @Insert
    void insertDetails(Register data);

    @Query("select * from register_details")
    LiveData<List<Register>> getDetails();

    @Query("delete from register_details")
    void deleteAll();
}
