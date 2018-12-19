package ie.holiday.booktimeoff;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    private static final String TAG = "BookingActivity";

    private TextView theDate1;
    private Button goToCalender1;
    private TextView theDate2;
    private Button goToCalender2;
    private Button add_data;
    private String record;


    //listview db
    DatabaseHelper db;

    Spinner add_reason;

    List<String> listViewValues = new ArrayList<String>();
    //Intent newintent = new Intent(BookingActivity.this, ViewActivity.class);

    private TextView mDisplayFromDate;
    private DatePickerDialog.OnDateSetListener mFromDateSetListener;

    private TextView mDisplayToDate;
    private DatePickerDialog.OnDateSetListener mToDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Book Time Off");

        db = new DatabaseHelper(this);


        //date picker starts here

        mDisplayFromDate = (TextView) findViewById(R.id.tvDateFrom);
        mDisplayFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar FromCal = Calendar.getInstance();
                int year = FromCal.get(Calendar.YEAR);
                int month = FromCal.get(Calendar.MONTH);
                int day = FromCal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog
                        (BookingActivity.this, mFromDateSetListener, year, month, day);

                // dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT ));
                dialog1.show();
            }
        });

        mFromDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy :" + dayOfMonth + "/" + month + "/" + year);
                String FromDate = dayOfMonth + "/" + month + "/" + year;
                mDisplayFromDate.setText(FromDate);
            }
        };

        //second date

        mDisplayToDate = (TextView) findViewById(R.id.tvDateTo);
        mDisplayToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar ToCal = Calendar.getInstance();
                int year = ToCal.get(Calendar.YEAR);
                int month = ToCal.get(Calendar.MONTH);
                int day = ToCal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog2 = new DatePickerDialog
                        (BookingActivity.this, mToDateSetListener, year, month, day);

                // dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT ));
                dialog2.show();
            }
        });

        mToDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy :" + dayOfMonth + "/" + month + "/" + year);
                String ToDate = dayOfMonth + "/" + month + "/" + year;
                mDisplayToDate.setText(ToDate);
            }
        };

//Spinner starts here

        //reference: https://www.youtube.com/watch?v=urQp7KsQhW8

        Spinner mySpinner = (Spinner) findViewById(R.id.VacationTypeSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(BookingActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.VacationTypes));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        record = "Paid Vacation Time";
                        break;

                    case 1:

                        record = "Sick Leave";
                        break;

                    case 2:

                        record = "Unpaid Leave";
                        break;
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


        // mySpinner.setOnItemSelectedListener(this);


        // add_data = (Button) findViewById(R.id.submit);


        //add_data.setOnClickListener(new View.OnClickListener() {
        // @Override
        //  public void onClick(View v) {

        //
        //String[] listItems = {record, date1, date2};
        // listViewValues.add(record);
        //listViewValues.add(date1);
        // listViewValues.add(date2);

        //newintent.putExtra("Array", String.valueOf(listViewValues));
        //startActivity(newintent);


        // record = add_reason.toString();

        // db.insertData(record, date1, date2);

                 /*if(db.insertData(record, date1, date2));
                 {

                     Toast.makeText(BookingActivity.this, "Data Added", Toast.LENGTH_SHORT);
                 }*/


    }

    public void onSubmitButtonClick(View v) {
        if (v.getId() == R.id.submit) {

            String ReasonString = record;
            String FromDateString = mDisplayFromDate.getText().toString();
            String toDateString = mDisplayToDate.getText().toString();

            //inserting into db



                UserBookingData b = new UserBookingData();
                b.setReason(ReasonString);
                b.setToDate(toDateString);
                b.setFromDate(FromDateString);

                db.insertUserBookingData(b);






                Log.d(TAG, "help");



        }

    }

}

