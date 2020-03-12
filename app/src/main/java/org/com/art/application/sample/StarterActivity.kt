package org.com.art.application.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import org.com.art.application.WheelViewAlternative
import org.wangjie.wheelview.R

/**
 * Created by Wonka on 28.07.2017.
 */

class StarterActivity : Activity() {
    private lateinit var mStart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.starter_screen)
        mStart = findViewById(R.id.start_button)
        mStart.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@StarterActivity, WheelViewAlternative::class.java))
        })
    }
}
