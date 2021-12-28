package com.gdsctsec.smartt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.gdsctsec.smartt.weekdayScreen.WeekdayActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nextButton : Button = findViewById(R.id.nextIntent)
        nextButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, WeekdayActivity::class.java)
            startActivity(intent)
        })
    }
}