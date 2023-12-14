package com.example.finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddCards : AppCompatActivity(){

/*    var deckId : String = ""
    var username : String = ""
    private val deck = Deck()
    private val cards: MutableList<List<String>> = mutableListOf()*/
    private lateinit var txtExit: TextView
    private lateinit var txtCreate: TextView
    private lateinit var edtFront: EditText
    private lateinit var edtBack: EditText
    private lateinit var imgSave: ImageView
    private lateinit var imgAdd: ImageView
    private lateinit var edtAddTitle: EditText

    private lateinit var databaseReference : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cards)

        txtExit = findViewById(R.id.txtExit)
        txtCreate = findViewById(R.id.txtCreate)
        edtBack = findViewById(R.id.edtBack)
        edtFront = findViewById(R.id.edtFront)
        edtAddTitle = findViewById(R.id.edtAddTitle)
        imgSave = findViewById(R.id.imgExit)
        imgAdd = findViewById(R.id.imgSignout)

        databaseReference = FirebaseDatabase.getInstance().getReference("Cards")

        imgSave.setOnClickListener {
            val intent = Intent(this,PublicDecks::class.java)
            startActivity(intent)
        }

        txtExit.setOnClickListener {
            val intent = Intent(this,PublicDecks::class.java)
            startActivity(intent)
        }

        txtCreate.setOnClickListener {
            saveCards();
        }

        imgAdd.setOnClickListener {
            saveCards();
        }
    }
        /*val bundle = intent.extras
        if (bundle != null) {

        } else {
            val date = Date()
            deckId = (date.time).toString()
            deckId = deckId.substring(deckId.length-9)
        }*/


    private fun saveCards(){
        //Get Values
        val front = edtFront.text.toString()
        val back = edtBack.text.toString()
        val title = edtAddTitle.text.toString()

        if(front.isEmpty()){
            edtFront.error = "Please enter Front Value!"
        }
        else if(back.isEmpty()){
            edtBack.error = "Please enter Back Value!"
        }
        else if(title.isEmpty()){
            edtAddTitle.error = "Please enter Title!"
        }  else {
            val cardId = databaseReference.push().key!!

            val cards = Cards(cardId, front, back, title)

            databaseReference.child(cardId).setValue(cards)
                .addOnCompleteListener{

                    Toast.makeText(this, "Card Created Successfully", Toast.LENGTH_SHORT).show()
                    edtFront.text.clear()
                    edtBack.text.clear()
                    edtAddTitle.text.clear()

                } .addOnFailureListener{ err ->

                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()

                }
        }


    }

    /*override fun onClick(v : View) {
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
    }*/
}

