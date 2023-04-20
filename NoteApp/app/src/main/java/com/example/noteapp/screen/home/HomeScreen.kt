package com.example.noteapp.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.noteapp.R
import com.example.noteapp.base.BaseBackground
import com.example.noteapp.base.BaseButton
import com.example.noteapp.base.CircleImage
import com.example.noteapp.base.backGroundGradient
import com.example.noteapp.mock.MockFolder
import com.example.noteapp.model.FolderModel
import com.example.noteapp.nav.DestinationName
import com.example.noteapp.nav.DestinationWithParam
import com.example.noteapp.nav.RouterManager
import com.example.noteapp.ui.theme.black
import com.example.noteapp.ui.theme.colorFFFFD5F8
import com.example.noteapp.ui.theme.colorItemFolder

@Composable
fun HomeScreen() {
    var modelFolder by remember { mutableStateOf(FolderModel()) }
    var listFolder by remember { mutableStateOf(MockFolder.listFolder) }
    var rootController = RouterManager.current.rootController

    BaseBackground() {
        Box(Modifier.fillMaxSize()) {
            Column {
                Row(
                    modifier = Modifier.padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(20.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = "", modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Text(text = "Hi, Northern", modifier = Modifier.padding(start = 5.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    CircleImage(R.drawable.ic_menu) {

                    }
                }
                Text(text = "My\nNotes", fontSize = 64.sp, fontWeight = FontWeight.Medium)
                LazyRow(modifier = Modifier.padding(top = 20.dp), content = {
                    items(listFolder) {
                        modelFolder = it
                        ItemFolder(model = it) {
                            listFolder.find { it.isSelected }?.isSelected = false
                            it.isSelected = !it.isSelected
                            val dummy = listFolder
                            listFolder = arrayListOf()
                            listFolder = dummy
                        }
                    }
                })
                LazyColumn(modifier = Modifier.padding(top = 12.dp),content = {
                    items(5){
                        ItemFile()
                    }
                })
            }
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.Transparent)
                    .padding(bottom = 30.dp)
            ) {
                CircleImage(
                    R.drawable.ic_add,
                ) {
                    rootController?.navigate(
                        DestinationWithParam.getDetailParams()
                    )
                }
            }
        }
    }
}