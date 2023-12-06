package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AddCards : AppCompatActivity(), View.OnClickListener {

    var deckId : String = ""
    var username : String = ""
    private val deck = Deck()
    private val cards: MutableList<List<String>> = mutableListOf()
    private lateinit var txtFront: TextView
    private lateinit var txtBack: TextView
    private lateinit var txtDone: TextView
    private lateinit var txtAddAnother: TextView
    private lateinit var edtTitle: EditText
    private lateinit var edtFront: EditText
    private lateinit var edtBack: EditText
    private lateinit var imgSave: ImageView
    private lateinit var imgAdd: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cards)

        var txtFront : TextView = findViewById(R.id.txtFront)
        var txtBack : TextView = findViewById(R.id.txtBack)
        var txtDone : TextView = findViewById(R.id.txtDone)
        var txtAddAnother : TextView = findViewById(R.id.txtAddAnother)
        var edtBack : EditText = findViewById(R.id.edtBack)
        var edtFront : EditText = findViewById(R.id.edtFront)
        var edtAddTitle : EditText = findViewById(R.id.edtAddTitle)
        var imgSave : ImageView = findViewById(R.id.imgSave)
        var imgAdd : ImageView = findViewById(R.id.imgAdd)
        
        txtAddAnother.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgSave.setOnClickListener(this);
        txtDone.setOnClickListener(this);

        val bundle = intent.extras
        if (bundle != null) {

        } else {
            val date = Date()
            deckId = (date.time).toString()
            deckId = deckId.substring(deckId.length-9)
        }
    }
    override fun onClick(v : View) {
        when(v.id){
            R.id.imgAdd,
            R.id.txtAddAnother -> {
                if (edtFront.text.toString().isNotEmpty() && edtBack.text.toString().isNotEmpty()){
                    cards.add(listOf(edtFront.text.toString(),edtBack.text.toString()))
                    edtFront.setText("")
                    edtBack.setText("")
                    edtFront.requestFocus()
                } else {
                    Toast.makeText(this, "The Card Content is Incomplete" ,Toast.LENGTH_SHORT);
                }
            }
            R.id.imgSave,
            R.id.txtDone -> {
                if (edtFront.text.toString().isNotEmpty() && edtBack.text.toString().isNotEmpty()){
                    cards.add(listOf(edtFront.text.toString()))
                }
                deck.cards = cards
                deck.title = edtTitle.text.toString()
                deck.author = username
                deck.uId = FirebaseAuth.getInstance().uid.toString()
                deck.deckId = deckId
                if (cards.size == 0){
                    val i = Intent(this@AddCards, PublicDecks::class.java)
                    startActivity(i)
                }

                FirebaseDatabase.getInstance().getReference("Accounts").child(deckId).setValue(deck).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        FirebaseDatabase.getInstance().getReference("Accounts").child(deck.uId).child("MyDecks").child(deckId).setValue(deckId)
                        val intent = Intent(this@AddCards, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@AddCards, "The Deck failed to Upload", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

