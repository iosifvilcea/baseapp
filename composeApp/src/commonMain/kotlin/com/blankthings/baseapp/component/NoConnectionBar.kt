package com.blankthings.baseapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.error_no_internet_connection
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

@Composable
fun NoConnectionBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            text = stringResource(Res.string.error_no_internet_connection),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}