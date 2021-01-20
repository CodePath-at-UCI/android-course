package com.codepath.debuggingchallenges.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;

import com.codepath.debuggingchallenges.R;

import java.util.Calendar;

public class CurrentDayActivity extends AppCompatActivity {
    private final String TAG = CurrentDayActivity.class.getSimpleName();
    TextView tvDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_day);
        tvDay = (TextView) findViewById(R.id.tvDay);
        //tvDay.setText(getDayOfMonth());
        tvDay.setText(fix());
    }

    private int getDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Log.d(TAG, "getDayOfMonth: " + cal.get(Calendar.DAY_OF_MONTH));
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Milestone 1 - What Day Is It
     * We were passing an integer to tvDay.setText(), which has overloaded constructors
     * One of those constructors takes an int (the ID of an app resource), while the other
     * a CharSequence (a string).
     * One way to start is to backtrack.
     * 1. Look at the stack trace and find your code in blue.
     * 2. This line ... tvDay.setText(getDayOfMonth()); ... is causing problems.
     * 3. We can use Log statements to determine what value is being put into tvDay (current day)
     * 4. setText(int) expects int to be an ID. We're giving it a number, not an ID.
     * 5. Convert that number to a string (CharSequence)
     */
    private CharSequence fix() {
        CharSequence day = Integer.toString(getDayOfMonth());
        return day;
    }
}
