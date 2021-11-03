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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.example.bite.ui.constants.RouteConstants
import com.example.bite.ui.theme.BiteTheme
import com.google.accompanist.insets.ProvideWindowInsets

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SignUpScreen(navController: NavController){
    ProvideWindowInsets {
        BiteTheme {
            var signUpPassword by rememberSaveable { mutableStateOf("") }
            var signUpPasswordConfirmed by rememberSaveable { mutableStateOf("") }
            var signUpEmail by remember { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current

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
                    value = signUpEmail,
                    onValueChange = { signUpEmail = it },
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
                    value = signUpPassword,
                    onValueChange = { signUpPassword = it },
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

                TextField(
                    value = signUpPasswordConfirmed,
                    onValueChange = { signUpPasswordConfirmed = it },
                    label = { Text(text = "Confirm password") },
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
                        if (signUpPassword != signUpPasswordConfirmed) {
                            Log.e ("PasswordsDontMatch", "Passwords dont match")
                            return@Button
                        }

//                        val options = AuthSignUpOptions.builder()
//                            .userAttribute(AuthUserAttributeKey.email(), signUpEmail)
//                            .userAttribute(AuthUserAttributeKey.name(), signUpEmail)
//                            .build()

                        navController.navigate(RouteConstants.ConfirmCodePageRoute)

//                        Amplify.Auth.signUp(signUpEmail, signUpPassword, options,
//                            {
//                                navController.navigate(RouteConstants.ConfirmCodePageRoute)
//                                Log.i("AuthQuickStart", "Sign up succeeded: $it")
//                            },
//                            {
//                                Log.e ("AuthQuickStart", "Sign up failed", it)
//                                navController.navigate(RouteConstants.ConfirmCodePageRoute)
//                            }
//                        )
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