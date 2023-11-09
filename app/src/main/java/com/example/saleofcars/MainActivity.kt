package com.example.saleofcars

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.SaveCar
import com.example.saleofcars.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val sharedPref: SaveCar by lazy {
        SaveCar(this)
    }

    private val adapter: CarAdapter by lazy {
        CarAdapter(onDeleteCarClick = { index ->
            sharedPref.deleteCarAtIndex(index)
            setupViewsAndAdapter()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewsAndAdapter()
        setOnClickListner()
    }

    private fun setupViewsAndAdapter() {
        val listofNotes = sharedPref.getAllCar()
        if (listofNotes.isNotEmpty()) {
            binding.notEmptyImg.visibility = View.GONE
            binding.recucleView.visibility = View.VISIBLE
            adapter.updateList(listofNotes)
            binding.recucleView.adapter = adapter
        }else{
            binding.notEmptyImg.visibility = View.VISIBLE
            binding.recucleView.visibility = View.GONE
        }
    }

    private fun setOnClickListner() = binding.apply {
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CarSave::class.java))
        }
        deleteCard.setOnClickListener {
            showconfirmDeleteNoteDiolog()
        }
    }

    private fun showconfirmDeleteNoteDiolog() {
        val alertDiolog = MaterialAlertDialogBuilder(this)
        alertDiolog.setMessage("Do you want to delete all Notes?")
        alertDiolog.setPositiveButton("Yes") { dialog, _ ->
            deleteAllSavedNotes()
            dialog.dismiss()
        }
        alertDiolog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        alertDiolog.create().show()
    }

    private fun deleteAllSavedNotes() {
        sharedPref.delelteAllCar()
        adapter.updateList(emptyList())
        binding.notEmptyImg.visibility = View.VISIBLE
        binding.recucleView.visibility = View.GONE
    }
}