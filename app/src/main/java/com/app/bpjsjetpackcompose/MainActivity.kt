package com.app.bpjsjetpackcompose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.bpjsjetpackcompose.realmModel.User
import com.app.bpjsjetpackcompose.ui.theme.BPJSJetpackComposeTheme
import com.app.bpjsjetpackcompose.utils.RealmConfig.createConfigRealm
import com.app.bpjsjetpackcompose.utils.addAllUser
import com.app.bpjsjetpackcompose.utils.isEmailValid
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isShowDialog by remember { mutableStateOf(false) }
            addAllUser()
            BPJSJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        ImageTop()
                        LoginForm(isError = {
                            isShowDialog = it
                        })
                        if (isShowDialog) {
                            Dialog(onDismissRequest = { }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
                                ErrorDialogUI(isError = {
                                    isShowDialog = it
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageTop() {
    Box(modifier = Modifier
        .height(150.dp)
        .padding(top = 64.dp)
        .fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.jmo), contentDescription = "", contentScale = ContentScale.Crop)
    }
}

@Composable
fun LoginForm(isError : (Boolean) -> Unit) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = "Login", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Silahkan login untuk masuk aplikasi", color = Color.Black, modifier = Modifier
                .alpha(0.5f)
                .padding(top = 8.dp), fontSize = 14.sp)
            OutlinedTextField(value = userName,
                onValueChange = {
                    userName = it
                    isEmailError = !isEmailValid(it)
            }, singleLine = true, label = {
                    Text("Email Anda")
            }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = isEmailError,
            trailingIcon = {
                if (isEmailError) {
                    val image = Icons.Filled.Error
                    val description = if (passwordVisibility) "Hide password" else "Show password"
                    Icon(imageVector = image, description, tint = Color.Red)
                }
            })
            if (isEmailError) {
                Text(text = "Email Tidak Valid", color = Color.Red)
            }

            OutlinedTextField(value = password,
                onValueChange = {
                    password = it
                }, singleLine = true, label = {
                    Text("Password Anda")
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisibility)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisibility) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisibility = !passwordVisibility}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
            
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 36.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text ="Lupa Akun ?", color = Color.Red)
                Text(text = "Lupa Kata Sandi ?", color = Color.Red)
            }
            
            Button(onClick = {
//                isError(true)
                val realm =  Realm.open(createConfigRealm())
                val items: RealmResults<User> = realm.query<User>().find()
                Log.e("TAG", "LoginForm: ${items.size}" )
                if (items.any { it.password == password && it.userName == userName }) {
                    val intent = Intent(context, DashboardActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    activity.finish()
                } else {
                    isError(true)
                }
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.green)),
                enabled = (!isEmailError && password != ""),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 46.dp)
                    .padding(horizontal = 32.dp)
                    .height(50.dp)) {
                Text(text = "Login", color = Color.White)
            }




        }
    }
}

@Composable
fun ErrorDialogUI(isError : (Boolean) -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(16.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.background(Color.White)) {
            Image(painter = painterResource(id = R.drawable.not_found), contentDescription = "", contentScale = ContentScale.Fit
                , modifier = Modifier
                    .padding(top = 24.dp)
                    .height(160.dp)
                    .fillMaxWidth())
            Text(
                text = "Akun tidak ditemukan pastikan email dan password anda benar",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .alpha(0.5f)
                    .padding(top = 24.dp),

            )

            Button(onClick = {
                isError(false)
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.green)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
                    .padding(horizontal = 24.dp)
                    .height(50.dp)) {
                Text(text = "Ok", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BPJSJetpackComposeTheme {
        ImageTop()
    }
}