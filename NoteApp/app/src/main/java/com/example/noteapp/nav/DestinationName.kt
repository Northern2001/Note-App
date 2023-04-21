package com.example.noteapp.nav

class DestinationName(isHasParam : Boolean = false) {
    val homeScreen = if (isHasParam){
        "homeScreen"
    }else{
        "homeScreen"
    }
    val detailScreen = if (isHasParam){
        "detailScreen?${ParamName.ID_FOLDER}={${ParamName.ID_FOLDER}}"
    }else{
        "detailScreen"
    }

}