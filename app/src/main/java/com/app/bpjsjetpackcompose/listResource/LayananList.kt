package com.app.bpjsjetpackcompose.listResource

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.app.bpjsjetpackcompose.R

object LayananList {
    val layananListDashboard = listOf(
        LayananItem(
            layananName = "Jaminan Hari Tua",
            layananDescription = "Anda sudah terdaftar di layanan ini",
            layananImage = R.drawable.jht
        ),
        LayananItem(
            layananName = "Jaminan Kecelakaan Kerja",
            layananDescription = "Anda sudah terdaftar di layanan ini",
            layananImage = R.drawable.jkk
        )
    )

    val layananListMenu = listOf(
        LayananItem(
            layananName = "Jaminan Hari Tua",
            layananDescription = "Anda sudah terdaftar di layanan ini",
            layananImage = R.drawable.jht
        ),
        LayananItem(
            layananName = "Jaminan Kecelakaan Kerja",
            layananDescription = "Anda sudah terdaftar di layanan ini",
            layananImage = R.drawable.jkk
        ),
        LayananItem(
            layananName = "Jaminan Kematian",
            layananDescription = "Anda sudah terdaftar di layanan ini",
            layananImage = R.drawable.jk
        ),
        LayananItem(
            layananName = "Jaminan Pensiun",
            layananDescription = "Anda sudah terdaftar di layanan ini",
            layananImage = R.drawable.jp
        ),
        LayananItem(
            layananName = "Jaminan Kehilangan Pekerjaan",
            layananDescription = "Anda sudah terdaftar di layanan ini",
            layananImage = R.drawable.jp
        )


    )

    val menuDashboard = listOf(
        MenuItem(
            menuName = "Info Program",
            menuImage = Icons.Filled.CardMembership
        ),
        MenuItem(
            menuName = "Bayar/AutoDebit",
            menuImage = Icons.Filled.Payments
        ),
        MenuItem(
            menuName = "Daftar BPU",
            menuImage = Icons.Filled.ManageAccounts
        ),
        MenuItem(
            menuName = "Pengkinian Data",
            menuImage = Icons.Filled.RestorePage
        ),
        MenuItem(
            menuName = "Kantor Cabang",
            menuImage = Icons.Filled.Apartment
        ),
        MenuItem(
            menuName = "Mitra Layanan",
            menuImage = Icons.Filled.MapsHomeWork
        ),
        MenuItem(
            menuName = "Pengaduan",
            menuImage = Icons.Filled.Drafts
        ),
        MenuItem(
            menuName = "Bantuan",
            menuImage = Icons.Filled.HeadsetMic
        ),
        MenuItem(
            menuName = "Menu Lainnya",
            menuImage = Icons.Filled.MoreHoriz
        )
    )
}