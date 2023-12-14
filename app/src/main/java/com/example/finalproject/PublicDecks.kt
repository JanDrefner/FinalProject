package com.example.finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapter.CardAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PublicDecks : AppCompatActivity(), View.OnClickListener {

    lateinit var databaseReference : DatabaseReference
    private lateinit var decksRecyclerView : RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var cardList: ArrayList<Cards>
    private lateinit var addDecks : ImageView
    private lateinit var logout : TextView
    private lateinit var logout2 : ImageView
    private lateinit var viewDecks : RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_decks)

        decksRecyclerView = findViewById(R.id.rvDecksPublic)
        decksRecyclerView.layoutManager = LinearLayoutManager(this)
        decksRecyclerView.hasFixedSize()
        tvLoadingData = findViewById(R.id.tvLoadingData)

        cardList = arrayListOf<Cards>()

        getCardsData()

        addDecks = findViewById(R.id.abPlusPublic)
        logout = findViewById(R.id.tvLogout)
        logout2 = findViewById(R.id.imgSignout)
        viewDecks = findViewById(R.id.rvDecksPublic)

        logout.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
        logout2.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }


        addDecks.setOnClickListener{
            val intent =Intent(this, AddCards::class.java)
            startActivity(intent)
        }
    }

    private fun getCardsData(){
        decksRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE
        databaseReference = FirebaseDatabase.getInstance().getReference("Cards")
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                cardList.clear()
                if(snapshot.exists()){
                    for(cardSnap in snapshot.children){
                        val cardData = cardSnap.getValue(Cards::class.java)
                        cardList.add(cardData!!)
                    }
                    val cAdapter = CardAdapter(cardList)
                    decksRecyclerView.adapter = cAdapter

                    decksRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
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

    interface OnGetDataListener {
        fun onSuccess(dataSnapshot: DataSnapshot?)
        fun onFailure()
    }*/