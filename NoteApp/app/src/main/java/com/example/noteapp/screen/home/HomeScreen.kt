package com.example.noteapp.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
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
import com.example.noteapp.R
import com.example.noteapp.base.*
import com.example.noteapp.mock.MockFolder
import com.example.noteapp.model.FolderModel
import com.example.noteapp.nav.DestinationWithParam
import com.example.noteapp.nav.RouterManager

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    var modelFolder by remember { mutableStateOf(FolderModel()) }
    var listFolder by remember { mutableStateOf(MockFolder.listFolder) }
    var isShowBackgroundBottomSheet by remember { mutableStateOf(false) }
    var rootController = RouterManager.current.rootController

    fun hiddenBottomSheet() {
        isShowBackgroundBottomSheet = false
    }

    fun showBottomSheet() {
        isShowBackgroundBottomSheet = true
    }

    BaseBottomSheet(
        sheetContent = {
            OptionBottomSheet("Create Folder",R.drawable.ic_folder){
                rootController?.navigate(
                    DestinationWithParam.getDetailParams()
                )
            }
            OptionBottomSheet("Create File",R.drawable.ic_file){

            }
        },
        isShowBackgroundBottomSheet = isShowBackgroundBottomSheet,
        onHidden = { hiddenBottomSheet() }) {
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
                                contentDescription = "",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Text(text = "Hi, Northern", modifier = Modifier.padding(start = 5.dp))
                        Spacer(modifier = Modifier.weight(1f))
                        CircleImage(R.drawable.ic_menu) {
                            showBottomSheet()
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
                    LazyColumn(modifier = Modifier.padding(top = 12.dp), content = {
                        items(5) {
                            ItemFile()
                        }
                    })
                }
            }
        }
    }
}