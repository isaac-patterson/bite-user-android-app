package com.example.bite.ui.SignOnScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.WindowCompat
import com.amplifyframework.AmplifyException
import com.amplifyframework.core.Amplify
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.example.bite.ui.HomeActivity
import com.example.bite.ui.SignOnApp
import dagger.hilt.android.AndroidEntryPoint

//This activity is called when first opening the app
//It checks if the user is logged in or not and redirects them to activities
@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)

            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }

        Amplify.Auth.fetchAuthSession({
            val session = it as AWSCognitoAuthSession
            if (session.isSignedIn) {
                val mainActivityIntent = Intent(this, HomeActivity::class.java)
                startActivity(mainActivityIntent)

            } else {
                runOnUiThread {
                    setContent {
                        SignOnApp()
                    }                }
            }
        }, {
            print("couldnt fetch auth session")
        })
    }
}
