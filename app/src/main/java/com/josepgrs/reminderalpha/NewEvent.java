package com.josepgrs.reminderalpha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class NewEvent extends AppCompatActivity {
    private static String TAG = "NEW EVENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newevent);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String stringOfDate = day + "/" + month + "/" + year;
        TextView tDate = (TextView) findViewById(R.id.tDate);
        tDate.setText(stringOfDate);
        TextView tTime = (TextView) findViewById(R.id.tTime);
        tTime.setText(hourOfDay + ":" + minute);
        tDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTextClicked(v);
            }
        });
        tTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTextTClicked(v);
            }
        });
    }

    public void onTextClicked(View v) {
        android.app.DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "Date Picker");
    }

    public void onTextTClicked(View v) {
        android.app.DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "Time Picker");
    }
}

