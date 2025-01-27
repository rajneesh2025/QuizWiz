package com.example.quizwiz.utils

import com.example.quizwiz.R

object iconsPicker {

    val icons =
        arrayOf(
            R.drawable.ic_icon_1,
            R.drawable.ic_icon_2,
            R.drawable.ic_icon_3,
            R.drawable.ic_icon_4,
            R.drawable.ic_icon_5,
            R.drawable.ic_icon_6,
            R.drawable.ic_icon_7,
            R.drawable.ic_icon_11,
            R.drawable.ic_icon_12,
            R.drawable.ic_icon_13,
            R.drawable.ic_icon_14,
        )
    var currIconIndex = 0

    fun getIcon(): Int {
        currIconIndex = (currIconIndex + 1) % icons.size
        return icons[currIconIndex]
    }
}