package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.RegisterRepository;

import java.util.List;

public class RegisterViewModel extends AndroidViewModel {

    private RegisterRepository repository;
    private LiveData<List<Register>> getAllData;

    public RegisterViewModel(@NonNull Application application) {
        super(application);

        repository = new RegisterRepository(application);
        getAllData = repository.getAllData();

    }

    public void insert(Register data) {
        repository.insertData(data);
    }

    public LiveData<List<Register>> getGetAllData() {
        return getAllData;
    }
}
