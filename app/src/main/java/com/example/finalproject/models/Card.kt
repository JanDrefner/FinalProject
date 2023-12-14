package com.example.finalproject.models
class Card {
    var username : String? = null
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var front : String? = null
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var back : String? = null
        get() {
            return field
        }
        set(value) {
            field = value
        }
    constructor(username: String?, front: String?, back: String?) {
        this.username = username
        this.front = front
        this.back = back
    }
}

