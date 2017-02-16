package com.example.ronakpc.gamelogin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;

/**
 * Created by parikshithkulkarni on 11/23/15.
 */
public class GridActivity extends AppCompatActivity {

    Socket client;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);
        /*final GridView gridview = (GridView) findViewById(R.id.gridview);
        System.out.println(SocketConnection.getSocket().getInetAddress());
       // client = SocketConnection.getSocket();
        try {
            //InputStreamReader isRead = new InputStreamReader(SocketConnection.getSocket().getInputStream());
            //BufferedReader bRead = new BufferedReader(isRead);
            result = SocketConnection.getbRead().readLine();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        String[] strArr = {"R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","Z"};
        gridview.setAdapter(new TextViewAdapter(this, strArr));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GridActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                GridView mGridView = gridview;
                ViewGroup gridChild = (ViewGroup) mGridView.getChildAt(0);
                final int size = mGridView.getChildCount();
               *//* for (int i = 0; i < size; i++) {
                    ViewGroup gridChild = (ViewGroup) mGridView.getChildAt(i);
                    int childSize = gridChild.getChildCount();
                    for (int k = 0; k < childSize; k++) {
                        if (gridChild.getChildAt(k) instanceof TextView) {
                            gridChild.getChildAt(k).setBackgroundColor(Color.GREEN);
                        }
                    }
                }*//*

                gridChild.getChildAt(0).setBackgroundColor(Color.GREEN);
            }

        });*/
    }

}
