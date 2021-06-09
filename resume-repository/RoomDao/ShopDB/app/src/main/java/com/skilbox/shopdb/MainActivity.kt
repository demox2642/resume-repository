package com.skilbox.shopdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skilbox.shopdb.database.Database

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Database.init(context = this)
    }
}
