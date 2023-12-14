package com.example.finalproject

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

class PublicCards : AppCompatActivity(), View.OnClickListener {

    lateinit var databaseReference : DatabaseReference
    private lateinit var decksRecyclerView : RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var logout : TextView
    private lateinit var cardList: ArrayList<Cards>
    private lateinit var addDecks : ImageView
    private lateinit var imgLogout : ImageView
    private lateinit var viewDecks : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_cards)

        decksRecyclerView = findViewById(R.id.rvDecksPublic)
        decksRecyclerView.layoutManager = LinearLayoutManager(this)
        decksRecyclerView.hasFixedSize()
        tvLoadingData = findViewById(R.id.tvLoadingData)

        cardList = arrayListOf<Cards>()

        getCardsData()

        addDecks = findViewById(R.id.abPlusPublic)
        logout = findViewById(R.id.tvLogout)
        viewDecks = findViewById(R.id.rvDecksPublic)
        imgLogout = findViewById(R.id.imgLogout)

        logout.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        imgLogout.setOnClickListener{
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

                    cAdapter.setOnItemClickListener(object : CardAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@PublicCards, ViewCard::class.java)
                            //put extras
                            intent.putExtra("cardId", cardList[position].cardId)
                            intent.putExtra("front", cardList[position].front)
                            intent.putExtra("back", cardList[position].back)
                            intent.putExtra("title", cardList[position].title)
                            startActivity(intent)
                        }
                    })

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