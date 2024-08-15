package com.example.quizwiz.models

data class Quiz(
    var Id : String = "",
    var title : String = "",
    var questions : MutableMap<String , Questions>  = mutableMapOf()

)
