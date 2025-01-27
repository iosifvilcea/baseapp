import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SplashScreen(
    navController: NavHostController = rememberNavController()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Splash Screen",
            textAlign = TextAlign.Center
        )
    }
}