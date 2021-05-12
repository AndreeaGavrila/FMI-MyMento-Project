package com.example.mymentoapp.data;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.LoginDao;
import com.example.mymentoapp.model.Login;
import com.example.mymentoapp.util.MyRoomDatabase;
import java.util.List;

public class LoginRepository {

    private LoginDao loginDao;
    private LiveData<List<Login>> allData;

    public LoginRepository(Application application) {

        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        loginDao = db.loginDao();
        allData = loginDao.getDetails();

    }

    public void deleteData() {
        loginDao.deleteAll();
    }

    public LiveData<List<Login>> getAllData() {
        return allData;
    }

    public void insertData(Login data) {
        new LoginInsertion(loginDao).execute(data);
    }

    private static class LoginInsertion extends AsyncTask<Login, Void, Void> {

        private LoginDao loginDao;

        private LoginInsertion(LoginDao loginDao) {

            this.loginDao = loginDao;

        }

        @Override
        protected Void doInBackground(Login... data) {

            loginDao.deleteAll();

            loginDao.insertDetails(data[0]);
            return null;

        }

    }

}