package com.example.asus.seekbardiy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class Main3Activity extends AppCompatActivity {
    private DatePicker datapicker;
    private TextView tvDate;
    private StringBuilder strTimeBuilder;
    private ArrayList<String> mListSelDate;
    private Calendar cal;
    private int year;
    private int day;
    private TimePicker timePicker;
    private TextView tvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }
        protected void initView(){
            timePicker=findViewById(R.id.timePicker);
            tvTime=findViewById(R.id.tvTime);
            tvDate=findViewById(R.id.tvDate);
            datapicker=findViewById(R.id.datePicker);
            cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR);
            day = cal.get(Calendar.DAY_OF_MONTH);

            datapicker.init(year, cal.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int month, int day) {
                    month++;
                    String strDate = String.valueOf(year + "-" + month + "-" + day + "");
                                mListSelDate=new ArrayList<>();
                    if (mListSelDate.contains(strDate))
                        mListSelDate.remove(strDate);
                    else
                        mListSelDate.add(strDate);

                    strTimeBuilder.delete(0, strTimeBuilder.length());
                    for (String strValue : mListSelDate) {
                        strTimeBuilder = (strTimeBuilder.length() > 0) ? strTimeBuilder.append("," + strValue) : strTimeBuilder.append(strValue);
                    }

                    tvDate.setText(strTimeBuilder);
                }
            });

            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    tvTime.setText(String.valueOf(hourOfDay + ":" + minute));
                }
            });

        }
    }
