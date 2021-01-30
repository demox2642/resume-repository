package com.skilbox.shopinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    int chooseItemPosition;
    private static final String PREFERENCES="PREFERENCES_PRODUCTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        arrayList = new ArrayList<>();

        SharedPreferences preferencesRestore = getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        for (int i = 0; i < preferencesRestore.getInt("lenth",0); i++){
            arrayList.add(preferencesRestore.getString(String.valueOf(i),""));
        }

        listView = findViewById(R.id.productsListVie);
        arrayAdapter = new ArrayAdapter<>(this,R.layout.simple_list_item_single_choice,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chooseItemPosition = position;
            }
        });
    }

    public void addProduct(View view) {
        EditText editText = findViewById(R.id.edit_text_products);
        String stringitem = editText.getText().toString();
        if (stringitem.equals("")){
            Toast toast = Toast.makeText(this,"Введите наименование продукта",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else{
                arrayList.add(stringitem);
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
        }
    }

    public void deleteProduct(View view) {
        if (arrayList.isEmpty()){
            Toast toast = Toast.makeText(this,"Нет данных для удаления",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else {
            arrayList.remove(chooseItemPosition);
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        onSaveData();
    }

    @Override
    public void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);

        onSaveData();
    }

    public void onSaveData(){
        String[] items = arrayList.toArray(new String[0]);
        SharedPreferences preferencesSave = getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesSave.edit();
        for (int i = 0; i < items.length;i++){
            editor.putString(String.valueOf(i), items[i]);
            editor.putInt("lenth", items.length);
            editor.apply();
        }
    }
}