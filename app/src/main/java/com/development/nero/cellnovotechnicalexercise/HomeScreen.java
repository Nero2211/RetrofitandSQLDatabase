package com.development.nero.cellnovotechnicalexercise;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class HomeScreen extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RelativeLayout aboutUS, search, products, searchQueryBtn;
    EditText searchET;
    TextView productCount;
    RecyclerView recyclerView;
    Context context;
    List<Product> productsList;
    ProductsPayload productsPayload;
    private SQLiteDatabaseHandler db;
    SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        searchQueryBtn = findViewById(R.id.search_Query_btn);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        setButtonViewColor(aboutUS, "#e87c41");
        setButtonViewColor(search, "#d183ce");
        setButtonViewColor(products, "#5871af");

        db = new SQLiteDatabaseHandler(this);
        if(checkInternetState()) {
            getProducts();
        }else{
            Toast.makeText(HomeScreen.this, "Failed to extract data from the API as no Internet connection was found," +
                    "Trying to load from Database", Toast.LENGTH_LONG).show();
            getAllProductsFromDatabase();
        }

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        searchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchET.getText().toString().trim().length() == 0) {
                    Toast.makeText(HomeScreen.this, "Please enter the item name", Toast.LENGTH_SHORT).show();
                }else {
                    populateSearchedDataInRecycler(searchET.getText().toString());
                }
            }
        });

        aboutUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, AboutUs.class);
                startActivity(intent);
            }
        });
    }

    public boolean checkInternetState(){
        boolean internetState;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            internetState = true;
        }
        else {
            internetState = false;
        }
        return internetState;
    }

    public void getProducts(){
        productsList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.bitbytec.co.uk/")
                .build();

        Bitbytec_API bitbytec_api = retrofit.create(Bitbytec_API.class);

        final Call<ProductsPayload> productCall = bitbytec_api.getProducts();
        productCall.enqueue(new Callback<ProductsPayload>() {
            @Override
            public void onResponse(Call<ProductsPayload> call, Response<ProductsPayload> response) {
                if(response.body() != null){
                    productsPayload = response.body();
                    for (int i = 0; i < response.body().getProducts().size(); i++){
                        Product myProduct = new Product(productsPayload.getProducts().get(i).getId(),
                                productsPayload.getProducts().get(i).getName(),
                                productsPayload.getProducts().get(i).getDescription(),
                                productsPayload.getProducts().get(i).getPrice(),
                                productsPayload.getProducts().get(i).getCategoryId(),
                                productsPayload.getProducts().get(i).getCategoryName());

                        if(ifDoesNotItemExists(myProduct)) {
                            db.addRecords(myProduct);
                        }
                    }

                    int count = productsPayload.getProducts().size();
                    productCount.setText(String.valueOf(count));

                    CustomListAdapter customListAdapter = new CustomListAdapter(
                            context, productsPayload.getProducts()
                    );
                    recyclerView.setAdapter(customListAdapter);
                }
            }

            @Override
            public void onFailure(Call<ProductsPayload> call, Throwable t) {
                Toast.makeText(context, "Failed to extract the data from API," +
                        "please try again later", Toast.LENGTH_LONG).show();
            }
        });
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public boolean ifDoesNotItemExists(Product record){
        boolean ifDoesNotItemExists = false;
        Product returnRecord = db.getRecordbyID(Integer.valueOf(record.getId()));
        if(returnRecord == null){
            ifDoesNotItemExists = true;
        }
        return ifDoesNotItemExists;
    }

    public void setButtonViewColor(RelativeLayout view, String color){
        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(Color.parseColor(color));
    }

    public void populateSearchedDataInRecycler(String id){
        if(productsList != null){
            productsList.clear();
        }

        List<Product> products = null;
        try {
            products = db.getRecord(id);
        }catch (Exception e){

        }
        if(products != null && products.size() != 0) {
            ProductsPayload productsPayload = new ProductsPayload();
            productsPayload.setProducts(products);

            CustomListAdapter searchAdapter = new CustomListAdapter(
                    context, products
            );
            productCount.setText(String.valueOf(products.size()));
            recyclerView.setAdapter(searchAdapter);
        }
        else{
            Toast.makeText(HomeScreen.this, "Item not found, please try again!", Toast.LENGTH_LONG).show();
        }
    }

    public void getAllProductsFromDatabase(){
        if(productsList != null) {
            productsList.clear();
        }

        boolean databaseNotFound = false;
        List<Product> products = null;
        try {
            products = db.getRecord(null);
        }catch (Exception e){
            Toast.makeText(HomeScreen.this, "Database not found", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Failure: " + e);
            databaseNotFound = true;
        }
        if(databaseNotFound == false) {
            if (products != null && products.size() != 0) {
                ProductsPayload productsPayload = new ProductsPayload();
                productsPayload.setProducts(products);

                CustomListAdapter searchAdapter = new CustomListAdapter(
                        context, products
                );
                productCount.setText(String.valueOf(products.size()));
                recyclerView.setAdapter(searchAdapter);
            } else {
                Toast.makeText(HomeScreen.this, "No items found in the database!", Toast.LENGTH_LONG).show();
            }
        }
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(HomeScreen.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(HomeScreen.this);
        }
        builder.setTitle("Refresh List")
                .setMessage("Get products list from API? \n (This will clear the searched results)")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(checkInternetState()) {
                            getProducts();
                        }else{
                            Toast.makeText(HomeScreen.this, "Failed to extract data from the API as no Internet connection was found," +
                                    "Trying to load from Database", Toast.LENGTH_LONG).show();
                            getAllProductsFromDatabase();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
