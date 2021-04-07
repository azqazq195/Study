package com.project.mylocation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var layout = arrayOfNulls<LinearLayout>(2)
    private lateinit var add1: TextView
    private lateinit var add2: TextView
    private lateinit var address1: LinearLayout
    private lateinit var address2: LinearLayout

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout[0] = findViewById(R.id.layout1)
        layout[1] = findViewById(R.id.layout2)

        add1 = findViewById(R.id.add1)
        add2 = findViewById(R.id.add2)
        address1 = findViewById(R.id.address1)
        address2 = findViewById(R.id.address2)

        add1.visibility = View.VISIBLE
        add2.visibility = View.VISIBLE
        address1.visibility = View.GONE
        address2.visibility = View.GONE

        for(i in layout){
            i!!.setBackgroundColor(R.color.danggeun)
        }

        add1.setOnClickListener(this)
        add2.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.add1 -> {
                getSearchLocationActivity()
            }
            R.id.add2 -> {
                getSearchLocationActivity()
            }
        }
    }

    private fun getSearchLocationActivity(){
        val intent = Intent(this, SearchLocationActivity::class.java)
        intent.putExtra("callingActivity", 1)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == RESULT_OK) {

            }
        }
    }
}
