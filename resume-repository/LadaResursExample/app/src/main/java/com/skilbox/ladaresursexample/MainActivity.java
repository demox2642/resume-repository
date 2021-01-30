package com.skilbox.ladaresursexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    public void onChuseCarActivity(View view) {
        Spinner chuseCar = findViewById(R.id.chuseCar);

       // String choosenCar = String.valueOf(chuseCar.getSelectedItemPosition());
        Integer choosenCar = chuseCar.getSelectedItemPosition();
        if (choosenCar == 0){
            Intent intent = new Intent(this,LadaGrantaActivity.class);
            startActivity(intent);
        }else if (choosenCar == 1){
            Intent intent = new Intent(this,LadaPrioraActivity.class);
            startActivity(intent);
        }else if (choosenCar == 2){
            Intent intent = new Intent(this,LadaVestaActivity.class);
            startActivity(intent);
        }else if (choosenCar == 3){
            Intent intent = new Intent(this,LadaNivaActivity.class);
            startActivity(intent);
        }
    }
}
