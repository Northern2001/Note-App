package com.example.noteapp.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.noteapp.R
import com.example.noteapp.base.BaseButton
import com.example.noteapp.base.CircleImage
import com.example.noteapp.base.backGroundGradient
import com.example.noteapp.ui.theme.black
import com.example.noteapp.ui.theme.colorFFFFD5F8

@Composable
fun ItemFile() {
    ConstraintLayout(
        Modifier
            .padding(bottom = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .backGroundGradient()
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 20.dp)
    ) {
        var (icon, content, button,forward) = createRefs()
        Box(
            Modifier
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        ) {
            CircleImage(
                icon = R.drawable.ic_save, modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(black)
            ) { }
        }
        Column(
            Modifier
                .constrainAs(content) {
                    top.linkTo(icon.bottom)
                    this.width = Dimension.matchParent
                }
                .padding(top = 40.dp)
        ) {
            Text(
                text = "Technical Drawing Fundamentals",
                fontSize = 29.sp,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = "Class Notes",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
        Column(
            Modifier
                .constrainAs(button) {
                    top.linkTo(content.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 25.dp)) {
            BaseButton("250 words") {
            }
        }
        CircleImage(
            icon = R.drawable.ic_forward, modifier = Modifier
                .constrainAs(forward) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .size(88.dp)
                .clip(RoundedCornerShape(44.dp))
                .background(colorFFFFD5F8)
        ) { }
    }
}