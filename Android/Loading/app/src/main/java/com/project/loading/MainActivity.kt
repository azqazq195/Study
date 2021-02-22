package com.project.loading

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var anim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button)
        button.setOnClickListener {
            showLoadingDialog()
        }
    }
    private fun showLoadingDialog() {
        val dialog = LoadingDialog(this@MainActivity)
        val TAG = "코루틴"
        Log.e(TAG, "함수 실행")

        CoroutineScope(Dispatchers.Main).launch {
            Log.e(TAG, "코루틴 실행")
            dialog.show()
            delay(2000)
            val geocoder = Geocoder(this@MainActivity, Locale.KOREAN)
            var tempAddress: Address = Address(Locale.KOREAN)
            for(i in 0..20) {
                tempAddress = geocoder.getFromLocationName("삼평동", 1)[0]
            }
            Log.e(TAG, "showLoadingDialog: $tempAddress")
            Log.e(TAG, "코루틴 작업 완료")
            dialog.dismiss()
        }
        Log.e(TAG, "코루틴 종료")
    }
}
