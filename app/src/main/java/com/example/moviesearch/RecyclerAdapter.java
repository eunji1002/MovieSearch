package com.example.moviesearch;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

import retrofit2.Callback;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private Callback <List<data_model>> c;
    private List<data_model> datalist;



    public RecyclerAdapter(Callback<List<data_model>> c, List<data_model> datalist){
        this.c = c;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        //inflater: xml을 객체화
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        Glide.with(holder.itemView.getContext())
                .load(datalist.get(position).getPoster_url())
                .into(holder.image);
        holder.content1.setText(datalist.get(position).getDetails_url());
     



    }

    @Override
    public int getItemCount() {
        int size = datalist.size();
        return size;
    }

    //Holder: 레이아웃과 연결해서 listView를 만들어주는 역할 (단순 연결)
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image; 
        TextView content1;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            
            content1 = (TextView)itemView.findViewById(R.id.content1);
            image = itemView.findViewById(R.id.image);
        }


    }

}
