package com.emrhmrc.cameraxlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emrhmrc.cameraxlib.enums.Static

class CameraXActivity : AppCompatActivity() {

    //test
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camerax)
        Static.PATH = intent.getStringExtra("PATH");
    }
}
