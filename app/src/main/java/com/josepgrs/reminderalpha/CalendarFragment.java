package com.josepgrs.reminderalpha;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CalendarFragment extends Fragment implements OnDateSelectedListener, OnMonthChangedListener {
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    ReminderMain reminderMain;
    MaterialCalendarView widget;
    List<CalendarDay> calendarDays = new ArrayList<>();
    ListView listView;
    List<String> EventDays = new ArrayList<>();
    private DatabaseReference mDatgroupchild;
    private DatabaseReference mDatabase;
    private int year;
    private int month;
    public CalendarFragment() {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        CalendarDay calendarDay = new CalendarDay();


        year = calendarDay.getYear();
        month = calendarDay.getMonth();
        groupChild(month, year);
       /* Log.d("Current Date:", String.valueOf(year) + String.valueOf(month)); */
        Log.d("CALENDAR FRAGMENT GROUP", ReminderMain.group);


    }

    private void groupChild(final int month, final int year) {


        //Log.d("CalendarFragment", reminderMain.group);

        mDatgroupchild = mDatabase.child("groups").child(ReminderMain.group).child("Events").child(String.valueOf(year)).child(String.valueOf(month + 1));
        mDatgroupchild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> lst = new ArrayList<>(); // Result will be holded Here

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    lst.add(String.valueOf(dsp.getKey())); //add result into array list

                }
                for (String data : lst) {


                    int y = Integer.parseInt(data);
                    EventDays.add(String.valueOf(y));
                    ListViewPopulate(EventDays);
                    Log.d("Events::", String.valueOf(y));

                    calendarDays.add(CalendarDay.from(year, month, y));
                        /* Log.d("MES", String.valueOf(month+1));
                         Log.d("DATAS", calendarDays.toString()); */


                }

                widget.addDecorator(new EventDecorator(Color.RED, calendarDays));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        int oldmonth = month;

        year = date.getYear();
        month = date.getMonth();
        EventDays.clear();

        if ( oldmonth == month ) {

        } else {
            groupChild(month, year);
        }


    }
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        if ( calendarDays.contains(date) ) {
            EventDays.clear();
            ListViewPopulate(EventDays);

    }

    }

    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if ( date == null ) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    private void ListViewPopulate(List<String> lvp) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.listviewgrid, R.id.listText, lvp);
        listView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        widget = (MaterialCalendarView) getActivity().findViewById(R.id.mCalendar);
        listView = (ListView) getActivity().findViewById(R.id.eventsList);


        widget.setOnMonthChangedListener(this);
        widget.setOnDateChangedListener(this);
        widget.addDecorator(oneDayDecorator);
    }
}
