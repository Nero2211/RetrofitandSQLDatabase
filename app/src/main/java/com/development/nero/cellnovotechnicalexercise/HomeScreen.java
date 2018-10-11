package com.development.nero.cellnovotechnicalexercise;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreen extends AppCompatActivity {

    RelativeLayout aboutUS, search, products;
    RecyclerView recyclerView;
    Context context;
    ArrayList<MyPojo> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        aboutUS = findViewById(R.id.about_us_view);
        search = findViewById(R.id.search_view);
        products = findViewById(R.id.product_view);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        setButtonViewColor(aboutUS, "#e87c41");
        setButtonViewColor(search, "#d183ce");
        setButtonViewColor(products, "#5871af");

        getProducts();
        System.out.print("getProducts");
    }

    public void getProducts(){
        records = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.bitbytec.co.uk/")
                .build();

        Bitbytec_API bitbytec_api = retrofit.create(Bitbytec_API.class);

        final Call<MyPojo> productCall = bitbytec_api.getProducts();
        productCall.enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
                if(response.body() != null){
                    for (int i = 0; i < response.body().getRecords().size(); i++){
                        Records responseRecord = response.body().getRecords().get(i);
                        records.add(i, response.body());
                    }
                    CustomListAdapter customListAdapter = new CustomListAdapter(
                            context, records
                    );
                    recyclerView.setAdapter(customListAdapter);
                }
            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {

            }
        });
    }
//        if(response.body() != null){
//            for(int i = 0; i < response.body().size(); i++){
////                        Records responseRecord = response.body().get(i);
////                        records.add(responseRecord);
//                //}
//                CustomListAdapter adapter = new CustomListAdapter(
//                        context, records);
//                recyclerView.setAdapter(adapter);

    public void setButtonViewColor(RelativeLayout view, String color){
        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(Color.parseColor(color));
    }
}
