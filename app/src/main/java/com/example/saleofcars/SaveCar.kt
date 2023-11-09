package com.example.notesapp

import android.content.Context
import com.example.saleofcars.CarModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private const val SHARED_PREFENCES_KEY = "SHARED_PREFENCES_KEY"
private const val NOTES_SHARED_PREF = "NOTES_PREF"

class SaveCar(
    private val context: Context,
) {
    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFENCES_KEY, Context.MODE_PRIVATE

    )

    fun getAllCar(): List<CarModel> {
        val gson = Gson()
        val json = sharedPreferences.getString(NOTES_SHARED_PREF, null)
        val type: Type = object : TypeToken<ArrayList<CarModel?>?>() {}.type
        val noteList = gson.fromJson<ArrayList<CarModel>>(json, type)
        return noteList ?: emptyList()
    }

    fun deleteCar(carModel: CarModel) {
        val notes = getAllCar().toMutableList()
        notes.add(0, carModel)
        val notesGson  = Gson().toJson(notes)
        sharedPreferences
            .edit()
            .putString(NOTES_SHARED_PREF, notesGson)
            .apply()
    }



    fun deleteCarAtIndex(index: Int) {
        val getAllCar = getAllCar().toMutableList()
        if (index in 0 until getAllCar.size) {
            getAllCar.removeAt(index)
            val noteGsonString = Gson().toJson(getAllCar)
            sharedPreferences.edit().putString(NOTES_SHARED_PREF, noteGsonString).apply()
        }
    }
    fun delelteAllCar() = sharedPreferences.edit().clear().apply()
}