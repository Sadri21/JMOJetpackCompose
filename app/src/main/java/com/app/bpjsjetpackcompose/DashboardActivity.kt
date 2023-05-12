package com.app.bpjsjetpackcompose


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.bpjsjetpackcompose.listResource.LayananItem
import com.app.bpjsjetpackcompose.listResource.LayananList
import com.app.bpjsjetpackcompose.listResource.MenuItem
import com.app.bpjsjetpackcompose.ui.theme.BPJSJetpackComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay


class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scrollState = rememberScrollState()
            BPJSJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        TopView()
                        LazyColumn (
                            Modifier
                                .padding(horizontal = 16.dp, vertical = 24.dp)) {
                            item {
                                Text(
                                    text = "Program Layanan",
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp
                                )
                            }
                            item {
                                MenuService()
                            }
                            item {
                                MoreProgram()
                                Text(
                                    text = "Layanan Lainnya",
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(top = 24.dp)
                                )
                            }
                            item {
                                MenuDashboard()
                            }
                            item {
                                Text(
                                    text = "Informasi",
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(top = 24.dp)
                                )
                                SlidingImage()
                            }

                        }
                    }

                }
            }
        }
    }
}

@Composable
fun TopView() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
        .padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.jmo), contentDescription = "",
            modifier = Modifier.height(70.dp))
        Row(modifier = Modifier.alpha(0.7f)) {
            Icon(imageVector = Icons.Filled.Wallet, contentDescription = "", modifier = Modifier.padding(end = 8.dp))
            Text(text = "e-wallet")
        }
        Icon(imageVector = Icons.Filled.Notifications, contentDescription = "", modifier = Modifier.alpha(0.7f))
    }
}

@Composable
fun MenuService() {
    val listService = remember { LayananList.layananListDashboard }
    Column(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) {
        listService.forEach {
            MenuServiceItem(service = it)
        }
    }

}

@Composable
fun MenuDashboard() {
    val listDashboard = remember { LayananList.menuDashboard }
    LazyVerticalGrid(columns = GridCells.Fixed(4), modifier = Modifier
        .padding(top = 16.dp)
        .height(350.dp)) {
        items(
            items = listDashboard,
            itemContent = {
                MenuDashboardItem(menuItem = it)
            })
    }
}

@Composable
fun MenuServiceItem(service: LayananItem) {
    Card(shape = RoundedCornerShape(10.dp), backgroundColor = Color.White, elevation = 4.dp, modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = service.layananImage), contentDescription = "", modifier = Modifier.height(40.dp))
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = service.layananName, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(text = service.layananDescription, fontSize = 14.sp, modifier = Modifier.alpha(0.7f))
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
            Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "", tint = colorResource(
                id = R.color.green
            ), modifier = Modifier.padding(top = 8.dp, end = 8.dp))
        }
    }
}

@Composable
fun MenuDashboardItem(menuItem: MenuItem) {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 8.dp)) {
        Card(border = BorderStroke(1.dp, colorResource(id = R.color.md_grey_100)), shape = RoundedCornerShape(10.dp), modifier = Modifier.clickable {
            if (menuItem.menuName == "Info Program") {
                val intent = Intent(context, ProgramInfoActivity::class.java)
                context.startActivity(intent)
            }
        }) {
            Icon(imageVector = menuItem.menuImage, contentDescription = "", modifier = Modifier
                .padding(16.dp)
                .height(30.dp)
                .width(30.dp), tint = colorResource(
                id = R.color.green
            ))
        }
        Text(text = menuItem.menuName, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 8.dp), fontSize = 14.sp)
    }

}



@Composable
fun MoreProgram() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)) {
        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = colorResource(
                id = R.color.green
            ),
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.2f)
        ){}
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Program Lainnya",
                color = colorResource(id = R.color.green),
                fontSize = 16.sp
            )
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), contentAlignment = Alignment.CenterEnd) {
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "", tint = colorResource(
                id = R.color.green
            ))
        }
    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(id = R.color.green) /* Color.Yellow */,
    unSelectedColor: Color = colorResource(id = R.color.md_grey_500) /* Color.Gray */,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 5000,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }

        // you can remove the surface in case you don't want
        // the transparant bacground
        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = 8.dp
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlidingImage() {
    val images = listOf(
        R.drawable.banner,
        R.drawable.banner2

    )

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        AutoSlidingCarousel(
            itemsCount = images.size,
            itemContent = { index ->
                Image(painter = painterResource(id = images[index]), contentDescription = "", contentScale = ContentScale.Crop,
                    modifier = Modifier.height(150.dp))
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    BPJSJetpackComposeTheme {
        TopView()
    }
}