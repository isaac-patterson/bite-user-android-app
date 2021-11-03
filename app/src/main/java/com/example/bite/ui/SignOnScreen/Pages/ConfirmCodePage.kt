package com.example.bite.ui.SignOnScreen.Pages

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.navigation.navOptions
import com.amazonaws.mobile.auth.core.signin.AuthException
import com.amplifyframework.auth.options.AuthConfirmSignUpOptions
import com.amplifyframework.core.Amplify
import com.example.bite.ui.HomeActivity
import com.example.bite.ui.constants.RouteConstants
import java.lang.Exception

@ExperimentalComposeUiApi
@Composable
fun ConfirmCodeScreen(navController: NavController){
    navController.context
    var confirmationCode by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = confirmationCode,
            onValueChange = { confirmationCode = it },
            label = { Text(text = "Enter 4 digit confirmation code") },
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
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
                Amplify.Auth.confirmSignUp("nzipatterson@gmail.com", confirmationCode, AuthConfirmSignUpOptions.defaults(),
                    {
                        navController.navigate(RouteConstants.signOnPageRoute)
                        Log.i("AuthQuickStart", "Sign up succeeded: $it")
                    },
                    {
                        Log.e ("AuthQuickStart", "Sign up failed", it)
                    }
                )
                navController.navigate(RouteConstants.ConfirmCodePageRoute
                )},
            modifier = Modifier
                .padding(20.dp)
                .width(150.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Confirm code",
                modifier = Modifier.padding(20.dp)
            )
        }

        Button(
            onClick = {
                      Amplify.Auth.resendSignUpCode("nzipatterson@gmail.com",
                          {
                              Log.i("AuthQuickStart", "Sign up succeeded: $it")

                          },
                          {
                              Log.e ("AuthResend Code", "Failed to send", it)

                          })
                },
            modifier = Modifier
                .padding(20.dp)
                .width(150.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Resend Code",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}