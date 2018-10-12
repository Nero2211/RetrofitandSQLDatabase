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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreen extends AppCompatActivity {

    RelativeLayout aboutUS, search, products, searchQueryBtn;
    EditText searchET;
    TextView productCount;
    RecyclerView recyclerView;
    Context context;
    ArrayList<MyPojo> myPojoArrayList;
    private SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        aboutUS = findViewById(R.id.about_us_view);
        search = findViewById(R.id.search_view);
        products = findViewById(R.id.product_view);
        recyclerView = findViewById(R.id.recyclerView);
        searchET = findViewById(R.id.searchEditText);
        productCount = findViewById(R.id.product_Count);

        searchQueryBtn = findViewById(R.id.search_Query_btn);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        setButtonViewColor(aboutUS, "#e87c41");
        setButtonViewColor(search, "#d183ce");
        setButtonViewColor(products, "#5871af");

        db = new SQLiteDatabaseHandler(this);
        getProducts();

        searchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchET.getText().length() < 0) {
                    Toast.makeText(context, "Please enter the ID number", Toast.LENGTH_SHORT).show();
                }else {
                    populateSearchedDataInRecycler(Integer.parseInt(searchET.getText().toString()));
                }
            }
        });
    }

    public void getProducts(){
        myPojoArrayList = new ArrayList<>();
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
                        myPojoArrayList.add(i, response.body());
                        Records myRecord = new Records(myPojoArrayList.get(i).getRecords().get(i).getId(),
                                myPojoArrayList.get(i).getRecords().get(i).getName(),
                                myPojoArrayList.get(i).getRecords().get(i).getDescription(),
                                myPojoArrayList.get(i).getRecords().get(i).getPrice(),
                                myPojoArrayList.get(i).getRecords().get(i).getCategoryId(),
                                myPojoArrayList.get(i).getRecords().get(i).getCategoryName());

                        db.addRecords(myRecord);
                    }

                    int count = myPojoArrayList.size();
                    productCount.setText(String.valueOf(count));

                    CustomListAdapter customListAdapter = new CustomListAdapter(
                            context, myPojoArrayList
                    );
                    recyclerView.setAdapter(customListAdapter);

                }
            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {

            }
        });
    }

    public void setButtonViewColor(RelativeLayout view, String color){
        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(Color.parseColor(color));
    }

    public void populateSearchedDataInRecycler(int id){
        myPojoArrayList.clear();

        List<Records> recordsList = new ArrayList<>();
        Records myRecord = db.getRecord(id);
        recordsList.add(myRecord);

        MyPojo myPojo = new MyPojo();
        myPojo.setRecords(recordsList);
        myPojoArrayList.add(myPojo);
        CustomListAdapter searchAdapter = new CustomListAdapter(
                context, myPojoArrayList
        );
        recyclerView.setAdapter(searchAdapter);
    }
}
