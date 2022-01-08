package com.gdsctsec.smartt.ui.main

import android.graphics.Color
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
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.main.adapter.TTScreenAdapter

import com.gdsctsec.smartt.ui.main.model.TTScreendata
import com.gdsctsec.smartt.viewmodel.TTScreenViewModel
import com.gdsctsec.smartt.viewmodel.TTScreenViewModelFactory


class TTSchedulingScreenFragment : Fragment() {

    lateinit var ttScreenRecyclerView: RecyclerView;
    lateinit var dataList: MutableList<TTScreendata>
    lateinit var titleTextView: TextView
    lateinit var viewModel: TTScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t_t_scheduling_screen, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ttScreenRecyclerView = view.findViewById(R.id.tt_items_recycler_view);
        titleTextView = view.findViewById(R.id.remindi_header)
        val viewModelFactory = TTScreenViewModelFactory(requireContext())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(TTScreenViewModel::class.java)

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

        var noOfLectures = mutableListOf(0, 0, 0, 0, 0, 0)
        dataList = mutableListOf()

        var mDataList = listOf(
            TTScreendata(R.string.monday, noOfLectures[0], R.color.color_monday),
            TTScreendata(R.string.tuesday, noOfLectures[1], R.color.color_tuesday),
            TTScreendata(R.string.wednesday, noOfLectures[2], R.color.color_wednesday),
            TTScreendata(R.string.thursday, noOfLectures[3], R.color.color_thursday),
            TTScreendata(R.string.friday, noOfLectures[4], R.color.color_friday),
            TTScreendata(R.string.saturday, noOfLectures[5], R.color.color_saturday)
        )
        dataList.addAll(mDataList)
        val adapter = TTScreenAdapter(requireContext(), dataList)
        ttScreenRecyclerView.layoutManager = GridLayoutManager(context, 2)
        ttScreenRecyclerView.adapter = adapter

        viewModel.getLectureCountPerWeekday().observe(requireActivity(), {
            noOfLectures = mutableListOf(0, 0, 0, 0, 0, 0)
            if (it.isNotEmpty()) {
                Log.e("TT screen data", it.toString())
                for (i in it.indices) {
                    val weekDay = it[i].dow.name

                    if (weekDay.equals("Monday"))
                        noOfLectures[0] = it[i].lecNo
                    else if (weekDay.equals("Tuesday"))
                        noOfLectures[1] = it[i].lecNo
                    else if (weekDay.equals("Wednesday"))
                        noOfLectures[2] = it[i].lecNo
                    else if (weekDay.equals("Thursday"))
                        noOfLectures[3] = it[i].lecNo
                    else if (weekDay.equals("Friday"))
                        noOfLectures[4] = it[i].lecNo
                    else
                        noOfLectures[5] = it[i].lecNo
                }
                mDataList = mutableListOf(
                    TTScreendata(R.string.monday, noOfLectures[0], R.color.color_monday),
                    TTScreendata(R.string.tuesday, noOfLectures[1], R.color.color_tuesday),
                    TTScreendata(R.string.wednesday, noOfLectures[2], R.color.color_wednesday),
                    TTScreendata(R.string.thursday, noOfLectures[3], R.color.color_thursday),
                    TTScreendata(R.string.friday, noOfLectures[4], R.color.color_friday),
                    TTScreendata(R.string.saturday, noOfLectures[5], R.color.color_saturday)
                )
                dataList.clear()
                dataList.addAll(mDataList)

                adapter.notifyDataSetChanged()
            } else {
                Log.e("TT screen data", "empty!")
            }
        })
    }
}