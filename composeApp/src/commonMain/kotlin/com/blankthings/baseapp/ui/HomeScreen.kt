package com.blankthings.baseapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.blankthings.baseapp.Routes
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    onNavigateToRoute: (Routes) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Home Screen",
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            onNavigateToRoute = {}
        )
    }
}