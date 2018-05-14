package com.example.maciej.smsdelaysender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView  textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, DelayMsg.class));
        textView = (TextView) findViewById(R.id.textView);

        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        int num = intent.getIntExtra("sizeofList", 0);
                        textView.setText("Zostało "+num+" wiadomości!");
                    }
                }, new IntentFilter(DelayMsg.ACTION_LOCATION_BROADCAST)
        );

    }

    public void addNewMessage(View v){
        Intent myIntent = new Intent(MainActivity.this, AddSmsActivity.class);
        MainActivity.this.startActivity(myIntent);
    }


}
