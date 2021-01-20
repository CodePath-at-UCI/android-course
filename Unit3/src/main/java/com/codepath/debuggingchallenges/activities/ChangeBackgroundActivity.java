package com.codepath.debuggingchallenges.activities;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.codepath.debuggingchallenges.R;

public class ChangeBackgroundActivity extends AppCompatActivity {
    public final String TAG = ChangeBackgroundActivity.class.getSimpleName();
    private int oldColor = Color.BLUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);
    }

    public void onGoClick(View view) {
        Log.d(TAG, "onGoClick: Called"); //Notice when this IS or ISN'T called in logcat
        View mainView = findViewById(android.R.id.content);
        mainView.setBackgroundColor(getNextColor());
    }

    private int getNextColor() {
        int newColor = (oldColor == Color.BLUE) ? Color.RED : Color.BLUE;
        oldColor = newColor;
        return newColor;
    }
}
