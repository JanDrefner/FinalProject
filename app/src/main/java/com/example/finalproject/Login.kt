package com.example.finalproject

import android.accounts.Account
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Firebase database reference and authentication instance
        databaseReference = FirebaseDatabase.getInstance().getReference("Accounts")
        mAuth = FirebaseAuth.getInstance()

        // Initialize views
        val edtEmail: TextInputEditText = findViewById(R.id.edtUsername)
        val edtPassword: TextInputEditText = findViewById(R.id.edtPass)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnSignup: Button = findViewById(R.id.btnSignup)

        // Login button click listener
        btnLogin.setOnClickListener {
            try {
                val email = edtEmail.text.toString().trim()
                val password = edtPassword.text.toString()

                // Validate email and password
                // Validate if the fields are not empty
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    UserLogin(email, password)
                } else {
                    toastMsg(this, "Please enter username and password.")
                }

            } catch (e: Exception) {
                Log.e("Error_Amado", e.message.toString())
            }
        }

        // Signup button click listener
        btnSignup.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }

    private fun UserLogin(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                toastMsg(this, "Login Success!")
                val userUID = mAuth.currentUser?.uid

                userUID?.let { UID ->
                    userAuthentication(UID)
                }
            } else {
                toastMsg(this, "Login Failed!")
                Log.e("Error_Amado", task.exception?.message.toString())
            }
        }
    }

    private fun userAuthentication(UID: String) {
        val userReference = databaseReference.child(UID)
        Log.d("Redirect_Debug", "getting reference")
        userReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Log.i("Redirect_Debug", "done referencing activity")
                    val user = snapshot.getValue(Accounts::class.java)

                    if (user != null) {
                        toastMsg(applicationContext, "Welcome Back, ${user.email}!")

                        Log.i("Redirect_Debug", "Redirecting to PublicDecks activity")
                        startActivity(Intent(this@Login, PublicDecks::class.java))
                    } else {
                        toastMsg(applicationContext, "New User Welcome!")

                        Log.d("Redirect_Debug", "Redirecting to Register activity")
                        startActivity(Intent(this@Login, Register::class.java))
                    }
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error_Amado", "Error checking user existence: ${error.message}")
            }
        })
    }

    fun toastMsg(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
