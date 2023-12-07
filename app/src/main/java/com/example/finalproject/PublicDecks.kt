package com.example.finalproject

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PublicDecks : AppCompatActivity(), View.OnClickListener {

    lateinit var databaseReference : DatabaseReference
    lateinit var mAuth : FirebaseAuth
    lateinit var lvDecks : ListView
    val allDecks: ArrayList<Deck> = ArrayList()
    val myDeckKeys: ArrayList<String> = ArrayList()
    val keyedDecks: MutableMap<String, Deck> = HashMap()
    val personalDecks: ArrayList<Deck> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_decks)

        databaseReference = FirebaseDatabase.getInstance().getReference("Accounts")
        mAuth = FirebaseAuth.getInstance()

        var svDecks : SearchView = findViewById(R.id.svSearchPublic)
        var myDecks : TextView = findViewById(R.id.tvMyDecks)
        var publicDecks : TextView = findViewById(R.id.tvPublicDecks)
        var addDecks : ImageView = findViewById(R.id.abPlusPublic)
        var logout : TextView = findViewById(R.id.tvLogout)
        var viewDecks : ListView = findViewById(R.id.lvDecksPublic)

        myDecks.setOnClickListener(this)
        addDecks.setOnClickListener(this)
        publicDecks.setOnClickListener(this)
        logout.setOnClickListener(this)
    }

    override fun onClick(v : View?) {
        TODO("Not yet implemented")
    }

    interface OnGetDataListener {
        fun onSuccess(dataSnapshot: DataSnapshot?)
        fun onFailure()
    }
}