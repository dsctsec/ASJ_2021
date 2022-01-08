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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.ui.main.adapter.TTScreenAdapter

import com.gdsctsec.smartt.ui.main.model.TTScreendata
import com.gdsctsec.smartt.viewmodel.TTScreenViewModel
import com.gdsctsec.smartt.viewmodel.TTScreenViewModelFactory
import java.security.acl.Owner


class TTSchedulingScreenFragment : Fragment() {

    lateinit var ttScreenRecyclerView: RecyclerView;
    lateinit var dataList: List<TTScreendata>
    lateinit var titleTextView: TextView
    lateinit var viewModel:TTScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_t_t_scheduling_screen, container, false)



        ttScreenRecyclerView = view.findViewById(R.id.tt_items_recycler_view);


        titleTextView = view.findViewById(R.id.remindi_header)

        val viewModelFactory=TTScreenViewModelFactory(requireContext())
         viewModel=ViewModelProvider(requireActivity(),viewModelFactory).get(TTScreenViewModel::class.java)



        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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




        val noOfLectures= mutableListOf(0,0,0,0,0,0)
        dataList = listOf(
            TTScreendata(R.string.monday, noOfLectures[0], R.color.color_monday),
            TTScreendata(R.string.tuesday, noOfLectures[1], R.color.color_tuesday),
            TTScreendata(R.string.wednesday, noOfLectures[2], R.color.color_wednesday),
            TTScreendata(R.string.thursday, noOfLectures[3], R.color.color_thursday),
            TTScreendata(R.string.friday, noOfLectures[4], R.color.color_friday),
            TTScreendata(R.string.saturday,noOfLectures[5], R.color.color_saturday)
        )

        viewModel.getLiveLectures().observe(requireActivity(), Observer {
            if (it.size!=0){
                for (i in it.indices){
                    val weekDay=it.get(i).weekday.name

                    if (weekDay.equals("Monday"))
                        noOfLectures[0]++

                    else if(weekDay.equals("Tuesday"))
                        noOfLectures[1]++

                    else if(weekDay.equals("Wednesday"))
                        noOfLectures[2]++
                    else if(weekDay.equals("Thursday"))
                        noOfLectures[3]++
                    else if(weekDay.equals("Friday"))
                        noOfLectures[4]++
                    else
                        noOfLectures[5]++
                }
            }
        })

        val adapter=TTScreenAdapter(requireContext(),dataList)

        dataList = listOf(
            TTScreendata(R.string.monday, noOfLectures[0], R.color.color_monday),
            TTScreendata(R.string.tuesday, noOfLectures[1], R.color.color_tuesday),
            TTScreendata(R.string.wednesday, noOfLectures[2], R.color.color_wednesday),
            TTScreendata(R.string.thursday, noOfLectures[3], R.color.color_thursday),
            TTScreendata(R.string.friday, noOfLectures[4], R.color.color_friday),
            TTScreendata(R.string.saturday,noOfLectures[5], R.color.color_saturday)
        )


        adapter.notifyDataSetChanged()

        ttScreenRecyclerView.layoutManager = GridLayoutManager(context, 2)
        ttScreenRecyclerView.adapter = TTScreenAdapter(requireContext(), dataList)
    }


}