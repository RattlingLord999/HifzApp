package com.example.hifzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddStudent extends AppCompatActivity {
    Button save;
    Button back;
    EditText name;
    EditText age;
    EditText sclass;
    List<Students> studentList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        DbHelper db=DbHelper.getInstance(this);
        int j=db.RowCount2();
        System.out.println("yhis is "+j);
        db.selectAllStudents();
        studentList2=db.selectAllStudents();
        if(studentList2==null ||  studentList2.isEmpty() )
        {
            System.out.println("nothing got");
        }
        for (int i = 0; i < studentList2.size(); i++) {
            String name = studentList2.get(i).getName();
            System.out.println("Index " + i + ": " + name);
        }
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=findViewById(R.id.name);
                age=findViewById(R.id.age);
                sclass=findViewById(R.id.sclass);

                String nameText=name.getText().toString();
                String ageText=age.getText().toString();
                String classText=sclass.getText().toString();

                Students s1=new Students(nameText,ageText,classText);
                db.insertStudent(s1);
                name.setText("");
                age.setText("");
                sclass.setText("");

                Toast.makeText(AddStudent.this, "Record added successfully", Toast.LENGTH_SHORT).show();



            }
        });

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddStudent.this,MainActivity.class);
                // intent.putExtra("db", (Serializable) db);
                startActivity(intent);
            }
        });



    }
}