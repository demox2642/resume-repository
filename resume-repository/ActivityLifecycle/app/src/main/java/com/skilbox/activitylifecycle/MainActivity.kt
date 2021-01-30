package com.skilbox.activitylifecycle

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

private var mImageView: ImageView? = null
private val mImageAddress = "https://miro.medium.com/max/2560/1*KiYw9LDmYMie_T3P2kSjXA.jpeg"
private val tag = "MainActivity "

private val email: String = ""
private val password: String = ""
private val egreement: Boolean = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mImageView = findViewById<ImageView>(R.id.imageView)
        val textInputEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val textEditPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val checkBoxAgreement = findViewById<CheckBox>(R.id.checkBox)
        val button = findViewById<Button>(R.id.button)

        DebugLogger.e(tag, "onCreate was called")

        var emailTextChange = false
        var passwordTextChange = false
        var checkBoxAgreemenChange = false

        textInputEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                emailTextChange = s?.isNotEmpty() ?: false
                enableButton(emailTextChange, passwordTextChange, checkBoxAgreemenChange)
            }
        })

        textEditPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passwordTextChange = s?.isNotEmpty() ?: false
                enableButton(emailTextChange, passwordTextChange, checkBoxAgreemenChange)
            }
        })

        checkBoxAgreement.setOnCheckedChangeListener { checkboxView, isChecked ->
            if (isChecked) {
                checkBoxAgreemenChange = true
                enableButton(emailTextChange, passwordTextChange, checkBoxAgreemenChange)
            } else {
                checkBoxAgreemenChange = false
                enableButton(emailTextChange, passwordTextChange, checkBoxAgreemenChange)
            }
        }

        button.setOnClickListener {
            if (isEmailValid(textInputEmail.text.toString())) {
                saveNewUser()
            } else {
                Toast.makeText(this, R.string.emailNotValid, Toast.LENGTH_SHORT).show()
                textInputEmail.setTextColor(Color.RED)
            }
        }

        anr_dialog.setOnClickListener {
            Thread.sleep(80000)
        }

        Glide
            .with(imageView)
            .load(mImageAddress)
            .into(mImageView!!)
    }

    override fun onStart() {
        super.onStart()
        DebugLogger.e(tag, "onStart  was called")
    }

    override fun onResume() {
        super.onResume()
        DebugLogger.e(tag, "onResume  was called")
    }
    override fun onPause() {
        super.onPause()
        DebugLogger.e(tag, "onPause  was called")
    }

    override fun onStop() {
        super.onStop()
        DebugLogger.e(tag, "onStop  was called")
    }

    override fun onDestroy() {
        super.onDestroy()
        DebugLogger.e(tag, "onDestroy  was called")
    }

    fun enableButton(emailText: Boolean, passwordText: Boolean, agreement: Boolean) {
        button.isEnabled = emailText && passwordText && agreement
    }

    private fun saveNewUser() {
        progressBar.visibility = View.VISIBLE
        val groupElement = findViewById<View>(R.id.group)
        groupElement.visibility = View.GONE

        Handler().postDelayed(
            {
                progressBar.visibility = View.GONE
                editTextTextPassword.setText("")
                editTextTextEmailAddress.setText("")
                checkBox.isChecked = false
                groupElement.visibility = View.VISIBLE
                Toast.makeText(this, R.string.performed, Toast.LENGTH_SHORT).show()
            },
            8000
        )
    }

    private fun isEmailValid(email: String?): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

object DebugLogger {
    fun e(tag: String, massage: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, massage)
        }
    }
}
