package ie.holiday.booktimeoff;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static ie.holiday.booktimeoff.R.id.loginButton;

public class LoginActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;


    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Log in");

        Email = (EditText) findViewById(R.id.loginEmail);
        Password = (EditText) findViewById(R.id.loginPassword);
        Login = (Button) findViewById(loginButton);

    }

    public void onButtonClick(View v)
    {


       // if(v.getId() == R.id.loginButton) {
            //Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
           // startActivity(intent);
        //}

        if(v.getId() == R.id.registerButton)

        {
         Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(i);
        }



       // Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
        //startActivity(i);

        //this was my db
       if(v.getId() == R.id.loginButton)
        {
           EditText a = (EditText) findViewById(R.id.loginEmail);
            String str = a.getText().toString();

            EditText b = (EditText) findViewById(R.id.loginPassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);
            if(pass.equals(password))

            {
                Intent i = new Intent(LoginActivity.this, HomeScreenActivity.class);

                startActivity(i);

            }
            else
            {
               Toast temp = Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_LONG);
               temp.show();
            }
        }

       if(v.getId() == R.id.registerButton)

        {
         Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
           startActivity(i);
        }


    }


}
