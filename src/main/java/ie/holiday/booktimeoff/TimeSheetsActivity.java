package ie.holiday.booktimeoff;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class TimeSheetsActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sheets);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Time Sheets");

        //getting current time

        Thread t = new Thread() {
            @Override

            public void run() {
                try {
                    while (!isInterrupted()) {

                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                TextView timeText = (TextView) findViewById(R.id.tvCurrentTime);

                                long time = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                                String timeString = sdf.format(time);
                                timeText.setText(timeString);

                            }
                        });
                    }
                } catch (InterruptedException e) {

                }
            }
        };

        t.start();


    }

    //clock in time

    public void onClockInButtonPressed(View V) {



        if (V.getId() == R.id.btnClockIn) {



           TextView ClockInTimeTV = (TextView) findViewById(R.id.tvClockInTime);

             long ClockInTime = System.currentTimeMillis();

            SimpleDateFormat TimeFormat = new SimpleDateFormat("hh:mm:ss");

            String ClockInTimeString = TimeFormat.format(ClockInTime);

            ClockInTimeTV.setText(ClockInTimeString);
            ClockInTimeTV.setVisibility(View.VISIBLE);


            //inserting into db

            Timesheets t = new Timesheets();
            t.setTimeIn(ClockInTimeString);

            helper.insertTimeSheetsData(t);

        }

    }

    public void onClockOutButtonPressed(View V) {



        if (V.getId() == R.id.btnClockOut) {



            TextView ClockOutTimeTV = (TextView) findViewById(R.id.tvClockOutTime);

            long ClockOutTime = System.currentTimeMillis();

            SimpleDateFormat TimeFormat = new SimpleDateFormat("hh:mm:ss");

            String ClockOutTimeString = TimeFormat.format(ClockOutTime);

            ClockOutTimeTV.setText(ClockOutTimeString);
            ClockOutTimeTV.setVisibility(View.VISIBLE);


            //inserting into db

            Timesheets t = new Timesheets();
            t.setTimeOut(ClockOutTimeString);

            helper.insertTimeSheetsData(t);

        }

    }

}
