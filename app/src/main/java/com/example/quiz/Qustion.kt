package com.example.quiz

//this will define the question data information that we will create the adapter

//and this will be data class
data class Qustion (
    val id:Int,
    val question:String,
    val image:Int,
    val optionOne:String,
    val optiontwo:String,
    val optionthree:String,
    val optionfour:String,
    val correctAnswer:Int,
)
