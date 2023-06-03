package com.example.rumahku

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Rumah(
    var pemilik: String? = "",
    var deskripsi: String? = "",
    var gambar: String? = "",
    var telepon: String? = "",
    var alamat: String? = ""
)