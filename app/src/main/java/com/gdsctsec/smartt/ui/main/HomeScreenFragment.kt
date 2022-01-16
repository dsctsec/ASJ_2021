package com.gdsctsec.smartt.ui.main

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
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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
import com.gdsctsec.smartt.viewmodel.HomeScreenViewModel
import com.gdsctsec.smartt.viewmodel.HomeScreenViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class HomeScreenFragment : Fragment(), SubjectsAdapter.OnItemclicklistener {

    private lateinit var weekDay: String

    private lateinit var navController: NavController

    val timeList: MutableList<String> = mutableListOf("0:00 - 0:00")
    val subjectList: MutableList<String> = mutableListOf("No Lectures as of now")
    val lectureObjectList: MutableList<TimeTable> =
        mutableListOf(TimeTable(-1, "", "", "", Weekday.Monday))
    val lectureCompleteList: MutableList<Boolean> = mutableListOf(false)
    val lectureEndTime: MutableList<Int> = mutableListOf(-1)

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
        val numberOfLecturesTextView =
            view.findViewById<TextView>(R.id.home_numberOf_tasks_day_textview)

        val lectureProgressBar =
            view.findViewById<ProgressBar>(R.id.home_fragment_tasks_progress_bar)

        val lectureProgressTextView = view.findViewById<TextView>(R.id.home_tasks_progress_textview)

        numberOfLecturesTextView.setText("0/" + subjectList.size)

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
        recyclerView.adapter = SubjectsAdapter(subjectList, timeList, lectureCompleteList, this)


        //1 means list is not empty and 0 means isEmpty
        var dataIsThere = 1

        val adapter = SubjectsAdapter(subjectList, timeList, lectureCompleteList, this)


        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(HomeScreenViewModel::class.java)
        @RequiresApi(Build.VERSION_CODES.O)
        weekDay = viewModel.getWeekDayString();

        val presentTime = Date()

        viewModel.getLiveLectureData().observe(requireActivity(), Observer {
            if (it.size != 0) {
                var count = 0
                dataIsThere = 1
                recyclerView.visibility = View.VISIBLE
                noLecturesTextView.visibility = View.GONE
                subjectList.clear()
                timeList.clear()
                lectureObjectList.clear()
                lectureCompleteList.clear()

                for (i in 0..it.size - 1) {
                    subjectList.add(i, it.get(i).lec)
                    timeList.add(i, (it.get(i).startTime + " - " + it.get(i).endTime))
                    lectureObjectList.add(it.get(i))
                    lectureEndTime.add(endTimeConverter(it.get(i).endTime))
                    if (endTimeConverter(it.get(i).endTime) < SimpleDateFormat("HH").format(
                            Calendar.getInstance().time
                        ).toInt()
                        || (endTimeConverter(it.get(i).endTime) <= SimpleDateFormat("HH").format(
                            Calendar.getInstance().time).toInt()
                                &&
                                minutesConverter(it.get(i).endTime) <= SimpleDateFormat("mm").format(
                            Calendar.getInstance().time).toInt())) {
                        lectureCompleteList.add(i, true)
                        count++
                    } else {
                        lectureCompleteList.add(i, false)
                    }
                }

                numberOfLecturesTextView.setText(count.toString() + " / " + subjectList.size)

                lectureProgressBar.setProgress(
                    ((count.toDouble() / (subjectList.size).toDouble()) * 100.0).roundToInt(),
                    true
                )
                lectureProgressTextView.setText((((count.toDouble() / (subjectList.size).toDouble()) * 100.0).roundToInt()).toString() + "%")

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

    suspend fun checkIsOver(time: Int) {
        for (x in 0..lectureEndTime.size - 1) {
            if (time > lectureEndTime.get(x)) {
                lectureCompleteList.set(x, true)
            }
        }
    }

    private fun endTimeConverter(time: String): Int {
        var x = -1
        if (time.takeLast(2) == "pm" && (time.split(":")[0]) != "12") {
            x = (time.split(":")[0]).toInt() + 12
        } else if (time.takeLast(2) == "am" && time == "12") {
            x = 24
        } else {
            x = (time.split(":")[0]).toInt()
        }
        Log.d("convertedTimeHours", "$x")
        return x;
    }

    private fun minutesConverter(minutes: String): Int {
        var x = -1
        x = (minutes.split(":")[1].split(" ")[0]).toInt()
        Log.d("convertedTimeMinutes", "$x")
        return x
    }

    override fun onResume() {
        super.onResume()
        Log.e("onResume", "HSF")
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
        navController.navigate(R.id.action_homeScreenFragment_to_editScreenFragment, bundle)

    }

}