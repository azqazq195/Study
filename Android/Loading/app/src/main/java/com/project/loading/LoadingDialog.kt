package com.project.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

class LoadingDialog constructor(context: Context) : Dialog(context){
        init {
            // 화면 밖을 터치해서 꺼지지 않게
            setCanceledOnTouchOutside(false)
            // 뒷 배경을 투명하도록
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.dialog_loading)
        }
    }
