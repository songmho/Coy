package com.asc.coy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.marcohc.robotocalendar.RobotoCalendarView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by songmho on 2015-10-31.
 */
public class CalendarActivity extends AppCompatActivity implements RobotoCalendarView.RobotoCalendarListener {

    RobotoCalendarView robotoCalendarView;
    private int currentMonthIndex;
    private Calendar currentCalendar;
    RecyclerView recyclerView;

    final byte[] bytes=new byte[10];

    ArrayList<Plan_item> items=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("동아리 행사 보기");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        robotoCalendarView=(RobotoCalendarView)findViewById(R.id.robotoCalendarPicker);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        currentMonthIndex = 0;
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        if(currentMonthIndex==0) {
            robotoCalendarView.markDayAsSelectedDay(currentCalendar.getTime());
            robotoCalendarView.markDayAsCurrentDay(currentCalendar.getTime());
        }
        robotoCalendarView.setRobotoCalendarListener(this);


        final Date date=new Date();
        final SimpleDateFormat format = new SimpleDateFormat("MM");
        ParseQuery<ParseObject> qurey=new ParseQuery<>("Plan");
        qurey.whereContains("date",format.format(date).toString());
        qurey.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for(ParseObject o: list){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                    try {
                        Date d=format.parse(o.getString("date"));
                        robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.BLUE_COLOR,d);

                    } catch (java.text.ParseException e1) {
                        e1.printStackTrace();
                    }

                    if(format.format(date).equals(o.getString("date"))){
                        Plan_item item = new Plan_item(bytes,o.getString("Title"), o.getString("club"), o.getString("date"),o.getString("Detail"));
                        items.add(item);
                    }
                }

                recyclerView.setAdapter(new PlanAdapter(getApplicationContext(),items,R.layout.item_plan));
            }
        });


    }

    @Override
    public void onDateSelected(Date date) {
        robotoCalendarView.markDayAsSelectedDay(date);

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        format.format(date);
        Toast.makeText(CalendarActivity.this, format.format(date), Toast.LENGTH_SHORT).show();
        items.clear();
        ParseQuery<ParseObject> qurey=new ParseQuery<>("Plan");

        qurey.whereEqualTo("date",format.format(date).toString());
        qurey.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for(ParseObject o: list){
                    Plan_item item = new Plan_item(bytes,o.getString("Title"), o.getString("club"), o.getString("date"),o.getString("Detail"));
                    items.add(item);
                }


                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onRightButtonClick() {
    /*    currentMonthIndex++;
        updateCalendar();
*/
    }

    @Override
    public void onLeftButtonClick() {
 /*       currentMonthIndex--;
        updateCalendar();
  */  }

    private void updateCalendar() {
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        currentCalendar.add(Calendar.MONTH, currentMonthIndex);
        robotoCalendarView.initializeCalendar(currentCalendar);

        ParseQuery<ParseObject> qurey=new ParseQuery<>("Plan");
        qurey.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for(ParseObject o:list){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                    try {
                        Date d=format.parse(o.getString("date"));
                        robotoCalendarView.markDayAsSelectedDay(d);
                        robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.BLUE_COLOR,d);
                        robotoCalendarView.clearDisappearingChildren();
                    } catch (java.text.ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        if(currentMonthIndex==0){
            robotoCalendarView.markDayAsSelectedDay(currentCalendar.getTime());
            robotoCalendarView.markDayAsCurrentDay(currentCalendar.getTime());
        }
        else
            robotoCalendarView.markDayAsCurrentDay(null);
    }
}
