package com.example.bite.ui

import SignUpScreen
import android.content.Intent
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.bite.ui.theme.BiteTheme
import com.google.accompanist.insets.ProvideWindowInsets
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.example.bite.ui.SignOnScreen.Pages.ConfirmCodeScreen
import com.example.bite.ui.SignOnScreen.Pages.SignOnScreen
import com.example.bite.ui.constants.RouteConstants

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SignOnApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "signOn") {
        composable(route = RouteConstants.signOnPageRoute) {
            SignOnScreen(navController)
        }
        composable(route = RouteConstants.signUpPageRoute) {
            SignUpScreen(navController)
        }
        composable(route = RouteConstants.ConfirmCodePageRoute) {
            ConfirmCodeScreen(navController)
        }
    }
}