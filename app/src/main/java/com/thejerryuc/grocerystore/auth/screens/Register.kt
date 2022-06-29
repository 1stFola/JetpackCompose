package com.thejerryuc.grocerystore.auth.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thejerryuc.grocerystore.R
import com.thejerryuc.grocerystore.auth.events.AuthEvent
import com.thejerryuc.grocerystore.auth.events.LoginFormEvent
import com.thejerryuc.grocerystore.auth.events.RegisterFormEvent
import com.thejerryuc.grocerystore.auth.viewmodels.AuthViewModel
import com.thejerryuc.grocerystore.auth.viewmodels.RegisterViewModel
import com.thejerryuc.grocerystore.general.components.CustomInputField
import com.thejerryuc.grocerystore.general.components.PasswordInputField
import com.thejerryuc.grocerystore.navigation.destinations.Destination
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Register(navController: NavController, registerViewModel: RegisterViewModel = hiltViewModel(), authViewModel: AuthViewModel) {
    val registerFormState = registerViewModel.state.value
    val isLoading = authViewModel.state.value.isLoading
    LaunchedEffect( Unit ){
        authViewModel.eventFlow.collectLatest { event->
            when(event) {
                is AuthEvent.RegisterSuccess -> {
                    Log.i("register", "Registered successfully")
                }
                else -> {}
            }
        }
    }



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(id = R.drawable.carrot_logo), contentDescription = "")
        }

        Text(
            text = "Sign Up",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Enter your credentials to continue",
            fontSize = 16.sp,
            color = Color(0xff7c7c7c)
        )
        Spacer(modifier = Modifier.height(20.dp))

        CustomInputField(
            value = registerFormState.firstname,
            onValueChange = { newValue ->
                registerViewModel.createEvent(RegisterFormEvent.EnteredFirstname(value = newValue))
            },
            onFocusChange = {
                registerViewModel.createEvent(RegisterFormEvent.FocusChange("firstname"))
            },
            modifier = Modifier.fillMaxWidth(),
            headerText = "Firstname",
            hasError = !registerFormState.firstnameValid,
            errorMessage = registerFormState.firstnameErrorMessage,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))

        CustomInputField(
            value = registerFormState.lastname,
            onValueChange = { newValue ->
                registerViewModel.createEvent(RegisterFormEvent.EnteredLastname(value = newValue))
            },
            onFocusChange = {
                registerViewModel.createEvent(RegisterFormEvent.FocusChange("lastname"))
            },
            modifier = Modifier.fillMaxWidth(),
            headerText = "Lastname",
            hasError = !registerFormState.lastnameValid,
            errorMessage = registerFormState.lastnameErrorMessage,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))


        CustomInputField(
            value = registerFormState.email,
            onValueChange = { newValue ->
                registerViewModel.createEvent(RegisterFormEvent.EnteredEmail(value = newValue))
            },
            onFocusChange = {
                registerViewModel.createEvent(RegisterFormEvent.FocusChange("email"))
            },
            modifier = Modifier.fillMaxWidth(),
            headerText = "Email",
            hasError = !registerFormState.emailValid,
            errorMessage = registerFormState.emailErrorMessage,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordInputField(
            value = registerFormState.password,
            onValueChange = {newValue ->
                registerViewModel.createEvent(RegisterFormEvent.EnteredPassword(value = newValue))
            },
            onFocusChange = {
                registerViewModel.createEvent(RegisterFormEvent.FocusChange("password"))
            },
            modifier = Modifier.fillMaxWidth(),
            headerText = "Password",
            hasError = !registerFormState.passwordValid,
            errorMessage = registerFormState.passwordErrorMessage,
            imeAction = ImeAction.Done
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.Start),

        ) {
            Text(text = "By continuing you agree to our Terms of Service and Privacy Policy. Click here to see!",
                color = Color.Black,
                fontSize = 10.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { authViewModel.createEvent(AuthEvent.Login(
                email = registerFormState.email,
                password = registerFormState.password
            )) },
            modifier = Modifier.fillMaxWidth()
        ) {
            if(isLoading){
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(text = "Register")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            onClick = { navController.navigate(Destination.LoginDestination.route) },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
            Text(text = "Already Signed Up? Go to Login")
        }
    }
}