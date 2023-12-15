package com.example.finalproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var validatePass = ValidatePass()

        databaseReference = FirebaseDatabase.getInstance().getReference("Accounts")
        mAuth = FirebaseAuth.getInstance()

        var editUsername : TextInputEditText = findViewById(R.id.edtUsername)
        var editEmail : TextInputEditText = findViewById(R.id.edtEmail)
        var editPassword : TextInputEditText = findViewById(R.id.edtPassword1)
        var confirmPassword : TextInputEditText = findViewById(R.id.edtPassword2)
        var buttonRegister : Button = findViewById(R.id.btnRegister)
        var buttonBackLogin : Button = findViewById(R.id.btnBackLogin)

        buttonRegister.setOnClickListener() {
            try {
                val username = editUsername.text.toString()
                val email = editEmail.text.toString()
                val password = editPassword.text.toString()
                val confirmPassword = confirmPassword.text.toString()

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    toastMsg(this, "Fields should not be Empty")
                } else if (password != confirmPassword) {
                    toastMsg(this, "Password does not Match!")
                } else if (!validatePass.ValidatePassword(password)) {
                    toastMsg(this, "Password must be 5-12 characters, contain at least one uppercase letter, one digit, and one special character")
                } else {
                    registerUser(username, email, password)
                }
            } catch (e: Exception) {
                Log.e("Error_Amado", e.message.toString())
            }
        }

        buttonBackLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
    private fun registerUser(username:String, email:String, password:String) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
                task -> if (task.isSuccessful){
            toastMsg(this, "Registration Success!")
            val userUID = mAuth.currentUser?.uid
            userUID?.let {UID -> UserRegistration(UID, username, email, password)
            }
        }else {
            toastMsg(this, "Registration Failed! Try Again")
            Log.e("Error_Amado", task.exception?.message.toString())
        }
        }
    }

    private fun UserRegistration(UID: String, username: String, email: String, password: String)
    {
        val userReference = databaseReference.child(UID)
        val newUser = Accounts(username, email, password)

        userReference.setValue(newUser).addOnCompleteListener{task -> if (task.isSuccessful) {
            toastMsg(this@Register, "Data Saved Successfully!")
            startActivity(Intent(this@Register, Login::class.java))
            finish()
        }else {
            toastMsg(this@Register, "Failed To Save Data")
            Log.e("Error_Amado", task.exception?.message.toString())
        }
        }
    }
    fun toastMsg(context: Context, Msg : String) {
        Toast.makeText(context, Msg, Toast.LENGTH_SHORT).show()
    }
}