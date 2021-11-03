package com.example.bite.ui.SignOnScreen.Pages

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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amplifyframework.core.Amplify
import com.example.bite.ui.HomeActivity
import com.example.bite.ui.constants.RouteConstants
import com.example.bite.ui.theme.BiteTheme
import com.google.accompanist.insets.ProvideWindowInsets

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SignOnScreen(navController: NavController){
    ProvideWindowInsets {
        BiteTheme {
            var password by rememberSaveable { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current
            val context = LocalContext.current

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    text = "Bite",
                    modifier = Modifier.padding(20.dp)
                )

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Enter email") },
                    maxLines = 1,
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
                    modifier = Modifier.padding(20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Enter password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    modifier = Modifier.padding(20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                Button(
                    onClick = {
                        Amplify.Auth.signIn("bitedevnz@gmail.com", "Isaac7421%!",
                            { result ->
                                if (result.isSignInComplete) {
                                    Log.i("AuthQuickstart", result.toString())
                                    context.startActivity(Intent(context, HomeActivity::class.java))

                                } else {
                                    Log.i("AuthQuickstart", "Sign in not complete")
                                }
                            },
                            { Log.e("AuthQuickstart", "Failed to sign in", it) }
                        )
                    },
                    modifier = Modifier
                        .padding(20.dp)
                        .width(150.dp),
                    shape = RoundedCornerShape(20.dp)

                ) {
                    Text(text = "Log in", color = Color.Black)
                }

                Button(
                    onClick = {
                        navController.navigate(RouteConstants.signUpPageRoute)
                    },
                    modifier = Modifier
                        .padding(20.dp)
                        .width(150.dp),
                    shape = RoundedCornerShape(20.dp)

                ) {
                    Text(text = "Sign Up", color = Color.Black)
                }
            }
        }
    }
}
