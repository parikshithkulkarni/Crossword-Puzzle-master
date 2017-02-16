package com.example.ronakpc.gamelogin;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.graphics.Color;
import android.widget.ArrayAdapter;

import java.net.Socket;

/**
 * Created by parikshithkulkarni on 11/23/15.
 */
public class GridActivityFragment extends Fragment {

    MainActivity mainActivity;
    Socket client;
    String[] strArr;

    public GridActivityFragment() {

    }
    TextView welcome_text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.grid_main, container, false);
        final GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        try {
            int i = 0;
            while (SocketConnection.getbRead()!=null){
                strArr[i] = SocketConnection.getbRead().readLine();
                i++;
                System.out.println(strArr[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       // String[] strArr = {"R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q","R","A","B","C","E","Q"};
        gridview.setAdapter(new TextViewAdapter(getActivity(), strArr));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
                GridView mGridView = gridview;
                ViewGroup gridChild = (ViewGroup) mGridView.getChildAt(position);
                final int size = mGridView.getChildCount();
               /* for (int i = 0; i < size; i++) {
                    ViewGroup gridChild = (ViewGroup) mGridView.getChildAt(i);
                    int childSize = gridChild.getChildCount();
                    for (int k = 0; k < childSize; k++) {
                        if (gridChild.getChildAt(k) instanceof TextView) {
                            gridChild.getChildAt(k).setBackgroundColor(Color.GREEN);
                        }
                    }
                }*/

                System.out.println(position+":"+size+":"+gridChild.getChildCount());
                gridChild.getChildAt(0).setBackgroundColor(Color.GREEN);
            }

        });

        return gridview;
    }
}
