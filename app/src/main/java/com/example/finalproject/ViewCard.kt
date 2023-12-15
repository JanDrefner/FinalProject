package com.example.finalproject

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class ViewCard : AppCompatActivity() {
    private lateinit var tvFront: TextView
    private lateinit var tvBack : TextView
    private lateinit var tvTitle: TextView
    private lateinit var btnFlip: Button
    private lateinit var btnHome: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_card)

        init_view()
        setValuesToView()

        btnFlip.setOnClickListener{
            flipCard()
        }

        btnHome.setOnClickListener {
            val intent = Intent(this,PublicCards::class.java)
            startActivity(intent)
        }

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("cardId").toString(),
                intent.getStringExtra("title").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("cardId").toString()
            )
        }
    }
    private fun deleteRecord(cardId : String){
        val databaseReference = FirebaseDatabase.getInstance().getReference("Cards").child(cardId)
        val cTask = databaseReference.removeValue()

        cTask.addOnSuccessListener {
            Toast.makeText(this, "Card Deleted",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PublicCards::class.java)
            finish()
            startActivity(intent)
        } .addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Error ${error.message}",Toast.LENGTH_SHORT).show()
        }
    }
    private fun init_view(){
        tvFront = findViewById(R.id.tvFront)
        tvBack = findViewById(R.id.tvBack)
        tvTitle = findViewById(R.id.txtCardTitle)

        btnFlip = findViewById(R.id.btnFlip)
        btnHome = findViewById(R.id.btnHome)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }
    private fun setValuesToView(){
        tvFront.text = intent.getStringExtra("front")
        tvBack.text = intent.getStringExtra("back")
        tvTitle.text = intent.getStringExtra("title")
    }
    private fun flipCard(){
        if (tvFront.visibility == View.VISIBLE) {
            tvFront.visibility = View.GONE
            tvBack.visibility = View.VISIBLE
        } else {
            tvFront.visibility = View.VISIBLE
            tvBack.visibility = View.GONE
        }
    }

    private fun openUpdateDialog(
        cardId: String,
        title: String
    ) {
        val cDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val cDialogView = inflater.inflate(R.layout.update_cards,null)

        cDialog.setView(cDialogView)

        val edtFront = cDialogView.findViewById<EditText>(R.id.edtUpdateFront)
        val edtBack = cDialogView.findViewById<EditText>(R.id.edtUpdateBack)
        val edtTitle = cDialogView.findViewById<EditText>(R.id.edtUpdateAddTitle)
        val btnUpdate = cDialogView.findViewById<Button>(R.id.btnUpdateData)

        edtFront.setText(intent.getStringExtra("front").toString())
        edtBack.setText(intent.getStringExtra("back").toString())
        edtTitle.setText(intent.getStringExtra("title").toString())

        cDialog.setTitle("Updating $title Record")

        val alertDialog = cDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener{
            updateCardData(
                cardId,
                edtFront.text.toString(),
                edtBack.text.toString(),
                edtTitle.text.toString()
            )

            Toast.makeText(this, "Card Data Updated", Toast.LENGTH_SHORT).show()

            //Updating Data to TextViews
            tvFront.text = edtFront.text.toString()
            tvBack.text = edtBack.text.toString()
            tvTitle.text = edtTitle.text.toString()

            alertDialog.dismiss()
        }
    }
    private fun updateCardData(
        cardId: String,
        front: String,
        back: String,
        title: String
    ){
        val databaseReference = FirebaseDatabase.getInstance().getReference("Cards").child(cardId)
        val cardInfo = Cards(cardId, front, back, title)
        databaseReference.setValue(cardInfo)
    }
}