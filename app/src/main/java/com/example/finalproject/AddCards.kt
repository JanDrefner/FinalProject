package com.example.finalproject

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
    private lateinit var txtAdd: TextView
    private lateinit var edtFront: EditText
    private lateinit var edtBack: EditText
    private lateinit var imgExit: ImageView
    private lateinit var imgAdd: ImageView
    private lateinit var edtAddTitle: EditText

    private lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cards)

        txtExit = findViewById(R.id.txtExit)
        txtAdd = findViewById(R.id.txtAdd)
        edtBack = findViewById(R.id.edtBack)
        edtFront = findViewById(R.id.edtFront)
        edtAddTitle = findViewById(R.id.edtAddTitle)
        imgExit = findViewById(R.id.imgExit)
        imgAdd = findViewById(R.id.imgAdd)

        databaseReference = FirebaseDatabase.getInstance().getReference("Cards")

        imgExit.setOnClickListener {
            val intent = Intent(this,PublicCards::class.java)
            startActivity(intent)
        }

        txtExit.setOnClickListener {
            val intent = Intent(this,PublicCards::class.java)
            startActivity(intent)
        }

        txtAdd.setOnClickListener {
            saveCards();
        }

        imgAdd.setOnClickListener {
            saveCards();
        }
    }

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

}

