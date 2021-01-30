package com.skilbox.myphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toCall(View view) {
        TextView phoneNumber = findViewById(R.id.phoneNumber);
        Intent toCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.getText().toString()));
        startActivity(toCall);

    }

    public void toAuthor(View view) {
        Intent toAuthor = new Intent(this,Author.class);
        startActivity(toAuthor);
    }
}
