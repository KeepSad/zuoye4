package com.example.asus.seekbardiy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
private CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        calendarView=findViewById(R.id.calendarView);
        initView();
    }
    protected void initView() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(Main2Activity.this,year+"年"+(month+1)+"月"+dayOfMonth+"日",Toast.LENGTH_LONG).show();
            }
        });
    }
}
