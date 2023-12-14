package com.example.finalproject

data class Deck(
    var title: String = "",
    var deckId: String = "",
    var author: String = "",
    var uId: String? = "",
    var cards : List<List<String>> = ArrayList()

) {
    constructor() : this("", "", "","", ArrayList())
}










