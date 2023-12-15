package com.example.finalproject

import android.util.Log
import java.util.regex.Pattern

class ValidatePass {

    public fun ValidatePassword(password : String) : Boolean {
        try {
            if(password.length >= 5 && password.length <= 12 ) {
                if (!password.isNullOrBlank()){
                    var char_of_string = password.toCharArray();
                    var (Uppercase, notUppercase) = char_of_string.partition { it.isUpperCase() };
                    if(!Uppercase.isEmpty() && !notUppercase.isEmpty()){
                        var (isNumber, isNotNumber) = char_of_string.partition { it.isDigit() };
                        if(!isNumber.isEmpty()){
                            val special : Pattern = Pattern.compile("[!@#$%&*()_+=|<>{}\\[\\]~-]")
                            if(special.matcher(password).find()){
                                return true;
                            }
                        }
                    }
                }
            }
            return false
        } catch (e : Exception){
            Log.e("error",e.message.toString());
            return false;
        }
    }

}