package ie.holiday.booktimeoff;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {




    ListView list;

    //listview db
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        list = (ListView) findViewById(R.id.lv);

        db = new DatabaseHelper(this);

        ArrayList<String> myList = new ArrayList<>();
       Cursor data = db.getUserBookingData() ;

        if(data.getCount()==0)
        {

            Toast.makeText(ViewActivity.this, "No Data Inserted!", Toast.LENGTH_LONG).show();
        }
            else{
             while (data.moveToNext()){
              myList.add(data.getString(1));
                 myList.add(data.getString(2));
                 myList.add(data.getString(3));
                ListAdapter myListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1, myList );
                list.setAdapter(myListAdapter );


                //set onitemclick lister

                //
                 // list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String name = parent.getItemAtPosition(position).toString();
                       // Log.d(tag, "you clicked on" + name)

                        Cursor data = db.getUserBookingID(name);

                        int itemID = -1;
                        while(data.moveToNext()){

                            itemID = data.getInt(0);
                        }

                        if(itemID > -1){

                            Toast.makeText(ViewActivity.this, "Data Inserted!", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(ViewActivity.this, EditActivity.class);
                            intent.putExtra("id", itemID);
                            intent.putExtra("name", name);
                            startActivity(intent);
                        }

                        else{
                            Toast.makeText(ViewActivity.this, "No Data Inserted!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }

       // Cursor cursor = db.getUserBookingData();

       // String[] fromField = new String[] {}




    }

    public void ViewBtnPressed(View V)

    {

        Intent intent = new Intent(this,ViewActivity.class);

        startActivity(intent);
    }
}
