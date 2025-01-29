
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AccountScreen(onLogoutClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "Account Screen",
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center
        )
        OutlinedButton(onClick = onLogoutClicked) {
            Text(
                text = "Logout",
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}