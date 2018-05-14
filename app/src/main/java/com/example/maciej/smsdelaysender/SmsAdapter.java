package com.example.maciej.smsdelaysender;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by maciej on 5/14/18.
 */

public class SmsAdapter extends ArrayAdapter<SMessage>
        implements View.OnClickListener  {
    Context context;

    public SmsAdapter(@NonNull Context context, int resource, @NonNull List<SMessage> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        // Check if an existing view is being reused, otherwise inflate the view
        SMessage sms = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_message_item, parent, false);
        }
        TextView numberField = (TextView) convertView.findViewById(R.id.numberField);
        TextView timeField = (TextView) convertView.findViewById(R.id.timeField);
        TextView dateField = (TextView) convertView.findViewById(R.id.dateField);

        numberField.setText("Number: "+sms.getNumber());
        timeField.setText("O godzinier: "+sms.getTimeOfSend());
        dateField.setText("Dnia: "+sms.getDayOfSend());

        return convertView;
    }


    @Override
    public void onClick(View v) {

    }
}
