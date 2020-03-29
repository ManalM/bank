package com.example.bank;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String Chanel_1_ID = "Chanel1";

    @Override
    public void onCreate() {
        super.onCreate();
        createnotficationchanels();
    }

    private void createnotficationchanels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    Chanel_1_ID,
                    "chanel1",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("this is Chanel1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
    }
}
