package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore

class PublicDecks : AppCompatActivity(), View.OnClickListener {

    var db = Firebase.firestore
    lateinit var databaseReference : DatabaseReference
    lateinit var mAuth : FirebaseAuth
/*    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: MyAdapter*/
    private lateinit var addDecks : ImageView
    private lateinit var accName : TextView
    private lateinit var logout : TextView
    private lateinit var viewDecks : RecyclerView
    private lateinit var publicDecks : TextView
    private lateinit var svDecks : SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_decks)

        Log.i("Logs", db.toString())

        databaseReference = FirebaseDatabase.getInstance().getReference("Decks")
        mAuth = FirebaseAuth.getInstance()

        svDecks = findViewById(R.id.svSearchPublic)
        accName = findViewById(R.id.tvAccName)
        publicDecks = findViewById(R.id.tvPublicDecks)
        addDecks = findViewById(R.id.abPlusPublic)
        logout = findViewById(R.id.tvLogout)
        viewDecks = findViewById(R.id.lvDecksPublic)

        logout.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        addDecks.setOnClickListener{
            val intent =Intent(this, AddCards::class.java)
            startActivity(intent)
        }


        /*val cards : ArrayList<Card> = ArrayList();

        db.collection("Cards")
            *//*.whereEqualTo("username","Dref")*//*
            .get()
            .addOnSuccessListener { documents ->
            for (document in documents){
                var username : String? = document.data.get("username").toString()
                var front : String? = document.data.get("front").toString()
                var back : String? = document.data.get("back").toString()
                *//*Log.i("Logs", document.data.toString())*//*

                val card : Card = Card(username, front, back)
                cards.add(card)

            }
                Log.i("CARDS", cards.toString())
            recyclerView = findViewById(R.id.lvDecksPublic)
            recyclerView.layoutManager = LinearLayoutManager(this)

            //Initializes an instance of MyAdapter and sets it as the RecyclerView's adapter
            adapter = MyAdapter(cards)
            recyclerView.adapter = adapter

        } .addOnFailureListener { exception ->
            Log.w(TAG, "Hello", exception)
        }


    }

    override fun onClick(v : View?) {

            TODO("Not yet implemented")
        }

    interface OnGetDataListener {
        fun onSuccess(dataSnapshot: DataSnapshot?)
        fun onFailure()
    }*/

    }
    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}