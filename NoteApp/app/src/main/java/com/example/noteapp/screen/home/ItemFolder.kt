package com.example.noteapp.screen.home


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.model.FolderModel
import com.example.noteapp.ui.theme.borderItemFolder
import com.example.noteapp.ui.theme.colorItemFolder

@Composable
fun ItemFolder(
    model: FolderModel,
    total: Int,
    onLongClick: (FolderModel) -> Unit,
    onClick: (FolderModel) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(colorItemFolder.copy(alpha = if (model.isSelected) 1f else 0f))
            .border(
                1.dp,
                color = if (model.isSelected) colorItemFolder else borderItemFolder,
                RoundedCornerShape(24.dp)
            ).pointerInput(model) {
                detectTapGestures(
                    onTap = {
                        onClick(model)
                    },
                    onLongPress = {
                        onLongClick(model)
                    }
                )
            }.padding(vertical = 12.dp, horizontal = 24.dp)

    ) {
        Text(
            text = model.title?.trim() + if (total > 0) " (${total})" else "",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}