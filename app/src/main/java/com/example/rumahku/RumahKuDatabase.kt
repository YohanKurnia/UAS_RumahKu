package com.example.rumahku

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class RumahKuDatabase {
    private lateinit var database: DatabaseReference
    private lateinit var storage: StorageReference

    fun initDB(){
        database = Firebase.database.reference
        storage = Firebase.storage.reference
    }

    fun tambahRumah(gambar: Drawable, pemilik: String?, alamat: String, deskripsi: String, telepon: String){
        val rumah = Rumah(pemilik, deskripsi, telepon, alamat)
        val key = database.child("rumah").push().key

        val bitmap = (gambar as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        storage.child("gambarRumah").child("$key.jpg").putBytes(data)

        database.child("rumah").child(key!!).setValue(rumah)
    }
}