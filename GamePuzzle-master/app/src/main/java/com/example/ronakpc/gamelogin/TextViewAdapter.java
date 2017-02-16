package com.example.ronakpc.gamelogin;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by parikshithkulkarni on 11/25/15.
 */
public class TextViewAdapter extends BaseAdapter{
    private Context context;
    private final String[] textViewValues;
    TextView[] textView = new TextView[100];
    static int count=0;
    public TextViewAdapter(Context context, String[] textViewValues) {
        this.context = context;
        this.textViewValues = textViewValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_fragment, null);

            // set value into textview

             textView[count] = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView[count].setText(textViewValues[position]);
           // textView[count].setBackgroundColor(Color.GREEN);
            count++;
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return textViewValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
