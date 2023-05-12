package com.app.bpjsjetpackcompose.listResource

import androidx.compose.ui.graphics.vector.ImageVector

data class LayananItem(
    val layananName: String,
    val layananDescription: String,
    val layananImage: Int = 0
)

data class MenuItem(
    val menuName: String,
    val menuImage: ImageVector
)