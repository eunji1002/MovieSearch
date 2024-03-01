package com.example.moviesearch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Response;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;


public class MainActivity2 extends AppCompatActivity {

    TextView textView_get;


    List<data_model> dataInfo;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView_get = (TextView) findViewById(R.id.textView_get) ;
        dataInfo = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        String text;
        Intent intent = getIntent();
        text = intent.getStringExtra("대사");


        System.out.println("입력 받은 대사 :" + " " + text);


        Call <List<data_model>> call = retrofit_client.getApiService().test_api_get(text);
        call.enqueue(new Callback<List<data_model>>(){
            //콜백 받는 부분
            @Override
            public void onResponse(Call<List<data_model>>call, Response<List<data_model>> response) {
                if (response.isSuccessful()) {
                    textView_get.setText("\"" + text + "\"" + "의\n검색 결과입니다. ");
                    List<data_model> dataInfo = response.body();
                    Log.d("MainActivity2", dataInfo.toString());

                    if(dataInfo.isEmpty()){
                        textView_get.setText("검색 결과가 없습니다.");
                    }


                    else { //📌
                        //Adapter를 이용해서 dataInfo에 있는 내용을 가져와서 저장해둔 listView 형식에 맞게 띄움
                        recyclerAdapter = new RecyclerAdapter(this, dataInfo);
                        recyclerView.setAdapter(recyclerAdapter);
                    }
                }


            }

            @Override
            public void onFailure(Call<List<data_model>> call, Throwable t) {

            }
        });




    }

}
