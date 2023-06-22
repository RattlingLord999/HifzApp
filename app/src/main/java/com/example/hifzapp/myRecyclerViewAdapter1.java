package com.example.hifzapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myRecyclerViewAdapter1 extends RecyclerView.Adapter<myRecyclerViewAdapter1.MyVH>
{
    Context context;
    List<Students> studentList;


//    private MyVH.OnItemClickListener clickListener;
//
//    public void setOnItemClickListener(MyVH.OnItemClickListener listener) {
//        this.clickListener = listener;
//    }



    public myRecyclerViewAdapter1(Context context, List<Students> studentList) {
        this.studentList = studentList;
        this.context=context;
    }

    @NonNull
    @Override
    public myRecyclerViewAdapter1.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        int r=R.layout.individualrecord;
        View itemView =inflater.inflate(r,parent, false);

//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.singe_item, parent, false);

        return new MyVH(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull myRecyclerViewAdapter1.MyVH holder, int position) {
        holder.data=studentList.get(position);
//        TextView a=holder.textViewId;
//        System.out.println(a.getText());
        int i=holder.data.getId();
        System.out.println("id is "+i);

        holder.id.setText(String.valueOf(i));
        holder.name.setText(holder.data.getName());
        holder.age.setText(holder.data.getAge());
        holder.class1.setText(holder.data.getSclass());

        Data data1=new Data();
        Record ind=new Record();
        ArrayList<Integer> val1=ind.findParaAndSurah(holder.data.getSabaq());
        String para=data1.getParaName(val1.get(0));
        String sura=data1.urduSurahNames[val1.get(1)];
        String verse=String.valueOf(val1.get(2));


        String sab = data1.getParaName(holder.data.getSabaqi());
        String  man=data1.getParaName(holder.data.getManzil());
        holder.sabaqiText.setText(sab+" sabqi");
        holder.manzilText.setText(man+" man");



        System.out.println("sabaq is from para no \t"+para+"\tsurah\t"+sura+"\tverse\t"+verse);
        holder.sabaqFrom.setText("from para "+para+" surah "+sura+" verse "+verse);

        ArrayList<Integer> val2=ind.findParaAndSurah(holder.data.getSabaq()+30);
        String para2=data1.getParaName(val2.get(0));
        String sura2=data1.urduSurahNames[val2.get(1)];
        String verse2=String.valueOf(val2.get(2));
        holder.sabaqTo.setText("to para "+para2+" surah "+sura2+" verse "+verse2);
        System.out.println("to para no \t"+para2+"\tsurah\t"+sura2+"\tverse\t"+verse2);






    }



    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public static class MyVH extends RecyclerView.ViewHolder {
        //ImageView imageViewFriend;
        TextView name;
        TextView id;

        // TextView textViewName;
        TextView age;
        TextView class1;
        TextView sabaqTo;
        TextView sabaqFrom;
        TextView sabaqiText;
        TextView manzilText;




        Students data;

        public MyVH(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name1);
            id=itemView.findViewById(R.id.id1);
            age=itemView.findViewById(R.id.age1);
            class1=itemView.findViewById(R.id.class1);
            sabaqFrom=itemView.findViewById(R.id.sabaq1);
            sabaqTo=itemView.findViewById(R.id.sabaq2);

            sabaqiText=itemView.findViewById(R.id.sabaqi1);
            manzilText=itemView.findViewById(R.id.manzil1);

        }




    }

}





