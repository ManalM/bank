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
    String email1, name1, job1, pass,cash;
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
        email1 =data[0];
        name1 = data[1];
        job1=data[2];
        cash =data[3];
        pass =data[4];
        if (data != null) {
            name.setText("your name is:\t" + name1);
            money.setText("your balance is:\t" + cash + "$");
            job.setText("your job is:\t" + job1);
            email.setText("your email is:\t" + email1);


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
        intent.putExtra("email", email1);
        intent.putExtra("name", name1);
        intent.putExtra("job", job1);
        intent.putExtra("money", cash);
        intent.putExtra("pass", pass);

        startActivity(intent);
    }

    public void logOut(View view) {

        Intent intent1 = new Intent(Profile.this, MainActivity.class);
        startActivity(intent1);
    }
}
