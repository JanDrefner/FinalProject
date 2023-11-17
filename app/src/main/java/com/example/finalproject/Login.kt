package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var edtEmail : TextInputEditText = findViewById(R.id.edtLoginEmail)
        var edtPassword : TextInputEditText = findViewById(R.id.edtPass)

        var btnLogin : Button = findViewById(R.id.btnLogin)
        var btnSignup : Button = findViewById(R.id.btnSignup)
    }
}