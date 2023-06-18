package com.example.hifzapp;



import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    RecyclerView.LayoutManager layoutManager;
    DbHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hello

//        Students s1=new Students(1,"Ali");
//        Students s2=new Students(1,"Ali2");
//        Students s3=new Students(1,"Ali3");
//        Students s4=new Students(1,"Ali4");
//        Students s5=new Students(1,"Ali5");
//        Students s6=new Students(1,"Ali5");

        button=findViewById(R.id.addStudent);

        DbHelper db = DbHelper.getInstance(this);
        // SQLiteDatabase db = dbHelper.getWritableDatabase();
        //   db = new DbHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Add.class);
                // intent.putExtra("db", (Serializable) db);
                startActivity(intent);

            }
        });



        //int i=db.getRowCount();

        SQLiteDatabase db1 = db.getWritableDatabase();
//        Students s1=new Students("Ali2");
//        db.insertStudent(s1);


        studentList=db.selectAllStudents();
        if(studentList==null ||  studentList.isEmpty() )
        {
            System.out.println("nothing got");
        }
//        studentList.addAll(Arrays.asList(new Students[]{s1,s2,s3,s4,s5,s6}));
        recyclerView = findViewById(R.id.recylerViewStudent);


        recyclerView.setHasFixedSize(true);



        //LinearLayoutManager GridLayoutManager
        layoutManager = new LinearLayoutManager(MainActivity.this);
//        layoutManager = new LinearLayoutManager(MainActivity.this,
//                LinearLayoutManager.HORIZONTAL,
//                false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new myRecyclerViewAdapter(this,studentList) ;
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new myRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Students student = studentList.get(position);
                // Handle item click event here
                // You can access the clicked position or item data
                Toast.makeText(MainActivity.this, "Clicked on " + student.getName(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,individual.class);
                intent.putExtra("myObject", student);

                // intent.putExtra("db", (Serializable) db);
                startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();





    }
}



