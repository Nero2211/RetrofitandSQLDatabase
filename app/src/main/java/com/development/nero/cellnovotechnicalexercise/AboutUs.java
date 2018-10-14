package com.development.nero.cellnovotechnicalexercise;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity {

    Toolbar toolbar;
    RelativeLayout youtube, linkedin, facebook;
    Button visitCallNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar = findViewById(R.id.toolbar);
        youtube = findViewById(R.id.youtubeView);
        linkedin = findViewById(R.id.linkedinView);
        facebook = findViewById(R.id.facebookView);
        visitCallNovo = findViewById(R.id.visitCellNovoBtn);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        visitCallNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openURL("https://www.cellnovo.com/en/homepage");
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openURL("https://www.youtube.com/channel/UCtWIF_oP82-jCO_afd9mwCw");
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openURL("https://www.linkedin.com/company/cellnovo-ltd/");
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openURL("https://www.facebook.com/Cellnovo-170324893037062/");
            }
        });
    }

    public void openURL(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
