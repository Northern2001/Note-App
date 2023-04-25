package com.example.noteapp.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.R
import com.example.noteapp.base.*
import com.example.noteapp.common.AlertDialog
import com.example.noteapp.common.CommonDatabase
import com.example.noteapp.common.CommonDialog
import com.example.noteapp.common.ReloadManager
import com.example.noteapp.mock.Constants
import com.example.noteapp.model.FolderModel
import com.example.noteapp.nav.DestinationWithParam
import com.example.noteapp.nav.RouterManager
import com.example.noteapp.ui.theme.Color4D000000
import com.example.noteapp.ui.theme.black
import com.example.noteapp.viewmodel.FolderViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(viewModel: FolderViewModel = viewModel()) {
    var isShowBackgroundBottomSheet by remember { mutableStateOf(false) }
    val rootController = RouterManager.current.rootController
    val context = LocalContext.current
    var isShowDiaLogAddNew by remember { mutableStateOf(false) }
    var isShowDiaLogDeleteFolder by remember { mutableStateOf(false) }
    var isShowDiaLogDeleteFile by remember { mutableStateOf(false) }
    val databaseFolder = CommonDatabase.current.getDatabase(context).daoFolder()
    val databaseFile = CommonDatabase.current.getDatabase(context).daoFile()
    var value by remember { mutableStateOf("") }
    var isReload by rememberSaveable { mutableStateOf(true) }
    var typeBottomSheet by remember { mutableStateOf(Constants.CREATE_TYPE_SHEET) }

    fun hiddenBottomSheet() {
        isShowBackgroundBottomSheet = false
    }

    fun showBottomSheet() {
        isShowBackgroundBottomSheet = true
    }

    fun reloadListFile(model: FolderModel) {
        viewModel.listFile = databaseFile.findListWithId(model.idFolder.toString())
        viewModel.reloadListFile()
    }

    fun addNewFolder() {
        if (value.isNotEmpty()) {
            databaseFolder.insertAll(FolderModel(title = value))
            viewModel.listFolder = databaseFolder.getListFolder()
            databaseFolder.refreshFolder(databaseFolder.getListFolder())
            viewModel.listFolder.find { it.isSelected }?.isSelected = false
            viewModel.listFolder[viewModel.listFolder.size - 1].isSelected = true
            reloadListFile(viewModel.listFolder[viewModel.listFolder.size - 1])
            isShowDiaLogAddNew = false
            value = ""
            hiddenBottomSheet()
        }
    }

    fun deleteFolder() {
        databaseFolder.delete(viewModel.modelFolder)
        viewModel.modelFolder.idFolder?.let {
            databaseFile.deleteFolder(it)
        }
        viewModel.listFolder = viewModel.listFolder.toMutableList().also {
            it.remove(viewModel.modelFolder)
        }
        databaseFolder.refreshFolder(databaseFolder.getListFolder())
        reloadListFile(viewModel.modelFolder)
        isShowDiaLogDeleteFolder = false
        hiddenBottomSheet()
    }

    fun deleteFile() {
        databaseFile.delete(viewModel.fileModel)
        reloadListFile(viewModel.modelFolder)
        isShowDiaLogDeleteFile = false
        hiddenBottomSheet()
    }

    LaunchedEffect(Unit) {
        if (isReload) {
            viewModel.listFolder = databaseFolder.getListFolder()
            if (viewModel.listFolder.isNotEmpty()) {
                viewModel.modelFolder = viewModel.listFolder[viewModel.listFolder.size - 1]
                viewModel.modelFolder.isSelected = true
                viewModel.reloadListFolder()
                reloadListFile(viewModel.modelFolder)
            }
        }
        ReloadManager.current.listFileUpdate?.let {
            viewModel.modelFolder.isSelected = true
            viewModel.reloadListFolder()
            reloadListFile(viewModel.modelFolder)
            ReloadManager.current.listFileUpdate = null
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            isReload = false
        }
    }

    BaseBottomSheet(sheetContent = {
        when (typeBottomSheet) {
            Constants.CREATE_TYPE_SHEET -> {
                OptionBottomSheet("Create Folder") {
                    isShowDiaLogAddNew = true
                }
                if (viewModel.listFolder.isEmpty().not()) {
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(black.copy(0.5f))
                    )
                    OptionBottomSheet("Create File") {
                        rootController?.navigate(
                            DestinationWithParam.getDetailParams(
                                viewModel.listFolder.find { it.isSelected }?.idFolder.toString()
                            )
                        )
                    }
                }
            }
            Constants.DELETE_FOLDER_TYPE_SHEET -> {
                OptionBottomSheet("Delete Folder") {
                    isShowDiaLogDeleteFolder = true
                }
            }
            else -> {
                OptionBottomSheet("Delete File") {
                    isShowDiaLogDeleteFile = true
                }
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
                            typeBottomSheet = Constants.CREATE_TYPE_SHEET
                            showBottomSheet()
                        }
                    }
                    Text(text = "My\nNotes", fontSize = 64.sp, fontWeight = FontWeight.Medium)
                    if (viewModel.listFile.isEmpty() && viewModel.listFolder.isEmpty()) {
                        Column(
                            Modifier
                                .fillMaxHeight()
                                .padding(bottom = 100.dp)
                                .clickable {
                                    typeBottomSheet = Constants.CREATE_TYPE_SHEET
                                    showBottomSheet()
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_add_folder),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(100.dp),
                            )
                            Text(
                                text = "Create Folder",
                                color = Color4D000000,
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        LazyRow(modifier = Modifier.padding(top = 20.dp), content = {
                            items(viewModel.listFolder.reversed()) {
                                ItemFolder(model = it,
                                    total = databaseFile.findListWithId(it.idFolder.toString()).size,
                                    onLongClick = { model ->
                                        typeBottomSheet = Constants.DELETE_FOLDER_TYPE_SHEET
                                        showBottomSheet()
                                        viewModel.modelFolder = model
                                    }) { model ->
                                    viewModel.modelFolder = model
                                    viewModel.listFolder.find { it.isSelected }?.isSelected =
                                        false
                                    model.isSelected = !model.isSelected
                                    viewModel.reloadListFolder()
                                    reloadListFile(model)
                                }
                            }
                        })
                        LazyColumn(modifier = Modifier.padding(top = 12.dp), content = {
                            items(viewModel.listFile.reversed()) {
                                ItemFile(it, viewModel.listFolder, onLongClick = { file ->
                                    viewModel.fileModel = file
                                    typeBottomSheet = Constants.DELETE_FILE_TYPE_SHEET
                                    showBottomSheet()
                                }) {
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

            if (isShowDiaLogDeleteFolder) {
                AlertDialog("Delete Folder",
                    des = "Are you sure you want delete this folder",
                    onDismissRequest = {
                        isShowDiaLogDeleteFolder = false
                    },
                    onSubmitRequest = {
                        deleteFolder()
                    })
            }
            if (isShowDiaLogDeleteFile) {
                AlertDialog("Delete File",
                    des = "Are you sure you want delete this File",
                    onDismissRequest = {
                        isShowDiaLogDeleteFile = false
                    },
                    onSubmitRequest = {
                        deleteFile()
                    })
            }
        }
    }
}