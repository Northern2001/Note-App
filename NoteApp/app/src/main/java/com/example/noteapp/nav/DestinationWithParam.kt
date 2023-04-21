package com.example.noteapp.nav

object DestinationWithParam {
    fun getHomeParams(): String {
        return DestinationName().homeScreen
    }

    fun getDetailParams(idFolder: String = ""): String {
        return "${DestinationName().detailScreen}?${ParamName.ID_FOLDER}=${idFolder}"
    }
}