package com.app.bpjsjetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.app.bpjsjetpackcompose.ui.theme.BPJSJetpackComposeTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class LayananDetail : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("d") ?: ""
        val image = intent.getIntExtra("image", 0)
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
                        HeaderView(string = name)
                        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                            DescriptionHeader(name, image)
                        }

                        TabLayout()
                    }
                }
            }
        }
    }
}

@Composable
fun DescriptionHeader(name: String, image: Int) {
    Card(shape = RoundedCornerShape(10.dp), backgroundColor = Color.White, elevation = 4.dp, modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 32.dp), ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = Modifier.height(40.dp)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    Text(
                        text = "Anda sudah terdaftar di layanan ini",
                        fontSize = 14.sp,
                        modifier = Modifier.alpha(0.7f)
                    )
                }
                Spacer(modifier = Modifier.weight(1.0f))
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "",
                    tint = colorResource(
                        id = R.color.green
                    )
                )
            }

            Divider(modifier = Modifier.padding(vertical = 16.dp))
            Text(text = "Berupa Uang tunai yang besarnya merupakan nilai akumulasi iuran ditambah hasil pengembangannya", modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Manfaat" ,
        "Besar Iuran"
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = colorResource(id = R.color.white),
        contentColor = colorResource(id = R.color.white),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = colorResource(id = R.color.green)
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) colorResource(id = R.color.green) else Color.Gray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        // on below line we are calling tabs
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    val listString0 = listOf("Akumulasi iuran ditambah hasil pengembangan yang besarnya minimal setara rata-rata tingkat suku bungan depostio bank pemerintah")
    val listString1 = listOf("Besar Iuran Penerima Upah 2% Pekerja 3.7% Perushaan (dari upah yang dilaporkan)", "Besar Iuran Bukan Penerima Upah 2%", "Besar Iuran Pekerja Migran Indonesia:\nRp 50.000.000(Lima puluh ribu rupiah)\nRp 100.000.000(Seratur ribu rupiah)")
    HorizontalPager(state = pagerState, count = 2) {
            page ->
        when (page) {
            0 -> TabContentScreen(data = listString0)
            1 -> TabContentScreen(data = listString1)

        }
    }
}

@Composable
fun TabContentScreen(data: List<String>) {
    // on below line we are creating a column
    val listString = remember { data }
    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
        listString.forEach { 
            Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.Top) {
                Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "", tint = colorResource(
                    id = R.color.green
                ))
                Text(text = it, modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}
