package com.gdsctsec.smartt.ui.main.data

import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.main.model.TTScreendata

class loadData {
    fun loadList():List<TTScreendata>{
        return listOf(TTScreendata(R.string.monday,R.string._5_lectures,R.color.color_monday),TTScreendata(R.string.tuesday,R.string._5_lectures,R.color.color_tuesday)
                      ,TTScreendata(R.string.wednesday,R.string._5_lectures,R.color.color_wednesday),TTScreendata(R.string.thursday,R.string._5_lectures,R.color.color_thursday)
                      ,TTScreendata(R.string.friday,R.string._5_lectures,R.color.color_friday),TTScreendata(R.string.saturday,R.string._5_lectures,R.color.color_saturday))


    }
}