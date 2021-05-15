package com.example.mymentoapp.data;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.RegisterDao;
import com.example.mymentoapp.model.Register;
import com.example.mymentoapp.util.MyRoomDatabase;
import java.util.List;

public class RegisterRepository {

    private RegisterDao registerDao;
    private LiveData<List<Register>> allData;

    public RegisterRepository(Application application) {

        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        registerDao =  db.registerDao();
        allData = registerDao.getDetails();

    }

    public void deleteData() {
        registerDao.deleteAll();
    }

    public LiveData<List<Register>> getAllData() {
        return allData;
    }

    public void insertData(Register data) {
        new RegisterRepository.RegisterInsertion(registerDao).execute(data);
    }

    private static class RegisterInsertion extends AsyncTask<Register, Void, Void> {

        private RegisterDao registerDao;

        private RegisterInsertion(RegisterDao registerDao) {

            this.registerDao = registerDao;

        }

        @Override
        protected Void doInBackground(Register... data) {

            registerDao.deleteAll();

            registerDao.insertDetails(data[0]);
            return null;

        }

    }
}
