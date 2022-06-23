package com.android.example.exercise2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.exercise2.adapter.CustomAdapter
import com.android.example.exercise2.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val datalist = ArrayList<DataViewModel>()

        for (i in 1..3)
        {
            datalist.add(DataViewModel(Color.GREEN,"                        நிறங்கள்                          "))
        }
        for (i in 4..6)
        {
            datalist.add(DataViewModel(Color.parseColor("#FFCC00"),"பழங்கள் மற்றும் காய்கறிகள்"))
        }
        for (i in 7..9)
        {
            datalist.add(DataViewModel(Color.RED," வீட்டு உபயோக பொருட்கள் "))
        }

        val adapter = CustomAdapter(datalist)
        recyclerview.adapter= adapter


    }
}