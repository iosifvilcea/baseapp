import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SplashScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Splash Screen",
            textAlign = TextAlign.Center
        )
    }
}