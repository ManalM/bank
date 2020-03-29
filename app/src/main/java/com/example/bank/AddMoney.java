package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.bank.App.CHANNEL_1_ID;

public class AddMoney extends AppCompatActivity {
    String email, name, job, pass;
    DBAdapter adapter;
    EditText amount;
    Button add;
    MediaPlayer md ;
    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        //-----------------toolbar----
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        TextView toolbarText = findViewById(R.id.toolbar_title);

        toolbar.setTitle("");
        toolbarText.setText("Add Money");
//----------------------------------------
        adapter = new DBAdapter(this);

        email = getIntent().getStringExtra("email");
        job = getIntent().getStringExtra("job");
        pass = getIntent().getStringExtra("pass");

        name = getIntent().getStringExtra("name");
//-----------init------
        add = findViewById(R.id.add_amount);
        amount = findViewById(R.id.amount);
        md =MediaPlayer.create(AddMoney.this,R.raw.click1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(view);
            }
        });
        notificationManager = NotificationManagerCompat.from(this);

    }

    public void add(View view) {

        String cash = amount.getText().toString();
        if (cash.isEmpty()) {
            Toast.makeText(this, "Enter the values for all fields", Toast.LENGTH_SHORT).show();
        } else {
            md.start();
            //todo:not showing notification
           adapter.updateData(email, name, pass, cash, job);

            sendNotification(view);
           viewData(view);

        }
    }

    public void viewData(View view) {
        String[] data = adapter.getData(email);
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("Data", data);
        startActivity(intent);
    }
    public void sendNotification(View V){


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("your money added")
                .setContentText("Your balance has been added successfully")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }

}
