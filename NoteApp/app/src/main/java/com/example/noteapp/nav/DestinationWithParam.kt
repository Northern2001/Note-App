package com.example.noteapp.nav

object DestinationWithParam {
    fun getHomeParams() :String{
        return DestinationName().homeScreen
    }

    fun getDetailParams() :String{
        return DestinationName().detailScreen
    }
}