package com.example.maciej.smsdelaysender;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);
        }
        return convertView;
    }


    @Override
    public void onClick(View v) {

    }
}
