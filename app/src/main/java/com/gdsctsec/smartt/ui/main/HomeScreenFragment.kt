package com.gdsctsec.smartt.ui.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R

import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.ui.edit.EditScreenFragment

import com.gdsctsec.smartt.util.SwipeGestureUtil
import com.gdsctsec.smartt.data.TimeTable


import com.gdsctsec.smartt.ui.main.adapter.SubjectsAdapter
import com.gdsctsec.smartt.ui.notifications.alarms.AlertReceiver

import com.gdsctsec.smartt.viewmodel.HomeScreenViewModel
import com.gdsctsec.smartt.viewmodel.HomeScreenViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class HomeScreenFragment : Fragment(), SubjectsAdapter.OnItemclicklistener {
    private lateinit var weekDay: String
     lateinit var navController:NavController
     var alarmManager:AlarmManager?=null
    private var _lecture:String="english"
    val lecture:String get() = _lecture


    val timeList: MutableList<String> = mutableListOf("0:00 - 0:00")
    val subjectList: MutableList<String> = mutableListOf("No Lectures as of now")
    val lectureObjectList: MutableList<TimeTable> =
        mutableListOf(TimeTable(-1, "", "", "", Weekday.Monday))
    private lateinit var recyclerView: RecyclerView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val titleTextView = view.findViewById<TextView>(R.id.remindi_header)
        val dayDateTextView = view.findViewById<TextView>(R.id.home_day_textview)
        val noLecturesTextView = view.findViewById<TextView>(R.id.zero_lectures_msg_textView)



        @RequiresApi(Build.VERSION_CODES.O)
        dayDateTextView.text = HomeScreenViewModel(requireActivity()).getMonthDate()
        val viewModelFactory = HomeScreenViewModelFactory(requireActivity())

        /*Remindi header code*/
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        val word: Spannable = SpannableString("Rem")
        word.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0,
            word.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        titleTextView.setText(word)

        val wordTwo: Spannable = SpannableString("i")
        wordTwo.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.remindi_icon_i
                )
            ), 0, wordTwo.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        titleTextView.append(wordTwo)

        val wordThree: Spannable = SpannableString("ndi")
        wordThree.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0,
            wordThree.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        titleTextView.append(wordThree)
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/



        recyclerView = view.findViewById(R.id.home_recyclerView)
        recyclerView.adapter = SubjectsAdapter(subjectList, timeList, this)


        //1 means list is not empty and 0 means isEmpty
        var dataIsThere = 1

        val adapter = SubjectsAdapter(subjectList, timeList, this)


        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(HomeScreenViewModel::class.java)
        @RequiresApi(Build.VERSION_CODES.O)
        weekDay = viewModel.getWeekDayString();


        viewModel.getLiveLectureData().observe(requireActivity(), Observer {
            if (it.size != 0) {
                dataIsThere = 1
                recyclerView.visibility = View.VISIBLE
                noLecturesTextView.visibility = View.GONE
                subjectList.clear()
                timeList.clear()
                lectureObjectList.clear()
                for (i in 0..it.size - 1) {
                    subjectList.add(i, it.get(i).lec)
                    _lecture=it.get(i).lec
                    timeList.add(i, (it.get(i).startTime + " - " + it.get(i).endTime))
                    Log.e("Time",it.get(i).startTime)
                    lectureObjectList.add(it.get(i))

                }
                adapter.notifyDataSetChanged()
            } else {
                dataIsThere = 0

                subjectList.clear()
                timeList.clear()
                lectureObjectList.clear()
                adapter.notifyDataSetChanged()

                recyclerView.visibility = View.GONE
                noLecturesTextView.visibility = View.VISIBLE
            }
        })

        //mutableListOf("subjects")
        recyclerView = view.findViewById(R.id.home_recyclerView)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        val swipeDelete = object : SwipeGestureUtil() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                @RequiresApi(Build.VERSION_CODES.O)
                if (dataIsThere == 0) {
                    Toast.makeText(requireActivity(), "Deleted Lecture", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.removeLecture(lectureObjectList.get(position))
                    lectureObjectList.removeAt(position)
                    subjectList.removeAt(position)
                    timeList.removeAt(position)
                    Toast.makeText(requireActivity(), "Deleted Lecture", Toast.LENGTH_SHORT).show()
                }

            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeDelete)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

//    private fun getTimeInMillis(startTime: String): Long? {
//        val sdf=SimpleDateFormat("hh:mm a")
//        val date: Date? =sdf.parse(startTime)
//
//        Log.e("milli",date?.time.toString())
//        return date?.time
//
//
//    }


    override fun onResume() {
        super.onResume()
        Log.e("onResume","HSF")
        (requireActivity() as MainActivity).showBottomNavigation()
    }

    override fun onItemClick(position: Int) {

        val chosenSubject = subjectList.get(position)
        val startTime = timeList.get(position).split(" - ")[0]
        val endTime = timeList.get(position).split(" - ")[1]


        Toast.makeText(context, "$startTime $endTime $chosenSubject", Toast.LENGTH_SHORT).show()



        val bundle = bundleOf(
            "Lecture_Choosen_subject" to chosenSubject,
            "Lecture_start_Time" to startTime,
            "Lecture_End_time" to endTime,
            "Lecture_Weekday" to weekDay,
            "TAG" to "HomeScreenFragment",
            "id" to lectureObjectList.get(position).id.toString()
        )


        //Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.action_homeScreenFragment_to_editScreenFragment,bundle)
       navController.navigate(R.id.action_homeScreenFragment_to_editScreenFragment,bundle)

    }


}