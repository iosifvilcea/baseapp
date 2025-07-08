package com.blankthings.baseapp.component

import androidx.compose.runtime.Composable
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextOverflow
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.app_name
import baseapp.composeapp.generated.resources.login
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
    )
}