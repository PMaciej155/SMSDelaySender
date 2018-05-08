package com.example.maciej.smsdelaysender;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by maciej on 5/6/18.
 */

public class DelayMsg extends Service {

    public static final int sizeMess = 0;
    public static final String ACTION_LOCATION_BROADCAST = DelayMsg.class.getName();
    private Timer timer = new Timer();
    static List<SMessage> smss = new ArrayList<SMessage>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        startService();

    }

    private void startService()
    {
        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                Log.i("Sms", "Petla");
                sendSMS();
            }};
        timer.schedule(task, 1000, 30000);
    }
    private void sendSMS()
    {
        Log.i("Sms", "Przegladanie wiadomosc");
        if(!smss.isEmpty()) {
            Calendar calendar1 = Calendar.getInstance();
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            for (SMessage i : smss) {
                String currentDate = formatter1.format(calendar1.getTime());
                String datadb = i.dayOfSend + " " + i.timeOfSend;
                Log.i("Data", String.valueOf(currentDate));
                Log.i("Data", String.valueOf(datadb));
                Log.i("Data", String.valueOf(currentDate.compareTo(datadb)));
                if (currentDate.compareTo(datadb) >= 0) {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(i.number, null, i.message, null, null);
                    smss.remove(i);
                    Log.i("Sms", "Wyslałano wiadomosc");
                }
            }
        }
        sendBroadcastMessage();
    }

    private void sendBroadcastMessage() {
            Intent intent = new Intent(ACTION_LOCATION_BROADCAST);
            intent.putExtra("size", smss.size());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }



}
