package com.example.laptopBuying;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText firstname;
    EditText lastname;
    EditText username;
    EditText password;
    EditText email;
    EditText confirmPassword;
    Button registerBtn;
    TextView LoginTxtVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.cnfpassword);

        registerBtn = (Button) findViewById(R.id.RegisterBtn);
        email = (EditText) findViewById(R.id.email);

        LoginTxtVw = (TextView) findViewById(R.id.LoginTxtVw);
        LoginTxtVw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginIntent);

            }

        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstname.getText().toString().trim();
                String lname = lastname.getText().toString().trim();
                String user = username.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String Cnf_pwd = confirmPassword.getText().toString().trim();

                if (pwd.equals(Cnf_pwd)){
                    //add the user to the database
                    long val = db.addUser(fname, lname, user, mail, pwd);

                    if (val > 0 ){
                        Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLOgin = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(moveToLOgin);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                    }

                }
                else {
                    Toast.makeText(RegisterActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
