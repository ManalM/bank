package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DBAdapter adapter;
    private EditText email, pass,confirmPass ,name,job;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //--------------toolbar--------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        TextView toolbarText = findViewById(R.id.toolbar_title);

        toolbar.setTitle("");
        toolbarText.setText("Create account");

        //---------------------database---------

        adapter = new DBAdapter(this);
        //-------------init------------------
        email = findViewById(R.id.register_username);
        pass = findViewById(R.id.register_pass);
       name = findViewById(R.id.register_name);
        confirmPass = findViewById(R.id.register_c_pass);

        job = findViewById(R.id.register_job);
        register=findViewById(R.id.register_btn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameInput = name.getText().toString();
                String emailInput = email.getText().toString();
                String jobInput = job.getText().toString();
                String passInput = pass.getText().toString();
                String confirmPassInput = confirmPass.getText().toString();

                MediaPlayer md = MediaPlayer.create(Register.this, R.raw.click1);

                if (nameInput.isEmpty() && emailInput.isEmpty() && jobInput.isEmpty() && passInput.isEmpty() && confirmPassInput.isEmpty()) {
                    Toast.makeText(Register.this, "Enter the values for all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // long id =
                    if (passInput.equals(confirmPassInput)) {
                        adapter.insertData(nameInput, emailInput, passInput,"0", jobInput);
                        md.start();

                        Toast.makeText(Register.this, "you registered successfully ", Toast.LENGTH_SHORT).show();

                        viewData(view);

                    } else {
                        Toast.makeText(Register.this, "Password doesn't match", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public void viewData(View view) {
        String[] data = adapter.getData(email.getText().toString());
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("Data", data);
        startActivity(intent);
    }

}
