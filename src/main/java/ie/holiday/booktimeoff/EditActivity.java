package ie.holiday.booktimeoff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave, btnDelete;
    private EditText editable_item;


    DatabaseHelper db;

    private String selectedName;

    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnSave = (Button)findViewById(R.id.Edit_BTN);
        btnDelete = (Button)findViewById(R.id.Delete_BTN);

        editable_item = (EditText) findViewById(R.id.etUpdate);
        db = new DatabaseHelper(this);

        //getting intent extra
        Intent recievedIntent = getIntent();

        //get item id passed in as extra

        selectedID = recievedIntent.getIntExtra("id", -1); //-1 is default

        selectedName = recievedIntent.getStringExtra("name");

        editable_item.setText(selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    db.update(item, selectedID, selectedName);
                }
                else{

                    toast("Please enter new data!");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.delete(selectedID, selectedName);

                editable_item.setText("");

                toast("Removed from database");

            }
        });

    }

    //my toast
    //
    //@param message

    private void toast(String message){

        Toast.makeText(this,message, Toast.LENGTH_LONG).show();;
    }
}
