package com.example.finalproject

data class Accounts(
        var username : String = "",
        var email : String = "",
        var password : String = ""
) {
        constructor(): this ("","","")
}
