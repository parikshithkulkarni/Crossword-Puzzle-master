package com.example.multiplayer;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText port, address, mesg;
	private Button send;
	String message;
	int portNum;
	Socket client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*setContentView(R.layout.activity_main);
		
		port = (EditText) findViewById(R.id.port_no);
		address = (EditText) findViewById(R.id.ip_add);
		mesg = (EditText) findViewById(R.id.msg);
		send = (Button) findViewById(R.id.button1);
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				message = mesg.getText().toString();
				mesg.setText("");
				portNum = Integer.parseInt(port.getText().toString());
				
				new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try{
							client = new Socket(address.getText().toString(),portNum);
							PrintWriter pw = new PrintWriter(client.getOutputStream());
							pw.println(message);
							pw.flush();
							pw.close();
							client.close();
							
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					
				}).start();;
				
				
			}
		});*/
		
		setContentView(R.layout.game);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout2);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Action_Man_Bold_Italic.ttf"); 

        gridLayout.removeAllViews();
        
        int total = 64;
        int column = 8;
        int row = total / column;
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);
        for(int i =0, c = 0, r = 0; i < total; i++, c++)
        {
            if(c == column)
            {
                c = 0;
                r++;
            }
        	TextView tv = new TextView(this);
        	tv.setBackgroundResource(R.drawable.back);
        	tv.setText("D");
            tv.setId(i);
            tv.setTextSize(25);
            tv.setGravity(Gravity.CENTER);
            tv.setTypeface(type);
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundColor(Color.GRAY);
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = 40;
            param.width = 40;
            param.rightMargin = 5;
            param.topMargin = 5;
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            tv.setLayoutParams (param);
            gridLayout.addView(tv);
        }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
