package com.gdsctsec.smartt

import android.R
import android.R.attr
import android.app.ActionBar
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.gdsctsec.smartt.databinding.ActivityEditscreenBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import android.R.attr.button

import android.graphics.drawable.Drawable
import android.media.ImageReader
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import java.time.Clock


class EditScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditscreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        val days = resources.getStringArray(com.gdsctsec.smartt.R.array.days)
        val ArrayAdapter = ArrayAdapter(applicationContext,
            com.gdsctsec.smartt.R.layout.dropdown,days)
        binding.textfieldDay.setAdapter(ArrayAdapter)

        binding.textviewStarttime.setOnClickListener{
                timePick(binding.textviewStarttime)
        }
        binding.textviewEndtime.setOnClickListener{
                timePick(binding.textviewEndtime)

        }
        binding.textViewSave.setOnClickListener{
           binding.textfieldDay.setDropDownHeight(0)
            editTextDisabled(binding.dayText)
            editTextDisabled(binding.textinputAddlecture)
           editTextDisabled(binding.textviewStarttime)
            editTextDisabled(binding.textviewEndtime)

        }
        binding.textViewCancel.setOnClickListener{
            binding.textfieldDay.setDropDownHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            editTextEnabled(binding.dayText)
            editTextEnabled(binding.textinputAddlecture)
           editTextEnabled(binding.textviewStarttime)
            editTextEnabled(binding.textviewEndtime)
        }

    }

    private fun editTextEnabled(v: View) {
        v.isFocusable = true
        v.isEnabled = true
        v.isPressed = true
        v.isFocusableInTouchMode = true

    }

    private fun editTextDisabled(v:View) {
        v.isFocusable = false
        v.isEnabled = false
        v.isPressed = false
    }


    private fun timePick(v : TextView){
        val mTimePickerstart: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePickerstart = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
               v.setText(onTimeSet(hourOfDay,minute))
            }
        }, hour, minute, false)
        mTimePickerstart.show()

    }
   private fun onTimeSet( hourOfDay: Int, minute: Int) : String {
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
        hour_of_12_hour_format = if (hourOfDay > 11) {

            // If the hour is greater than or equal to 12
            // Then we subtract 12 from the hour to make it 12 hour format time
            hourOfDay - 12
        } else {
            hourOfDay
        }

        // Get the calling activity TextView reference

        // Display the 12 hour format time in app interface
        val time = "$hour_of_12_hour_format:$minute $status"
        return time
    }


}