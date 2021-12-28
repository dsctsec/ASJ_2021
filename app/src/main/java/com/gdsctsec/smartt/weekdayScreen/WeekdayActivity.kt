package com.gdsctsec.smartt.weekdayScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import com.gdsctsec.smartt.R

class WeekdayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekday)
        //test code delete it later
        Log.d("abcd","chala")
        //getSupportActionBar().hide()
//        val ab : ActionBar? = supportActionBar
//        ab.hide()
//        val daycolor : Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(daycolor)
        val dayColor : Toolbar = findViewById(R.id.toolbar)
        val imageView : ImageView = findViewById(R.id.imageView)
        Toast.makeText(this,"asdas",Toast.LENGTH_SHORT).show()
        val changeDay : Button = findViewById(R.id.test)
        //THIS IS A TEST BUTTON
        changeDay.setOnClickListener(View.OnClickListener {
            var randomday=  (1..6).random()
            Toast.makeText(this, "Click "+randomday, Toast.LENGTH_SHORT).show()
            //daycolor.setBackgroundColor(R.color.tuesday)
           //dayColor.setBackgroundResource(R.color.friday)
            //IMP
            //imageView.visibility = View.GONE


        })

    }
}