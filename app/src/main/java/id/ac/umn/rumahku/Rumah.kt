package id.ac.umn.rumahku

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Rumah(
    var pemilik: String? = "",
    var deskripsi: String? = "",
    var telepon: String? = "",
    var alamat: String? = "",
    var key: String? = ""
)