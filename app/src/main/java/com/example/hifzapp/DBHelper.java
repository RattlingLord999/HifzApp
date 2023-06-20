package com.example.hifzapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper instance;
    private static final String DATABASE_NAME = "students.db";
    private static final String TABLE_NAME = "students";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_CLASS = "class";

    private static final String COLUMN_SABAQ = "sabaq";
    private static final String COLUMN_SABAQI = "sabaqi";
    private static final String COLUMN_MANZIL = "manzil";


    // private static final String COLUMN_ENROLL = "is_enroll";

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //                + COLUMN_ROLLNO + " TEXT,"
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_AGE + " TEXT,"
                + COLUMN_CLASS + " TEXT,"
                + COLUMN_SABAQ + " TEXT,"
                + COLUMN_MANZIL + " TEXT,"
                + COLUMN_SABAQI + " TEXT"
                + ")";
        System.out.println("Executing create");

        db.execSQL(sql);
        int i=getRowCount(db);
//        if (i>=0) {
//            insertDefaultRecords(db);
//        }
        System.out.println("after"+i);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertStudent(Students student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_CLASS, student.getSclass());
        values.put(COLUMN_SABAQ, student.getSabaq());
        values.put(COLUMN_SABAQI, student.getSabaqi());
        values.put(COLUMN_MANZIL, student.getManzil());

        // values.put(COLUMN_ENROLL, student.isEnroll());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public int getRowCount(SQLiteDatabase db) {
        // SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM"+TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }
        System.out.println("value\t"+count);
        return count;
    }

    public int RowCount2() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM"+TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }
        System.out.println("value\t"+count);
        return count;
    }

    private boolean isTableEmpty(SQLiteDatabase db) {
        String countQuery = "SELECT COUNT(*) FROM"+ TABLE_NAME ;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();
        return rowCount == 0;
    }




//    private void insertDefaultRecords(SQLiteDatabase db) {
//        // Insert your default student records here
//        String[] names = {"John", "Alice", "Bob"};
//        for (String name : names) {
//            ContentValues values = new ContentValues();
//            values.put("name", name);
//            db.insert(TABLE_NAME, null, values);
//        }
        // int i=getRowCount();
        //  System.out.println("size\t"+i);

    }

    //    public void updateStudent(Student student) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME, student.getName());
//        values.put(COLUMN_ROLLNO, student.getRollNo());
//        values.put(COLUMN_ENROLL, student.isEnroll());
//
//        db.update(TABLE_NAME, values, COLUMN_ROLLNO + " = ?", new String[] {student.getRollNo()});
//        db.close();
//    }
//
//    public void deleteStudent(String rollNo) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, COLUMN_ROLLNO + " = ?", new String[] {rollNo});
//        db.close();
//    }
    public List<Students> selectAllStudents() {
        List<Students> students = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        /*
        * if (cursorCourses.moveToFirst()) {
            do {

                studentArrayList.add(new StudentModel(cursorCourses.getString(1),
                      cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1 ? true : false));
            } while (cursorCourses.moveToNext());

        }
        * */

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex(COLUMN_AGE));
                @SuppressLint("Range") String sclass = cursor.getString(cursor.getColumnIndex(COLUMN_CLASS));
                @SuppressLint("Range") String sabaq = cursor.getString(cursor.getColumnIndex(COLUMN_SABAQ));
                @SuppressLint("Range") String sabaqi = cursor.getString(cursor.getColumnIndex(COLUMN_SABAQI));
                @SuppressLint("Range") String manzil = cursor.getString(cursor.getColumnIndex(COLUMN_MANZIL));
//                @SuppressLint("Range")  String rollNo = cursor.getString(cursor.getColumnIndex(COLUMN_ROLLNO));
//                @SuppressLint("Range") boolean isEnroll = cursor.getInt(cursor.getColumnIndex(COLUMN_ENROLL))>0;
                Students s=new Students(id,name,age,sclass,sabaq,sabaqi,manzil);

                students.add(s);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        students.size();
        return students;
    }



}