package ie.holiday.booktimeoff;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class HomeScreenActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    //private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

       // toolbar = findViewById(R.id.toolBar);
        //setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
    }

    public void bookingTimeOffButtonPressed(View V)

    {

        Intent i = new Intent(this,BookingActivity.class);

        startActivity(i);
    }

    public void ViewTimeOffButtonPressed(View V)

    {

        Intent intent = new Intent(this,ViewActivity.class);

        startActivity(intent);
    }


    public void timeSheetsButtonPressed(View V)

    {

        Intent i = new Intent(this,TimeSheetsActivity.class);

        startActivity(i);
    }


    public void EditTimeOffButtonPressed(View V)

    {

        Intent i = new Intent(this,EditActivity.class);

        startActivity(i);
    }
}

