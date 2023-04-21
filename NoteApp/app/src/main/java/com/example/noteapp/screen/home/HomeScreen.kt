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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.R
import com.example.noteapp.base.*
import com.example.noteapp.common.CommonDatabase
import com.example.noteapp.common.CommonDialog
import com.example.noteapp.model.FileModel
import com.example.noteapp.model.FolderModel
import com.example.noteapp.nav.DestinationWithParam
import com.example.noteapp.nav.RouterManager
import com.example.noteapp.ui.theme.black

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    var modelFolder by remember { mutableStateOf(FolderModel()) }
    var listFolder by remember { mutableStateOf(listOf<FolderModel>()) }
    var listFile by remember { mutableStateOf(listOf<FileModel>()) }
    var isShowBackgroundBottomSheet by remember { mutableStateOf(false) }
    var rootController = RouterManager.current.rootController
    val context = LocalContext.current
    var isShowDiaLogAddNew by remember { mutableStateOf(false) }
    var databaseFolder = CommonDatabase.current.getDatabase(context).daoFolder()
    var databaseFile = CommonDatabase.current.getDatabase(context).daoFile()
    var value by remember { mutableStateOf("") }

    fun hiddenBottomSheet() {
        isShowBackgroundBottomSheet = false
    }

    fun showBottomSheet() {
        isShowBackgroundBottomSheet = true
    }

    fun reloadListFolder() {
        val dummy = listFolder
        listFolder = arrayListOf()
        listFolder = dummy
    }

    fun reloadListFile(model: FolderModel) {
        listFile = databaseFile.findListWithId(model.idFolder.toString())
        val dummy = listFile
        listFile = arrayListOf()
        listFile = dummy
    }

    fun addNewFolder() {
        if (value.isNotEmpty())
            databaseFolder.insertAll(FolderModel(title = value))
        listFolder = databaseFolder.getListFolder()
        listFolder.find { it.isSelected }?.isSelected = false
        listFolder[listFolder.size - 1].isSelected = true
        reloadListFile(listFolder[listFolder.size - 1])
        value = ""
        reloadListFolder()
        isShowDiaLogAddNew = false
        hiddenBottomSheet()
    }

    LaunchedEffect(Unit) {
        listFolder = databaseFolder.getListFolder()
        listFile = databaseFile.findListWithId(modelFolder.idFolder.toString())
    }

    BaseBottomSheet(
        sheetContent = {
            OptionBottomSheet("Create Folder") {
                isShowDiaLogAddNew = true
            }
            if (listFolder.isEmpty().not()) {
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(black.copy(0.5f))
                )
                OptionBottomSheet("Create File") {
                    rootController?.navigate(
                        DestinationWithParam.getDetailParams(
                            listFolder.find { it.isSelected }?.idFolder.toString()
                        )
                    )
                }
            }
        },
        isShowBackgroundBottomSheet = isShowBackgroundBottomSheet,
        onHidden = { hiddenBottomSheet() }) {
        BaseBackground {
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
                        items(listFolder.reversed()) {
                            modelFolder = it
                            ItemFolder(
                                model = it,
                                total = databaseFile.findListWithId(it.idFolder.toString()).size
                            ) {
                                listFolder.find { it.isSelected }?.isSelected = false
                                it.isSelected = !it.isSelected
                                reloadListFolder()
                                reloadListFile(it)
                            }
                        }
                    })
                    LazyColumn(modifier = Modifier.padding(top = 12.dp), content = {
                        items(listFile.reversed()) {
                            ItemFile(it, listFolder) {
                                rootController?.navigate(
                                    DestinationWithParam.getDetailParams(
                                        idFile = it.idFile.toString()
                                    )
                                )
                            }
                        }
                    })
                }
            }
            if (isShowDiaLogAddNew) {
                CommonDialog(value = value, onChangeValue = {
                    value = it
                }, onDismissRequest = {
                    isShowDiaLogAddNew = false
                    value = ""
                }, onClose = {
                    value = ""
                }) {
                    addNewFolder()
                }
            }
        }
    }
}