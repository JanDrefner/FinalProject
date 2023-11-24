package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import java.security.interfaces.EdECKey

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var edtEmail: TextInputEditText = findViewById(R.id.edtLoginEmail)
        var edtPassword: TextInputEditText = findViewById(R.id.edtPass)

        var btnLogin: Button = findViewById(R.id.btnLogin)
        var btnSignup: Button = findViewById(R.id.btnSignup)
        // val allDecks : ArrayList<Deck> = ArrayList<Deck>()


        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val pss = edtPassword.text.toString()


            when {
                email.isEmpty() -> {
                    edtEmail.error = "Please enter an email."
                    edtEmail.requestFocus()
                    return@setOnClickListener
                }

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    edtEmail.error = "Enter a valid email."
                    edtEmail.requestFocus()
                    return@setOnClickListener
                }

                pss.isEmpty() -> {
                    edtPassword.error = "Please enter a password."
                    edtPassword.requestFocus()
                    return@setOnClickListener
                }

                pss.length < 6 -> {
                    edtPassword.error = "Invalid password."
                    edtPassword.requestFocus()
                    return@setOnClickListener
                }
            }

            // Continue with the rest of your code if all validation passes
        }


    }
}