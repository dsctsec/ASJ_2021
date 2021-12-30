package com.gdsctsec.smartt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.gdsctsec.smartt.databinding.ActivityAddLectureBinding
import com.gdsctsec.smartt.databinding.ActivityEditscreenBinding
private lateinit var binding : ActivityAddLectureBinding
class AddLectureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddLectureBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        val days = resources.getStringArray(R.array.days)
        val ArrayAdapter = ArrayAdapter(applicationContext,R.layout.dropdown,days)
        binding.textfieldDay.setAdapter(ArrayAdapter)



    }
}