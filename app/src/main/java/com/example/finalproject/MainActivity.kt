package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    lateinit var rootRef : FirebaseDatabase
    lateinit var mAuth: FirebaseAuth
    val allDecks = ArrayList<Deck>()
    var Deck = Deck();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootRef = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();


        fun getTitle() : String {
            return Deck.title
        }

        fun getDeckId() : String {
            return Deck.deckId
        }

        fun getAuthor() : String {
            return Deck.author
        }

        fun getUid() : String {
            return Deck.Uid
        }

        fun getCards() : List<List<String>> {
            return Deck.cards
        }

        fun getmAuth() : FirebaseAuth{
            mAuth = FirebaseAuth.getInstance();
            return mAuth;
        }

        fun getCurrUser() : FirebaseUser?{
            mAuth = getmAuth()
            var user = mAuth.currentUser;
            return user
        }
    }
}