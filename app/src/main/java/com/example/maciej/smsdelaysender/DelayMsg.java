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


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        startService();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startService()
    {
        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                Log.i("Sms", "Loop");
                sendSMS();
            }};
        timer.schedule(task, 1000, 30000);
    }
    private void sendSMS()
    {
        Log.i("Sms", "Check messages");
        if(!smss.isEmpty()) {
            Calendar calendar1 = Calendar.getInstance();
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String currentDate;
            String datadb;
            for (SMessage i : smss) {
                currentDate = formatter1.format(calendar1.getTime());
                datadb = i.getDayOfSend() + " " + i.getTimeOfSend();
                Log.i("Data", "Current date"+String.valueOf(currentDate));
                Log.i("Data", "Date of send"+String.valueOf(datadb));
                Log.i("Data", "Result of compare"+String.valueOf(currentDate.compareTo(datadb)));
                if (currentDate.compareTo(datadb) >= 0) {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(i.getNumber(), null, i.getMessage(), null, null);
                    smss.remove(i);
                    Log.i("Sms", "Message is sended");
                }
            }
        }
        sendBroadcastMessage();
    }

    private void sendBroadcastMessage() {
            Intent intent = new Intent(ACTION_LOCATION_BROADCAST);
            intent.putExtra("sizeOfList", smss.size());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }



}
