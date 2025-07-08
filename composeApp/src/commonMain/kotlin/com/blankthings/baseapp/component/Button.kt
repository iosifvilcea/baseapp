package com.blankthings.baseapp.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BaOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp),
    text: String,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        ButtonContent(text = text)
    }
}

@Composable
fun ButtonContent(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}