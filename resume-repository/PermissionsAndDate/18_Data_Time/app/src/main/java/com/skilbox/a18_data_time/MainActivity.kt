package com.skilbox.a18_data_time

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.ConnectionResult.SERVICE_INVALID
import com.google.android.gms.common.ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE
import com.google.android.gms.common.GooglePlayServicesUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)

        when (status){
            SERVICE_INVALID -> needGoogleService()
            SERVICE_VERSION_UPDATE_REQUIRED-> {
                Toast.makeText(this, "Please update your google play service", Toast.LENGTH_SHORT).show()
                GoogleApiAvailability.getInstance().getErrorDialog(this,status,GOOGLE_PLAY_SERVICES_VERSION_CODE).show()
            }
            else -> startApp()
        }

    }

    private fun startApp() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.conteiner_intraction, DangerousPermissionFragmrnt())
                .commit()
    }

    private fun needGoogleService() {
        AlertDialog.Builder(this)
                .setTitle("Приложение не может быть загружено т.к. не найден Google Service")
                .setCancelable(true)
    }


}
