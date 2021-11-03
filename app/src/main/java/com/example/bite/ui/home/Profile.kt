

package com.example.bite.ui.home

import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amplifyframework.core.Amplify
import com.example.bite.ui.theme.BiteTheme

@Composable
fun Profile(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as Activity

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = {
                Amplify.Auth.signOut(
                    {
                        Log.i("AuthQuickstart", "Signed out successfully")
                        activity.finish()
                    },
                    { Log.e("AuthQuickstart", "Sign out failed", it) }
                )

            },
            modifier = Modifier
                .padding(20.dp)
                .width(150.dp),
            shape = RoundedCornerShape(20.dp)

        ) {
            Text(text = "Log out", color = Color.Black)
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun ProfilePreview() {
    BiteTheme {
        Profile()
    }
}
