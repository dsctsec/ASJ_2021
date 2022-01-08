package com.gdsctsec.smartt.ui.main

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.main.adapter.SubjectsAdapter
import com.gdsctsec.smartt.viewmodel.HomeScreenViewModel
import com.gdsctsec.smartt.viewmodel.HomeScreenViewModelFactory

class HomeScreenFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val titleTextView = view.findViewById<TextView>(R.id.remindi_header)
        val dayDateTextView = view.findViewById<TextView>(R.id.home_day_textview)

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


        val timeList: MutableList<String> = mutableListOf("0:00 - 0:00")
        val subjectList: MutableList<String> = mutableListOf("No Lectures as of now")

        val adapter = SubjectsAdapter(subjectList, timeList)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(HomeScreenViewModel::class.java)
        viewModel.getLiveLectureData().observe(requireActivity(), Observer {
            if(it.size!=0){
                for(i in 0..it.size-1){
                    subjectList.add(i, it.get(i).lec)
                    timeList.add(i, (it.get(i).startTime+" - "+it.get(i).endTime))
                }
                adapter!!.notifyDataSetChanged()
            }
        })

        //mutableListOf("subjects")
        recyclerView = view.findViewById(R.id.home_recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }
}