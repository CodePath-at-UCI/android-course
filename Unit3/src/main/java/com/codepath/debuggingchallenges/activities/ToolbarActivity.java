package com.codepath.debuggingchallenges.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.codepath.debuggingchallenges.R;

public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null

        /**
         * Milestone 3 - Toolbar
         * 1. activities > ToolbarActivity > Change
         *     from     setActionBar(toolbar)
         *     to:      setSupportActionBar(toolbar)
         *     Why:     setActionBar(toolbar) is trying to use the version of the toolbar widget
         *              from <android.widget.Toolbar> while the existing view in the xml layout
         *              file is using the toolbar widget from <androidx.appcompat.widget.Toolbar>.
         *              The androidx library is a library that includes every modern type of view
         *              but supports backwards compatibility. This allows app developers to use
         *              existing UI designs on older versions of the Android OS with minimal problems.
         *              It's good practice to use the androidx library for backwads compatibility, so
         *              we want to use the setSupportActionBar version to accommodate earlier Android
         *              OS versions.
         */
        //setActionBar(toolbar);
        setSupportActionBar(toolbar);

        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDescription.setText(R.string.hello);
    }
}
