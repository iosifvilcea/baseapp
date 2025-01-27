package com.blankthings.baseapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth().padding(all = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            MessageCard(Message(author = "Joe List", title = "Mark My Words", "Pizza Pasta Masta Musse."))
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

data class Message(
    val author: String,
    val title: String,
    val body: String
)

@Composable
fun MessageCard(msg: Message) {
    Text(text = msg.author)
    Spacer(Modifier.padding(all = 10.dp))
    Text(text = msg.title)
    Spacer(Modifier.padding(all = 10.dp))
    Text(text = msg.body)
    Spacer(Modifier.padding(all = 10.dp))
}