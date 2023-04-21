package com.example.noteapp.nav

object DestinationWithParam {
    fun getHomeParams(): String {
        return DestinationName().homeScreen
    }

    fun getDetailParams(idFolder: String = "", idFile: String = ""): String {
        return "${DestinationName().detailScreen}?${ParamName.ID_FOLDER}=${idFolder}" +
                "&${ParamName.ID_FILE}=${idFile}"
    }
}