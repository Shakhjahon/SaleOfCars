package com.example.saleofcars

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.SaveCar
import com.example.saleofcars.databinding.ActivityCarSaveBinding
import com.google.android.material.snackbar.Snackbar

class CarSave : AppCompatActivity() {

    private val binding: ActivityCarSaveBinding by lazy {
        ActivityCarSaveBinding.inflate(layoutInflater)
    }
    private val sharedPref: SaveCar by lazy {
        SaveCar(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            saveNotes()
        }
        binding.backCard.setOnClickListener{
            finish()
        }
    }

    private fun saveNotes() = binding.apply {
        if (editModel.text?.isNotEmpty() == true && editmarka.text?.isNotEmpty() == true) {
            sharedPref.deleteCar(
                CarModel(
                    model = editModel.text.toString(),
                    marka = editmarka.text.toString()
                )
            )
            startActivity(Intent(this@CarSave, MainActivity::class.java))
        } else showToastMassage("Заполните все Поля")
    }

    private fun showToastMassage(massage: String) {
        Snackbar.make(
            binding.root,
            massage,
            Snackbar.LENGTH_SHORT,
        ).show()
    }

}