package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    String[] data;
    TextView name, email, job, money, logout;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        TextView toolbarText = findViewById(R.id.toolbar_title);

        toolbar.setTitle("");
        toolbarText.setText("View Information");


        //-------------------------------------------
        email = findViewById(R.id.your_email);
        name = findViewById(R.id.your_name);

        job = findViewById(R.id.your_job);
        but = findViewById(R.id.add);
        logout = findViewById(R.id.logout);
        money = findViewById(R.id.money);

        //-------------------------------------------
        data = getIntent().getStringArrayExtra("Data");
        if (data != null) {
            name.setText("your name is:\t" + data[1]);
            money.setText("your balance is:\t" + data[3] + "$");
            job.setText("your job is:\t" + data[2]);
            email.setText("your email is:\t" + data[0]);


        } else {

            Toast.makeText(this, "no data for the user", Toast.LENGTH_SHORT).show();
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut(view);
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoney(view);
            }
        });
    }

    public void addMoney(View view) {
        Intent intent = new Intent(Profile.this, AddMoney.class);
        intent.putExtra("email", data[0]);
        intent.putExtra("name", data[1]);
        intent.putExtra("job", data[2]);
        intent.putExtra("money", data[3]);
        intent.putExtra("pass", data[4]);

        startActivity(intent);
    }

    public void logOut(View view) {

        Intent intent1 = new Intent(Profile.this, MainActivity.class);
        startActivity(intent1);
    }
}
