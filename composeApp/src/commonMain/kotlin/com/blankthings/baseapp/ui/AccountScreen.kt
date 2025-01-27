import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blankthings.baseapp.ui.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AccountScreen(
    navController: NavHostController = rememberNavController()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Account Screen",
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun AccountScreenPreview() {
    MaterialTheme {
        AccountScreen()
    }
}