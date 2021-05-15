package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.LoginRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository repository;
    private LiveData<List<Login>> getAllData;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        repository = new LoginRepository(application);
        getAllData = repository.getAllData();

    }

    public void insert(Login data) {
        repository.insertData(data);
    }

    public LiveData<List<Login>> getGetAllData() {
        return getAllData;
    }

}

