package com.example.finalproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue


class MainActivity : AppCompatActivity() {

    lateinit var datebaseReference : FirebaseDatabase
    lateinit var mAuth : FirebaseAuth
    private val allDecks = ArrayList<Deck>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        datebaseReference = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();


    }
    fun getmAuth() : FirebaseAuth {
        mAuth = FirebaseAuth.getInstance();
        return mAuth
    }

    fun getCurrUser() : FirebaseUser {
        return mAuth.currentUser!!
    }
    fun readAllDecks(rr: DatabaseReference, listener: OnGetDataListener) {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val allDecks = mutableListOf<Deck>()

                for (ds in dataSnapshot.children) {
                    val uid = ds.child("uid").getValue(String::class.java)
                    val author = ds.child("author").getValue(String::class.java)
                    val title = ds.child("title").getValue(String::class.java)
                    val cards = ds.child("cards").getValue<MutableList<List<String>>>()
                    val did = ds.child("deckId").getValue(String::class.java)

                    val thisDeck = Deck(did.toString(),uid.toString(),title.toString(),author.toString(),cards)
                    allDecks.add(thisDeck)
                }

                listener.onSuccess(dataSnapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MainActivity:", "Can't fetch all decks")
            }
        }

        rr.addListenerForSingleValueEvent(eventListener)
    }

    interface OnGetDataListener {
        fun onSuccess(dataSnapshot: DataSnapshot)
        fun onFailure()
    }

    fun getAllDecks(): ArrayList<Deck> {
        return allDecks
    }

}