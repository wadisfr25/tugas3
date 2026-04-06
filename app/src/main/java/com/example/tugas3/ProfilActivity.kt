package com.example.tugas3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val data = intent.getParcelableExtra<Mahasiswa>("data")

        findViewById<TextView>(R.id.tvNama).text = "Nama: ${data?.nama}"
        findViewById<TextView>(R.id.tvNim).text = "NIM: ${data?.nim}"
        findViewById<TextView>(R.id.tvProdi).text = "Prodi: ${data?.prodi}"
        findViewById<TextView>(R.id.tvGender).text = "Gender: ${data?.gender}"
        findViewById<TextView>(R.id.tvHobi).text = "Hobi: ${data?.hobi}"
    }
}