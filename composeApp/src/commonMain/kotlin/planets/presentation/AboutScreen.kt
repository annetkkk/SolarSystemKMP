
package planets.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import solarsystemkmp.composeapp.generated.resources.Res
import solarsystemkmp.composeapp.generated.resources.about
import solarsystemkmp.composeapp.generated.resources.app_version
import solarsystemkmp.composeapp.generated.resources.developer

@Composable
fun AboutScreen(
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(Res.string.about))

        Text(text = "SolarSystemKMP")

        Text(
            text = "${stringResource(Res.string.app_version)}: 1.0"
        )

        Text(
            text = "${stringResource(Res.string.developer)}: annetkkk"
        )

        Button(onClick = navigateBack) {
            Text(text = "←")
        }
    }
}
