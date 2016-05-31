package com.josepgrs.reminderalpha;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY); //Current Hour
        int minute = c.get(Calendar.MINUTE); //Current Minute
        int second = c.get(Calendar.SECOND); //Current Second


        return new TimePickerDialog(getActivity(), this, hourOfDay, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView tTime = (TextView) getActivity().findViewById(R.id.tTime);
        tTime.setText(hourOfDay + ":" + minute);
        Log.d("TIME", hourOfDay + ":" + minute);
    }
}