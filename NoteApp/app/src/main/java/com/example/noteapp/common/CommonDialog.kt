package com.example.noteapp.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.noteapp.R
import com.example.noteapp.ui.theme.black
import com.example.noteapp.ui.theme.borderItemFolder

@Composable
fun CommonDialog(
    value: String,
    onChangeValue: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onClose: () -> Unit,
    onSubmitRequest: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White.copy(0.9f))
        ) {
            Text(
                text = "New Folder",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Text(text = "Enter a name for this folder")

            TextField(modifier = Modifier
                .padding(vertical = 15.dp)
                .clip(RoundedCornerShape(15.dp))
                .height(50.dp), trailingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        onClose()
                    })
            }, value = value, onValueChange = {
                onChangeValue(it)
            },
                colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
                maxLines = 1
                )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(black)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = "Cancel",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onDismissRequest() }
                        .padding(vertical = 15.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(50.dp)
                        .background(black)
                )
                Text(
                    text = "Save",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onSubmitRequest() }
                        .padding(vertical = 15.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}