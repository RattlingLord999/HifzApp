package com.example.hifzapp;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.MyVH>
{
    Context context;
    List<Students> studentList;
    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

//    private MyVH.OnItemClickListener clickListener;
//
//    public void setOnItemClickListener(MyVH.OnItemClickListener listener) {
//        this.clickListener = listener;
//    }



    public myRecyclerViewAdapter(Context context, List<Students> studentList) {
        this.studentList = studentList;
        this.context=context;
    }

    @NonNull
    @Override
    public myRecyclerViewAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        int r=R.layout.template;
        View itemView =inflater.inflate(r,parent, false);

//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.singe_item, parent, false);

        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerViewAdapter.MyVH holder, int position) {
        holder.data=studentList.get(position);
        TextView a=holder.textViewId;
        System.out.println(a.getText());
        int i=holder.data.getId();
        System.out.println("id is "+i);

        holder.textViewId.setText(String.valueOf(i));
        holder.textViewName.setText(holder.data.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClick(position);

                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public static class MyVH extends RecyclerView.ViewHolder {
        //ImageView imageViewFriend;
        TextView textViewId;
        TextView textViewName;
        Students data;

        public MyVH(@NonNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.textViewId);
            System.out.println(textViewId.getText());
            textViewName = itemView.findViewById(R.id.textViewName);
            System.out.println(textViewName.getText());
        }


    }
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

}



