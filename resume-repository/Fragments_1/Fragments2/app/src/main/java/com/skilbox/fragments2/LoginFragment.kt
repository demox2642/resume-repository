package com.skilbox.fragments2

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_fragment_main.*

class LoginFragment : Fragment(R.layout.login_fragment_main) {

    private val saveUser: LogOn?
        get() = activity?.let { it as LogOn }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mImageView: ImageView? = null

        Log.e("LongFragment", "onCreate was called mImageView=${mImageView}\"")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        Log.e("LongFragment", "onCreateView")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.e("LongFragment", "onActivityCreated was called  ")

        var emailTextChange = false
        var passwordTextChange = false
        var checkBoxAgreemenChange = false

        editTextTextEmailAddress.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                emailTextChange = s?.isNotEmpty() ?: false
                enableButton(emailTextChange, passwordTextChange, checkBoxAgreemenChange)
            }
        })

        editTextTextPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passwordTextChange = s?.isNotEmpty() ?: false
                enableButton(emailTextChange, passwordTextChange, checkBoxAgreemenChange)
            }
        })

        checkBox.setOnCheckedChangeListener { checkboxView, isChecked ->
            if (isChecked) {
                checkBoxAgreemenChange = true
                enableButton(emailTextChange, passwordTextChange, checkBoxAgreemenChange)
            } else {
                checkBoxAgreemenChange = false
                enableButton(emailTextChange, passwordTextChange, checkBoxAgreemenChange)
            }
        }

        button.setOnClickListener {
            if (isEmailValid(editTextTextEmailAddress.text.toString())) {
                saveNewUser()
            } else {
                Toast.makeText(activity, R.string.emailNotValid, Toast.LENGTH_SHORT).show()
                editTextTextEmailAddress.setTextColor(Color.RED)
            }
        }

        Glide
            .with(imageView)
            .load("https://miro.medium.com/max/2560/1*KiYw9LDmYMie_T3P2kSjXA.jpeg")
            .into(imageView!!)
    }

    fun enableButton(emailText: Boolean, passwordText: Boolean, agreement: Boolean) {
        button.isEnabled = emailText && passwordText && agreement
    }

    private fun saveNewUser() {
        progressBar.visibility = View.VISIBLE
        group.visibility = View.GONE
        anr_dialog.visibility = View.GONE

        Handler().postDelayed(
            {
                progressBar.visibility = View.GONE
                editTextTextPassword.setText("")
                editTextTextEmailAddress.setText("")
                checkBox.isChecked = false
                group.visibility = View.VISIBLE
                Toast.makeText(activity, R.string.performed, Toast.LENGTH_SHORT).show()
                saveUser?.newUserIsSaved(true)
            },
            300
        )
    }

    private fun isEmailValid(email: String?): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("LongFragment", "By onDestroyView ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("LongFragment", "By onDetach ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LongFragment", "By onDestroy ")
    }
}
