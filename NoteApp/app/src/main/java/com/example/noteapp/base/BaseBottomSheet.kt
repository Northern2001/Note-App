package com.example.noteapp.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.noteapp.ui.theme.ColorCB0A83
import com.example.noteapp.ui.theme.black
import com.example.noteapp.ui.theme.colorItemFolder
import com.example.noteapp.ui.theme.white

@ExperimentalMaterialApi
@Composable
fun BaseBottomSheet(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    modifierSheetContent: Modifier = Modifier.padding(vertical = 22.dp, horizontal = 12.dp),
    isShowBackgroundBottomSheet: Boolean,
    topBar: (@Composable () -> Unit)? = null,
    snackBarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: (@Composable () -> Unit)? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    sheetGesturesEnabled: Boolean = true,
    sheetElevation: Dp = 0.dp,
    sheetBackgroundColor: Color = Color.Transparent,
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
    sheetPeekHeight: Dp = 0.dp,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerGesturesEnabled: Boolean = true,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    drawerScrimColor: Color = DrawerDefaults.scrimColor,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(backgroundColor),
    isPaddingBottom: Boolean = true,
    onHidden: () -> Unit,
    content: @Composable () -> Unit,
) {
//    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    var isShow by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    LaunchedEffect(isShowBackgroundBottomSheet, block = {
        isShow = isShowBackgroundBottomSheet
        if (isShow) {
            bottomSheetScaffoldState.bottomSheetState.expand()
        } else {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    })
    LaunchedEffect(bottomSheetScaffoldState.bottomSheetState.isCollapsed, block = {
        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
            onHidden()
        }
    })

    BottomSheetScaffold(
        sheetContent = {
            if (isShow) {
                Column(
                    Modifier
                        .then(if (isPaddingBottom) Modifier.navigationBarsPadding() else Modifier)
                        .fillMaxWidth()
                        .clip( RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                        .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } }
                        .padding(top = 2.dp)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .heightIn(0.dp, screenHeight - 50.dp)
                            .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                            .background(white)
                            .then(modifierSheetContent),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        sheetContent()
                    }
                }
            }
        },
        modifier = modifier,
        scaffoldState = bottomSheetScaffoldState,
        topBar = topBar,
        snackbarHost = snackBarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        sheetGesturesEnabled = sheetGesturesEnabled,
        sheetElevation = sheetElevation,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContentColor = sheetContentColor,
        sheetPeekHeight = sheetPeekHeight,
        drawerContent = drawerContent,
        drawerGesturesEnabled = drawerGesturesEnabled,
        drawerShape = drawerShape,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = drawerBackgroundColor,
        drawerContentColor = drawerContentColor,
        drawerScrimColor = drawerScrimColor,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } }
                    .fillMaxHeight()
            ) {
                content()
                if (isShowBackgroundBottomSheet) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(black.copy(0.7f))
                        .clickable {
                            isShow = false
                            onHidden()
                        })
                }
            }
        }
    )
}
