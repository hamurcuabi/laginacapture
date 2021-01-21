package com.emrhmrc.capture;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.emrhmrc.cameraxlib.CameraXActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent newIntent = new Intent(this, CameraXActivity.class);
        newIntent.putExtra("PATH", getFilesDir().getAbsolutePath());
        startActivityForResult(newIntent, 1231);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String test = data.getStringExtra("CameraXFilePath");
            Log.d(TAG, "onActivityResult: +test");
        }
    }
}
