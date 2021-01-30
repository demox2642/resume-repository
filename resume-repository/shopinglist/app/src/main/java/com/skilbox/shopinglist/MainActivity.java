package com.skilbox.shopinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(MainActivity.this,ProductsActivity.class);
                    startActivity(intent);
                }
                if (position == 1){
                    Intent intent = new Intent(MainActivity.this,ClothesActivity.class);
                    startActivity(intent);
                }
                if (position == 2){
                    Intent intent = new Intent(MainActivity.this,ToolsActivity.class);
                    startActivity(intent);
                }
                if (position == 3){
                    Intent intent = new Intent(MainActivity.this,HouseholdGoodsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}