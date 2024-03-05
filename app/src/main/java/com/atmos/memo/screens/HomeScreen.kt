package com.atmos.memo.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.atmos.memo.R
import com.atmos.memo.ui.ellements.CustomRow
import com.atmos.memo.ui.ellements.MemoScreen
import com.atmos.memo.utils.MyPreview

@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current
    val openGitHubLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Handle the result if needed
        }

    val githubUri = Uri.parse(context.getString(R.string.github))
    val intent = Intent(Intent.ACTION_VIEW, githubUri)

    MemoScreen {
        CustomRow(stringResource(R.string.app_name), R.drawable.baseline_edit_24, "File Edit") {
            navController.navigate("FileEdit")
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextButton(
                onClick = { navController.navigate("CardsScreen") },
                modifier = Modifier.size(200.dp)
            ) {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(R.drawable.playing_cards),
                    contentDescription = "Cards",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )

            }
            TextButton(
                onClick = { navController.navigate("KeyboardScreen") },
                modifier = Modifier.size(200.dp),
            ) {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(R.drawable.baseline_keyboard_24),
                    contentDescription = "Keyboard",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
            TextButton(
                onClick = {
                    openGitHubLauncher.launch(intent)
                },
                modifier = Modifier.size(50.dp),
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(R.drawable.github_142_svgrepo_com),
                    contentDescription = "Github",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
        }

    }
}

@Preview
@Composable
fun MyScreenPreview() {
    MyPreview(screenContent = { navController ->
        HomeScreen(navController = navController)
    })
}