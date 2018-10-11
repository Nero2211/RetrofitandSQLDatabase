package com.development.nero.cellnovotechnicalexercise;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HomeScreen extends AppCompatActivity {

    RelativeLayout aboutUS, search, products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        aboutUS = findViewById(R.id.about_us_view);
        search = findViewById(R.id.search_view);
        products = findViewById(R.id.product_view);

        setButtonViewColor(aboutUS, "#e87c41");
        setButtonViewColor(search, "#d183ce");
        setButtonViewColor(products, "#5871af");
    }


    public void setButtonViewColor(RelativeLayout view, String color){
        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(Color.parseColor(color));
    }
}
