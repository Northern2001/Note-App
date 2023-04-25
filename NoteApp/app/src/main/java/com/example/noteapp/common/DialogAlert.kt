package com.example.noteapp.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.noteapp.ui.theme.black

@Composable
fun AlertDialog(
    title: String,
    des: String,
    onDismissRequest: () -> Unit,
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
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Text(
                text = des, modifier = Modifier.padding(vertical = 12.dp)
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
                    text = "Delete",
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