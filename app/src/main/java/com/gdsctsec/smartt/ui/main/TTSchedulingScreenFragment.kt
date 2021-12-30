package com.gdsctsec.smartt.ui.main


import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD:app/src/main/java/com/gdsctsec/smartt/TTSchedulingScreen.kt
import android.widget.TextView
import androidx.core.content.ContextCompat
=======
import com.gdsctsec.smartt.R
>>>>>>> 96f382ddc8fc2fdf170a678b5d3ed3987eea877d:app/src/main/java/com/gdsctsec/smartt/ui/main/TTSchedulingScreenFragment.kt


class TTSchedulingScreenFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_t_t_scheduling_screen, container, false)

        val title:TextView=view.findViewById(R.id.remindi_text_view);
        val word:Spannable=SpannableString("Rem")
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