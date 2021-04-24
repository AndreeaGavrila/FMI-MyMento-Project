package com.example.simpleloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "login.db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate");

        db.execSQL("CREATE TABLE userStudent(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT," +
                " password TEXT, firstname TEXT, lastname TEXT, email TEXT, phonenumber TEXT, studyyear TEXT, domain TEXT)");

        System.out.println("student creat");
        db.execSQL("CREATE TABLE userTutor(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT," +
                " password TEXT, firstname TEXT, lastname TEXT, email TEXT, phonenumber TEXT, studyyear TEXT, domain TEXT, iban TEXT)");
        System.out.println("tutore creat");

        db.execSQL("CREATE TABLE course (ID INTEGER PRIMARY KEY AUTOINCREMENT, coursename TEXT, idTutore REFERENCES userTutore(ID))");
        db.execSQL("CREATE TABLE coursestudent (COURSEID INTEGER PRIMARY KEY REFERENCES course(ID), STUDENTID INTEGER PRIMARY KEY REFERENCES userStudent(ID))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userStudent");
        db.execSQL("DROP TABLE IF EXISTS userTutor");
        db.execSQL("DROP TABLE IF EXISTS course");
        db.execSQL("DROP TABLE IF EXISTS coursestudent");


    }

    public boolean InsertCourse(Course c){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("coursename", c.getCourseName());
        contentValues.put("idTutore", c.getIdTutore());

        long result = sqLiteDatabase.insert("course", null, contentValues);
        if(result == -1){
            return false;
        }else{
            System.out.println("successful course inseration");
            return true;
        }

    }
    public boolean InsertStudent(Student s){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", s.getUserName());
        contentValues.put("password", s.getUserPassword());
        contentValues.put("lastname", s.getLastName());
        contentValues.put("firstname", s.getFirstName());
        contentValues.put("email", s.getEmail());
        contentValues.put("phonenumber", s.getPhoneNumber());
        contentValues.put("studyyear", s.getStudyYear());
        contentValues.put("domain", s.getStudentDomain());

        long result = sqLiteDatabase.insert("userStudent", null, contentValues);
        if(result == -1){
            return false;
        }else{
            System.out.println("successful student inseration");
            return true;
        }
    }

    public boolean InsertTutor(Tutor t){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", t.getUserName());
        contentValues.put("password", t.getUserPassword());
        contentValues.put("lastname", t.getLastName());
        contentValues.put("firstname", t.getFirstName());
        contentValues.put("email", t.getEmail());
        contentValues.put("phonenumber", t.getPhoneNumber());
        contentValues.put("studyyear", t.getStudyYear());
        contentValues.put("domain", t.getStudentDomain());
        contentValues.put("iban", t.getIbanNumber());

        long result = sqLiteDatabase.insert("userTutor", null, contentValues);
        if(result == -1){
            return false;
        }else{
            System.out.println("successful tutor inseration");
            return true;
        }
    }

    public Boolean CheckStudentUsername(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userStudent WHERE username=?", new String[]{username});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }
    public Boolean CheckTutorUsername(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTutor WHERE username=?", new String[]{username});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

    public int getStudent(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userStudent WHERE username=?", new String[]{username});
        System.out.println("Hello from gs");
        if(cursor.moveToFirst() && cursor.getCount() > 0){
            int foundStudent = cursor.getInt(0);
            System.out.println(foundStudent);
            return foundStudent;
        }
        return -1;
    }
    public int getTutor(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTutor WHERE username=?", new String[]{username});
        System.out.println("hello from gt");
        if(cursor.moveToFirst() && cursor.getCount() > 0){
            int foundTutor = cursor.getInt(0);
            System.out.println(foundTutor);
            return foundTutor;
        }
        return -1;
    }
    public boolean updateStudent(int ID, String firstName, String lastName, String email, String phone, String studyYear, String domain)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname",firstName);
        contentValues.put("lastname",lastName);
        contentValues.put("email",email);
        contentValues.put("phonenumber",phone);
        contentValues.put("studyyear",studyYear);
        contentValues.put("domain",domain);
        System.out.println(ID);
        db.update("userStudent", contentValues, "ID =" + ID,null);

        return true;
    }
    public Boolean CheckLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userStudent WHERE username=? AND password=?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public void deleteStudent(String username) {
        int ID = getStudent(username);
        if (ID != -1) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("userStudent", "ID= " + ID, null);
            db.close();
        }
        else{
            System.out.println("doesnt exist ba");
        }
    }
    public void deleteTutor(String username) {
        int ID = getTutor(username);
        if (ID != -1) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("userTutor", "ID=" + ID, null);
            db.close();
            deleteStudent(username);
        }
        else{
            System.out.println("doesnt exist ba");
        }
    }
}
