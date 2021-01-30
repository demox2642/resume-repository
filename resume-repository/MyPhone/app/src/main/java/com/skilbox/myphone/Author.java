package com.skilbox.myphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Author extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
    }

    public void goToSite(View view) {
        Intent intentToSite = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skillbox.ru"));
        startActivity(intentToSite);
    }
}
