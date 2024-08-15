package com.example.quizwiz.utils

object colorPicker {
    val colors =
        arrayOf("#FFC7ED", "#77E4C8","#E178C5", "#FFBE98", "#F5FCCD", "#91DDCF", "#B1AFFF", "#FFF5E0", "#FAFFAF")
    var currColorIndex = 0

    fun getColor(): String {
        currColorIndex = (currColorIndex + 1) % colors.size
        return colors[currColorIndex]
    }



}