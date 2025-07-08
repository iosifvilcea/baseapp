
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AccountScreen(onLogoutClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxHeight()
            .padding(40.dp)
    ) {
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