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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.main.adapter.TTScreenAdapter
import com.gdsctsec.smartt.ui.main.data.loadData
import com.gdsctsec.smartt.ui.main.model.TTScreendata


class TTSchedulingScreenFragment : Fragment() {

    lateinit var rv:RecyclerView;
    lateinit var dataList:List<TTScreendata>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_t_t_scheduling_screen, container, false)
        dataList=loadData().loadList()
       rv=view.findViewById(R.id.tt_items_recycler_view);
        rv.layoutManager= GridLayoutManager(context,2)
        rv.adapter=TTScreenAdapter(requireContext(),dataList)

        val title:TextView=view.findViewById(R.id.remindi_header)

        val word: Spannable = SpannableString("Rem")
        word.setSpan(ForegroundColorSpan(Color.BLACK),0,word.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        title.setText(word)

        val wordTwo:Spannable=SpannableString("i")
        wordTwo.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.remindi_icon_i)),0,wordTwo.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        title.append(wordTwo)

        val wordThree:Spannable=SpannableString("ndi")
        wordThree.setSpan(ForegroundColorSpan(Color.BLACK),0,wordThree.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        title.append(wordThree)
        return view;
    }


}