package com.example.noteapp.nav

class DestinationName(isHasParam : Boolean = false) {
    val splashScreen = if (isHasParam){
        "splashScreen"
    }else{
        "splashScreen"
    }
    val homeScreen = if (isHasParam){
        "homeScreen"
    }else{
        "homeScreen"
    }
    val detailScreen = if (isHasParam){
        "detailScreen?${ParamName.ID_FOLDER}={${ParamName.ID_FOLDER}}" +
                "&${ParamName.ID_FILE}={${ParamName.ID_FILE}}"
    }else{
        "detailScreen"
    }

}