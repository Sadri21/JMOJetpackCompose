package com.app.bpjsjetpackcompose

import android.app.Activity
import android.content.Intent
import android.graphics.Paint.Align
import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.bpjsjetpackcompose.listResource.LayananItem
import com.app.bpjsjetpackcompose.listResource.LayananList
import com.app.bpjsjetpackcompose.ui.theme.BPJSJetpackComposeTheme

class ProgramInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BPJSJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()) {
                        HeaderView("Program BPJamsostek")
                        MenuServiceAll()
                    }

                }
            }
        }
    }
}

@Composable
fun HeaderView(string: String) {
    val context = LocalContext.current as Activity
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)) {
        Box(modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = string, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        }
        Box(modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.CenterStart) {
            IconButton(onClick = {context.finish()}) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "", modifier = Modifier.alpha(0.7f))
            }
        }
    }
}

@Composable
fun MenuServiceAll() {
    val listService = remember { LayananList.layananListMenu }

    Column(modifier = Modifier.padding(top = 16.dp)) {
        listService.forEach {
            MenuServiceAllItem(service = it)
        }
    }

}

@Composable
fun MenuServiceAllItem(service: LayananItem) {
    val context = LocalContext.current
    Card(shape = RoundedCornerShape(10.dp), backgroundColor = Color.White, elevation = 4.dp, modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(vertical = 8.dp).clickable {
            val intent = Intent(context, LayananDetail::class.java)
            intent.putExtra("d", service.layananName)
            intent.putExtra("image", service.layananImage)
            context.startActivity(intent)
        }) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = service.layananImage), contentDescription = "", modifier = Modifier.height(40.dp))
            Text(text = service.layananName, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 16.dp))
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
            Icon(imageVector = Icons.Filled.ArrowForwardIos, contentDescription = "", tint = colorResource(
                id = R.color.green
            ), modifier = Modifier.padding(top = 8.dp, end = 8.dp))
        }
    }
}
