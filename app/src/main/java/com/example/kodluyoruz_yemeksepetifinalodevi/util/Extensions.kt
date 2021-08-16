package com.example.kodluyoruz_yemeksepetifinalodevi.util

import android.view.View

//PRogressBar'ın görümü ile alakalı fonksiyonları burada yazıp Fragment ın içinde yazmamıza gerek olmasın diye yazıyoruz.

fun View.show(){
    visibility= View.VISIBLE
}

fun View.hide(){
    visibility= View.INVISIBLE
}

fun View.gone(){
    visibility= View.GONE
}