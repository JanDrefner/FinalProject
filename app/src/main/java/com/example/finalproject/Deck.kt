package com.example.finalproject

import kotlinx.serialization.SerialName
import java.io.Serializable

@kotlinx.serialization.Serializable
data class Deck(
    @SerialName("title") var title: String = "",
    @SerialName("deckId") var deckId: String = "",
    @SerialName("author") var author: String = "",
    @SerialName("uId") var uId: String = "",
    @SerialName("cards") var cards: List<List<String>> = ArrayList()
) {
    constructor() : this("", "", "", "", ArrayList())
}










