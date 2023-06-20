package com.example.hifzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class individual extends AppCompatActivity {

    TextView name;
    TextView id;
    TextView age;
    TextView class1;
    TextView sabaqTo;
    TextView sabaqFrom;
    TextView sabaqiText;
    TextView manzilText;



    CheckBox checkBox ;
    Button done;
    Button back;
    Data data;

    RecyclerView recyclerView;
    myRecyclerViewAdapter1 adapter;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);
//        name=findViewById(R.id.name1);
//        id=findViewById(R.id.id1);
//        age=findViewById(R.id.age1);
//        class1=findViewById(R.id.class1);
//        sabaqFrom=findViewById(R.id.sabaq1);
//        sabaqTo=findViewById(R.id.sabaq2);
//
//        sabaqiText=findViewById(R.id.sabaqi1);
//        manzilText=findViewById(R.id.manzil1);
        data=new Data();

        List<Students> studentList = new ArrayList<>();

        Intent intent = getIntent();
        Students myObject = (Students) ((Intent) intent).getSerializableExtra("myObject");
        studentList.add(myObject);



//        name.setText(myObject.getName());
//
//
//        id.setText(String.valueOf(myObject.getId()));
//        age.setText(myObject.getAge());
//        class1.setText(myObject.getSclass());
//
//        System.out.println("name"+name.getText().toString());
//        System.out.println("id"+id.getText().toString());
//        System.out.println("age"+age.getText().toString());
//
//        System.out.println("class"+class1.getText().toString());
        System.out.println("Done");

        int sabaq=myObject.getSabaq();
        int sabaqi=myObject.getSabaqi();
        int manzil=myObject.getManzil();

        recyclerView = findViewById(R.id.recylerViewStudent1);


        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(individual.this);
//        layoutManager = new LinearLayoutManager(MainActivity.this,
//                LinearLayoutManager.HORIZONTAL,
//                false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new myRecyclerViewAdapter1(this,studentList) ;
        recyclerView.setAdapter(adapter);


//         String para=data.getParaName(val1.get(0));
//         String sura=data.urduSurahNames[val1.get(1)];
//         String verse=String.valueOf(val1.get(2));
//
//
//        String sab = data.getParaName(sabaqi);
//        String  man=data.getParaName(manzil);
//
//
//
//        System.out.println("sabaq is from para no \t"+para+"\tsurah\t"+sura+"\tverse\t"+verse);
//        sabaqFrom.setText("from para "+para+" surah "+sura+" verse "+verse);
//
//        int sabaq1=myObject.getSabaq();
//        int sabaqi1=myObject.getSabaqi();
//        int manzil1=myObject.getManzil();
//
//        ArrayList<Integer> val2=findParaAndSurah(sabaq+30);
//       // int par=val1.get(0);
//        String para2=data.getParaName(val2.get(0));
//        String sura2=data.urduSurahNames[val2.get(1)];
//        String verse2=String.valueOf(val2.get(2));
//        System.out.println("to para no \t"+para2+"\tsurah\t"+sura2+"\tverse\t"+verse2);
//        sabaqTo.setText("to para "+para2+" surah "+sura2+" verse "+verse2);
//        System.out.println("sabaqi is para no \t"+sab+"\tmanzil\t"+man);
//        sabaqiText.setText(sab+" sabqi");
//        manzilText.setText(man+" man");
////        sabaqi1=par-1;
//        myObject.setSabaq(sabaq1+30);
//        myObject.setSabaqi(sabaqi1);
//        if(manzil1+1<sabaqi)
//        {
//            manzil1++;
//        }
//        else
//        {
//            manzil1=0;
//        }
//        myObject.setManzil(manzil1);


        // checkBox = findViewById(R.id.cb);

        done=findViewById(R.id.button2);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("inside button Learnt");

//                if (!checkBox.isChecked())
//                {
//                    System.out.println("not checked");
//                }
//
//                    if (checkBox.isChecked())
//                    {
                //System.out.println("checked");
                Data d=new Data();

                ArrayList<Integer> val1=findParaAndSurah(sabaq);
                int sabaq1=myObject.getSabaq();
                int sabaqi1=myObject.getSabaqi();
                int manzil1=myObject.getManzil();

                ArrayList<Integer> val2=findParaAndSurah(sabaq+30);
                int par=val1.get(0);
                sabaqi1=par-1;
                myObject.setSabaq(sabaq1+30);
                myObject.setSabaqi(sabaqi1);
                if(manzil1+1<=sabaqi1)
                {
                    System.out.println("manzil"+manzil1+"sabaqi"+sabaqi1);
                    manzil1++;
                }
                else if(sabaqi==0)
                {

                    manzil1=0;
                } else if (manzil1+1>sabaqi) {
                    manzil1=0;


                }
                myObject.setManzil(manzil1);


                DbHelper db=DbHelper.getInstance(individual.this);



                db.updateStudent(myObject);
                System.out.println("Saved");
                Intent intent=new Intent(individual.this,MainActivity.class);
                Toast.makeText(individual.this, "Record updated successfully", Toast.LENGTH_SHORT).show();
                // intent.putExtra("db", (Serializable) db);
                startActivity(intent);
            }




        });





        System.out.println("Done");


        back=findViewById(R.id.button1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(individual.this,MainActivity.class);
                // intent.putExtra("db", (Serializable) db);
                startActivity(intent);

            }
        });






// Perform operations with the receivedObject

    }


    public ArrayList<Integer>findParaAndSurah(int verseNumber)
    {
        ArrayList<Integer> val = new ArrayList<>();
        Data data=new Data();
        List<Integer> paraStartingVerseList = new ArrayList<>();
        for (int i : data.PSP) {
            paraStartingVerseList.add(i);
        }
        List<Integer> paraVerseCountList = new ArrayList<>();
        for (int i : data.paraVerseCountList) {
            paraVerseCountList.add(i);
        }
        List<Integer> surahVerseCountList = new ArrayList<>();
        for (int i : data.surahAyatCount) {
            surahVerseCountList.add(i);
        }

        int para = 1;
        int verseCount = 0;

        // Iterate through the paraStartingVerseList to find the para
        for (int i = 0; i < paraStartingVerseList.size(); i++) {
            verseCount += paraVerseCountList.get(i);
            if (verseNumber <= verseCount) {
                para = i + 1; // Para index starts from 1
                break;
            }
        }

        // Find the corresponding Surah within the para
        int surah = -1;
        verseCount = 0;

        for (int i = 0; i < surahVerseCountList.size(); i++) {
            verseCount += surahVerseCountList.get(i);
            if (verseNumber <= verseCount) {
                surah = i + 1; // Surah index starts from 1
                break;
            }
        }

        // Output the result
        if (surah != -1) {
            int verseInSurah = verseNumber - (verseCount - surahVerseCountList.get(surah - 1));
            System.out.println("Verse " + verseNumber + " is in Para " + para + ", Surah " + (surah-1) + ", Verse " + verseInSurah);
            val.add(para-1);
            val.add(surah-1);
            val.add(verseInSurah);

        } else {
            System.out.println("Invalid verse number");
        }
        return val;
    }


}