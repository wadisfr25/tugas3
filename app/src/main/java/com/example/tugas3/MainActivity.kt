package com.example.tugas3

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etNim: EditText
    private lateinit var spinnerProdi: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var cbMakan: CheckBox
    private lateinit var cbTidur: CheckBox
    private lateinit var cbGame: CheckBox
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setupSpinner()
        setupButton()
    }

    // 🔹 Inisialisasi View
    private fun initView() {
        etNama = findViewById(R.id.etNama)
        etNim = findViewById(R.id.etNim)
        spinnerProdi = findViewById(R.id.spinnerProdi)
        radioGroup = findViewById(R.id.radioGroupGender)
        cbMakan = findViewById(R.id.cbMakan)
        cbTidur = findViewById(R.id.cbTidur)
        cbGame = findViewById(R.id.cbGame)
        btnSubmit = findViewById(R.id.btnSubmit)
    }

    // 🔹 Setup Spinner
    private fun setupSpinner() {
        val prodiList = arrayOf(
            "Pilih Program Studi",
            "Informatika",
            "Sistem Informasi",
            "Teknik Elektro"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            prodiList
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProdi.adapter = adapter
    }

    // 🔹 Setup Button
    private fun setupButton() {
        btnSubmit.setOnClickListener {
            if (validateInput()) {
                kirimData()
            }
        }
    }

    // 🔹 Validasi Input
    private fun validateInput(): Boolean {
        val nama = etNama.text.toString().trim()
        val nim = etNim.text.toString().trim()

        if (nama.isEmpty()) {
            etNama.error = "Nama tidak boleh kosong"
            etNama.requestFocus()
            return false
        }

        if (nim.isEmpty()) {
            etNim.error = "NIM tidak boleh kosong"
            etNim.requestFocus()
            return false
        }

        if (spinnerProdi.selectedItemPosition == 0) {
            Toast.makeText(this, "Pilih Program Studi!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (radioGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Pilih Jenis Kelamin!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    // 🔹 Kirim Data ke Activity berikutnya
    private fun kirimData() {
        val nama = etNama.text.toString()
        val nim = etNim.text.toString()
        val prodi = spinnerProdi.selectedItem.toString()

        val genderId = radioGroup.checkedRadioButtonId
        val gender = findViewById<RadioButton>(genderId).text.toString()

        val hobiList = mutableListOf<String>()
        if (cbMakan.isChecked) hobiList.add("Makan")
        if (cbTidur.isChecked) hobiList.add("Tidur")
        if (cbGame.isChecked) hobiList.add("Gaming")

        val hobi = if (hobiList.isEmpty()) "-" else hobiList.joinToString(", ")

        val data = Mahasiswa(nama, nim, prodi, gender, hobi)

        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }
}