package com.practicum.sharedpreferencearraylisthistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practicum.sharedpreferencearraylisthistory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CourseAdapter.ClickListener {

    private lateinit var mainBinding: ActivityMainBinding
    private var adapter = CourseAdapter(this)

    companion object {
        const val SHARED_PREFERENCES = "shared preferences"
        const val SHARED_PREFERENCES_KEY = "shared preferences_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        loadData()
        buildRecyclerView()

        mainBinding.idBtnAdd.setOnClickListener {
            adapter.courseModalArrayList.add(
                CourseModal(
                    mainBinding.idEdtCourseName.text.toString(),
                    mainBinding.idEdtCourseDescription.text.toString()
                )
            )
            adapter.notifyItemInserted((adapter.courseModalArrayList.size))
        }

        mainBinding.idBtnSave.setOnClickListener {
            saveData()
        }
    }

    private fun buildRecyclerView() {
        val manager = LinearLayoutManager(this)
        mainBinding.idRVCourses.hasFixedSize()
        mainBinding.idRVCourses.layoutManager = manager
        mainBinding.idRVCourses.adapter = adapter
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE)
        val json = sharedPreferences.getString(SHARED_PREFERENCES_KEY, " []")
        val type = object : TypeToken<ArrayList<CourseModal>>() {}.type
        adapter.courseModalArrayList = Gson().fromJson(json, type)
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE)
        val json = Gson().toJson(adapter.courseModalArrayList)
        sharedPreferences.edit().putString(SHARED_PREFERENCES_KEY, json).apply()
        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(cours: CourseModal) {
        Toast.makeText(this, "Вот тебе имя ${cours.courseName} вот тебе описание ${cours.courseDescription} ", Toast.LENGTH_SHORT).show()
    }
}