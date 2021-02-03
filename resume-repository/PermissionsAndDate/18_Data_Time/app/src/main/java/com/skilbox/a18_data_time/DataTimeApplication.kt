package com.skilbox.a18_data_time

import android.app.Application
import android.content.res.Configuration
import com.jakewharton.threetenabp.AndroidThreeTen

class DataTimeApplication : Application() {

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        AndroidThreeTen.init(this)
    }
}
