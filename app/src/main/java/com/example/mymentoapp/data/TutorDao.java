package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.mymentoapp.model.Tutor;
import java.util.List;



@Dao
public interface TutorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Tutor tutor);

    @Query("DELETE FROM tutor_table")
    void deleteAll();

    @Query("SELECT * FROM tutor_table")
    LiveData<List<Tutor>> getAllTutors();

}