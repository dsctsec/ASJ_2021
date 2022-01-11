package com.gdsctsec.smartt.ui.weekday

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.util.SwipeGestureUtil
import com.gdsctsec.smartt.ui.edit.EditScreenFragment
import com.gdsctsec.smartt.ui.main.MainActivity
import com.gdsctsec.smartt.ui.weekday.adapter.WeekdayAdapter
import com.gdsctsec.smartt.viewmodel.WeekdayActivityViewModelFactory
import com.gdsctsec.smartt.viewmodel.WeekdayActvityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeekdayFragment : Fragment() {
    lateinit var dayColorChangingToolbar: Toolbar
    lateinit var imageViewCalendarImageWhenEmpty: ImageView
    lateinit var lecturesRecyclerView: RecyclerView
    lateinit var lecNumberCountTextView: TextView
    lateinit var addNewLectureEventFloatingActionButton: FloatingActionButton
    lateinit var dayTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity() as MainActivity).hideBottomNavigation()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_weekday, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewCalendarImageWhenEmpty = view.findViewById(R.id.empty_list_image_view)
        lecturesRecyclerView = view.findViewById(R.id.lecture_recycler_view)
        lecNumberCountTextView = view.findViewById(R.id.lecture_number_text_view)
        addNewLectureEventFloatingActionButton =
            view.findViewById(R.id.lecture_add_floating_action_button)
        dayColorChangingToolbar = view.findViewById(R.id.weekday_activity_toolbar_top_card_view)
        dayTextView = view.findViewById(R.id.day_text_view)

        val weekDay = arguments?.getString("weekday")
        val weekNum = arguments?.getInt("weeknum")

        Log.e("Values", weekDay + " " + weekNum)

        val viewModelFactory = WeekdayActivityViewModelFactory(requireContext(), weekDay.toString())
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(WeekdayActvityViewModel::class.java)


        //recycler View Adapter
        val timeList: MutableList<String> = mutableListOf()
        val subjectList: MutableList<String> = mutableListOf()
        val idList = mutableListOf<Int>()

        val adapter = WeekdayAdapter(timeList, subjectList)

        viewModel.getLiveLecturesData().observe(requireActivity(), Observer {
            if (it.size != 0) {
                idList.clear()
                timeList.clear()
                subjectList.clear()
                imageViewCalendarImageWhenEmpty.visibility = View.INVISIBLE
                for (i in it.indices) {
                    idList.add(it[i].id)
                    timeList.add(i, it[i].startTime + "-" + it[i].endTime)
                    subjectList.add(i, it[i].lec)
                }

                adapter.notifyDataSetChanged()
            } else {
                imageViewCalendarImageWhenEmpty.visibility = View.VISIBLE
            }
        })



        lecturesRecyclerView.adapter = adapter
        lecturesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        lecNumberCountTextView.text = timeList.size.toString() + " Lectures"

        viewModel.getLectureCountPerWeekday().observe(requireActivity(), {
            for (day in it) {
                if (day.dow.name.equals(weekDay, ignoreCase = true)) {
                    lecNumberCountTextView.text = day.lecNo.toString() + " Lectures"
                }
            }
        })

        //getting the intent and the day color to be displayed by the weeknum int


        if (weekNum != null) {
            dayColor(weekNum)
        }

        //Image visibility
        if (timeList.isNotEmpty()) imageViewCalendarImageWhenEmpty.visibility =
            View.GONE else imageViewCalendarImageWhenEmpty.visibility =
            View.VISIBLE


        //Floating Button OnClick
        addNewLectureEventFloatingActionButton.setOnClickListener(View.OnClickListener {

            val bundle = bundleOf(
                "Weekday" to weekDay,
                "TAG" to "WeekdayActivity",
                "Source" to R.id.weekdayActivity
            )
            it.findNavController()
                .navigate(R.id.action_weekdayActivity_to_editScreenFragment, bundle)

        })

        val swipeDelete = object : SwipeGestureUtil() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    viewModel.deleteLecture(idList[position])
                    idList.removeAt(position)
                    timeList.removeAt(position)
                    subjectList.removeAt(position)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeDelete)
        itemTouchHelper.attachToRecyclerView(lecturesRecyclerView)
    }

    override fun onResume() {
        super.onResume()
        Log.e("onResume", "WF")
        (requireActivity() as MainActivity).hideBottomNavigation()
    }

    fun dayColor(day: Int) {


        val backgroundTintAwareDrawable = DrawableCompat.wrap(dayColorChangingToolbar.background)



        when (day) {
            1 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(requireContext(), R.color.color_monday)
                )
                dayTextView.text = "Monday"
            }
            2 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(requireContext(), R.color.color_tuesday)
                )
                dayTextView.text = "Tuesday"
            }
            3 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(requireContext(), R.color.color_wednesday)
                )
                dayTextView.text = "Wednesday"
            }
            4 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(requireContext(), R.color.color_thursday)
                )
                dayTextView.text = "Thursday"
            }
            5 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(requireContext(), R.color.color_friday)
                )
                dayTextView.text = "Friday"
            }
            6 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(requireContext(), R.color.color_saturday)
                )
                dayTextView.text = "Saturday"
            }
            else -> {
                Log.d("WeekdayActivity error", "unforeseen error occurred in when(x)")
            }
        }
    }


}
