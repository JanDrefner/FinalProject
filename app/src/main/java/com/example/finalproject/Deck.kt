package com.example.finalproject

import java.io.Serializable

data class Deck(
    var title: String = "",
    var deckId: String = "",
    var author: String = "",
    var uId: String = "",
    var cards: MutableList<List<String>>? = ArrayList()
) : Serializable  {

    constructor() : this("","","","",ArrayList())
}










