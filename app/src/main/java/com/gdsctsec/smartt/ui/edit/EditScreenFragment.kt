package com.gdsctsec.smartt.ui.edit

import android.os.Bundle
import android.text.Editable

import java.util.*
import android.graphics.Color

import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.ui.main.MainActivity
import com.gdsctsec.smartt.viewmodel.EditScreenViewModel
import com.gdsctsec.smartt.viewmodel.EditscreenViewmodelfactory


class EditScreenFragment : Fragment() {
    private lateinit var lectureEditText: EditText
    private lateinit var starttimeTextView: TextView
    private lateinit var endtimeTextView: TextView
    private lateinit var dayTextInputEditText: AutoCompleteTextView
    private lateinit var saveTextview: TextView
    private lateinit var cancelTextView: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        (requireActivity() as MainActivity).hideBottomNavigation()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View=inflater.inflate(R.layout.fragment_editscreen,container,false)

        lectureEditText = view.findViewById(R.id.add_lecture_edit_text)
        starttimeTextView = view.findViewById(R.id.textview_starttime)
        endtimeTextView = view.findViewById(R.id.textview_endtime)
        dayTextInputEditText = view.findViewById(R.id.textfield_day)
        saveTextview = view.findViewById(R.id.textView_save)
        cancelTextView = view.findViewById(R.id.textView_cancel)

        val viewModelFactory = EditscreenViewmodelfactory(requireContext())
        var id : Int  = -1
        val sourceId=arguments?.getInt("Source")
        val viewModel = ViewModelProvider(this,viewModelFactory).get(EditScreenViewModel::class.java)
        val choice : String? = arguments?.getString("TAG")
        if(choice == "HomeScreenFragment") {
            val startTime = arguments?.getString("Lecture_start_Time")
            val endTime = arguments?.getString("Lecture_End_time")
            val lecture = arguments?.getString("Lecture_Choosen_subject")
            val weekDay = arguments?.getString("Lecture_Weekday")
            id = Integer.parseInt(arguments?.getString("id"))



            lectureEditText.setText(lecture)
            starttimeTextView.setText(startTime)
            endtimeTextView.setText(endTime)
            dayTextInputEditText.setText(weekDay)
        }
        if(choice == "WeekdayActivity"){
            val weekDay = arguments?.getString("Weekday")
            dayTextInputEditText.setText(weekDay)
            dayTextInputEditText.dropDownHeight = 0
            viewDisabled(dayTextInputEditText)
        }



        // dropdown
        var days = resources.getStringArray(com.gdsctsec.smartt.R.array.days)
        val ArrayAdapter = ArrayAdapter(
            requireContext(),
            com.gdsctsec.smartt.R.layout.dropdown, days
        )
        dayTextInputEditText.setAdapter(ArrayAdapter)

        // adding Textwatcher
        lectureEditText.addTextChangedListener(textWatcher)
        starttimeTextView.addTextChangedListener(textWatcher)
        endtimeTextView.addTextChangedListener(textWatcher)
        dayTextInputEditText.addTextChangedListener(textWatcher)


        starttimeTextView.setOnClickListener {
            viewModel.timePick(starttimeTextView)
        }
        endtimeTextView.setOnClickListener {
            viewModel.timePick(endtimeTextView)

        }
        saveTextview.setOnClickListener {
            // wil require all strings for saving in database
            var lecture: String = lectureEditText.text.toString()
            var day: String = dayTextInputEditText.text.toString()
            var starttime: String = starttimeTextView.text.toString()
            var endtime: String = endtimeTextView.text.toString()
            if(!choice.equals("HomeScreenFragment")){
                viewModel.addlecture(TimeTable(lec = lecture, weekday = Weekday.valueOf(day) , startTime = starttime, endTime = endtime))
                Toast.makeText(requireContext(), "adding", Toast.LENGTH_SHORT).show()

            }
            else{
                viewModel.updatelecture(TimeTable(lec = lecture, weekday = Weekday.valueOf(day) , startTime = starttime, endTime = endtime, id = id))
                Toast.makeText(requireContext(), "updating", Toast.LENGTH_SHORT).show()
            }
//
            dayTextInputEditText.height = WRAP_CONTENT
            viewEnabled(dayTextInputEditText)

            val navController= Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)
//
            if (sourceId==R.id.weekdayActivity){
                navController.popBackStack(R.id.weekdayActivity,false)
            }
            else if(sourceId==R.id.TTSchedulingScreenFragment){
                navController.popBackStack(R.id.TTSchedulingScreenFragment,false)
            }
            else {
                Log.e("check","id checking "+sourceId)
                navController.popBackStack(R.id.homeScreenFragment, false)
            }

            (requireActivity() as MainActivity).showBottomNavigation()

            //requireActivity().supportFragmentManager.popBackStack()
            //finish()
        }





        cancelTextView.setOnClickListener {
            dayTextInputEditText.height = WRAP_CONTENT
            viewEnabled(dayTextInputEditText)

            val navController= Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)

            if (sourceId==R.id.weekdayActivity){
                navController.popBackStack(R.id.weekdayActivity,false)
            }
            else if(sourceId==R.id.TTSchedulingScreenFragment){
                navController.popBackStack(R.id.TTSchedulingScreenFragment,false)
            }
            else {
                Log.e("check","id checking "+sourceId)
                navController.popBackStack(R.id.homeScreenFragment, false)
            }

            (requireActivity() as MainActivity).showBottomNavigation()
//            finish()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        Log.e("onResume","ESF")
        (requireActivity() as MainActivity).hideBottomNavigation()
    }



    private fun  btnDisabled(v : TextView){
        v.isEnabled = false
        v.setTextColor(Color.parseColor("#5D1BAACA"))
    }
    private fun  btnEnabled(v : TextView){
        v.isEnabled = true
        v.setTextColor(Color.parseColor("#1BAACA"))
    }

    private fun viewEnabled(v: AutoCompleteTextView) {
        v.isEnabled = true
        v.isPressed = true
        v. isFocusable = true
        v.isFocusableInTouchMode = true;
        v.inputType = EditorInfo.TYPE_CLASS_TEXT


    }

    private fun viewDisabled(v: AutoCompleteTextView) {
        v.isEnabled = false
        v.isPressed = false
        v.isFocusable = false
        v.isFocusableInTouchMode = false
        v.inputType = EditorInfo.TYPE_NULL

    }




    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            var lecture: String? = lectureEditText.text.toString()
            var day: String? = dayTextInputEditText.text.toString()
            var starttime: String? = starttimeTextView.text.toString()
            var endtime: String? = endtimeTextView.text.toString()
            if (lecture != "" && day != "Select day" && starttime != "" && endtime != "") {
                btnEnabled(saveTextview)
            } else {
                btnDisabled(saveTextview)
            }
        }
    }


}