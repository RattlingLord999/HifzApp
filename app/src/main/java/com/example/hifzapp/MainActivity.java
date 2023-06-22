package com.example.hifzapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Students> studentList = new ArrayList<>();
    RecyclerView recyclerView;
    myRecyclerViewAdapter adapter;
    Button button;
    Button git;
    Button search;
    EditText edit;

    RecyclerView.LayoutManager layoutManager;
    DbHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        button=findViewById(R.id.addStudent);
        git=findViewById(R.id.git);

        DbHelper db = DbHelper.getInstance(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddStudent.class);

                startActivity(intent);

            }
        });

        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String githubUrl = "https://github.com/RattlingLord999/HifzApp";


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(githubUrl));
                startActivity(intent);

            }
        });
        edit=findViewById(R.id.edit);
        search=findViewById(R.id.search);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(edit.getText().toString());
                if (num < 0) {
                    Toast.makeText(MainActivity.this, "No record found", Toast.LENGTH_SHORT).show();
                }
                Students s = getStudentById(num);
                if (s == null) {
                    Toast.makeText(MainActivity.this, "No record found", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(MainActivity.this, Record.class);
                    intent.putExtra("myObject", s);


                    startActivity(intent);
                }
            }
        });





        SQLiteDatabase db1 = db.getWritableDatabase();



        studentList=db.selectAllStudents();
        if(studentList==null ||  studentList.isEmpty() )
        {
            System.out.println("nothing got");
        }

        recyclerView = findViewById(R.id.recylerViewStudent);


        recyclerView.setHasFixedSize(true);




        layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new myRecyclerViewAdapter(this,studentList) ;
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new myRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Students student = studentList.get(position);

              //  Toast.makeText(MainActivity.this, "Clicked on " + student.getName(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, Record.class);
                intent.putExtra("myObject", student);


                startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();





    }
    public Students getStudentById(int id ) {
        Students student = null;
        DbHelper db = DbHelper.getInstance(this);
        SQLiteDatabase database= db.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM students WHERE id = ?";
            String[] selectionArgs = {String.valueOf(id)};
            cursor = database.rawQuery(query, selectionArgs);
            System.out.println("hello worldjhk");

            if (cursor.moveToFirst()) {
                // Retrieve the student recordy
                @SuppressLint("Range") int id1 = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex("age"));
                @SuppressLint("Range") String sclass = cursor.getString(cursor.getColumnIndex("class"));
                @SuppressLint("Range") int sabaq = Integer.parseInt(cursor.getString(cursor.getColumnIndex("sabaq")));
                @SuppressLint("Range") int sabaqi = Integer.parseInt(cursor.getString(cursor.getColumnIndex("sabaqi")));
                @SuppressLint("Range") int manzil = Integer.parseInt( cursor.getString(cursor.getColumnIndex("manzil")));
                // String name = cursor.getString(cursor.getColumnIndex("name"));
                // Retrieve other columns as needed

                // Create a Student object
                student = new Students(id1, name,age,sclass,sabaq,sabaqi,manzil);
                System.out.println("hello world");
                // ...
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }

        // Handle the case when no matching record is found

        return student;
    }
}




