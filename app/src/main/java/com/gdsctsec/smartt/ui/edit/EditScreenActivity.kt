package com.gdsctsec.smartt.ui.edit

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gdsctsec.smartt.databinding.ActivityEditscreenBinding
import java.util.*
import android.graphics.Color

import android.text.TextWatcher
import android.widget.*
import com.gdsctsec.smartt.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.content.Intent

import android.app.Activity

import android.R.attr.name





class EditScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditscreenBinding
    // Initializing



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditscreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val lectureEditText : EditText = binding.textinputAddlecture
        val starttimeTextView : TextView = binding.textviewStarttime
        val EndtimeTextView : TextView = binding.textviewEndtime
        val dayTextInputEditText : AutoCompleteTextView = binding.textfieldDay
        val SaveTextview : TextView = binding.textViewSave
        val cancelTextView : TextView  = binding.textViewCancel




        // dropdown
        var days = resources.getStringArray(com.gdsctsec.smartt.R.array.days)
        val ArrayAdapter = ArrayAdapter(
            applicationContext,
            com.gdsctsec.smartt.R.layout.dropdown, days
        )
        dayTextInputEditText.setAdapter(ArrayAdapter)

        // adding Textwatcher
        lectureEditText.addTextChangedListener(textWatcher)
        starttimeTextView.addTextChangedListener(textWatcher)
        EndtimeTextView.addTextChangedListener(textWatcher)
        dayTextInputEditText.addTextChangedListener(textWatcher)


        starttimeTextView.setOnClickListener {
            timePick(starttimeTextView)
        }
       EndtimeTextView.setOnClickListener {
            timePick(EndtimeTextView)

        }
        SaveTextview.setOnClickListener {
            // wil require all strings for saving in database
            var lecture: String? = lectureEditText.text.toString()
            var day : String? = binding.textfieldDay.text.toString()
            var starttime : String? = binding.textviewStarttime.text.toString()
            var endtime : String? = binding.textviewEndtime.text.toString()
            ViewDisabled(SaveTextview)

        }


        cancelTextView.setOnClickListener {
           finish()
        }
    }


    private fun ViewEnabled(v: TextView) {
        v.isEnabled = true
        v.setTextColor(Color.parseColor("#1BAACA"))

    }

    private fun ViewDisabled(v: TextView) {
        v.isEnabled = false
        v.setTextColor(Color.parseColor("#5D1BAACA"))
    }


    private fun timePick(v: TextView) {
        val mTimePickerstart: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePickerstart = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                v.setText(onTimeSet(hourOfDay, minute))
            }
        }, hour, minute, false)
        mTimePickerstart.show()

    }

    private fun onTimeSet(hourOfDay: Int, minute: Int): String {
        // Set a variable to hold the current time AM PM Status
        // Initially we set the variable value to AM


        var status = "am"
        if (hourOfDay > 11) {
            // If the hour is greater than or equal to 12
            // Then the current AM PM status is PM
            status = "pm"
        }

        // Initialize a new variable to hold 12 hour format hour value
        val hour_of_12_hour_format: Int
        hour_of_12_hour_format = if (hourOfDay > 12) {

            // If the hour is greater than or equal to 12
            // Then we subtract 12 from the hour to make it 12 hour format time
            hourOfDay - 12
        } else {
            hourOfDay
        }

        // Get the calling activity TextView reference

        // Display the 12 hour format time in app interface
        if (minute >= 0 && minute <= 9) {
            val time = "$hour_of_12_hour_format:0$minute $status"
            return time
        } else {
            val time = "$hour_of_12_hour_format:$minute $status"
            return time
        }

    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            var lecture: String? = binding.textinputAddlecture.text.toString()
            var day : String? = binding.textfieldDay.text.toString()
            var starttime : String? = binding.textviewStarttime.text.toString()
            var endtime : String? = binding.textviewEndtime.text.toString()
            if (lecture != "" && day != "Select day" && starttime != "" && endtime != "") {
                ViewEnabled(binding.textViewSave)
            }
            else{
                ViewDisabled(binding.textViewSave)
            }
        }
    }




}