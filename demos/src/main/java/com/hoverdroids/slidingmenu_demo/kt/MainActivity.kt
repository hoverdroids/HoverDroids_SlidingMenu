package com.hoverdroids.slidingmenu_demo.kt

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hoverdroids.slidingmenu_demo.R
import kotterknife.bindView

class MainActivity : AppCompatActivity() {

    val tv: TextView by bindView(R.id.tv)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
