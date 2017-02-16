package com.example.ronakpc.gamelogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.net.Socket;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Button loginbtn;
    TextView register_label;
    LinearLayout signup_new;
    String userName = null,pass = null,result = null;
    int portNum = 9080;
    String address = "10.0.2.2";
    MainActivity activity;
    Socket client;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,true);
        loginbtn = (Button)view.findViewById(R.id.login_btn);
        final EditText content_text = (EditText)view.findViewById(R.id.pass_edit);
        final EditText pass_edit = (EditText)view.findViewById(R.id.content_text);
        register_label = (TextView)view.findViewById(R.id.register_label);
        signup_new = (LinearLayout) view.findViewById(R.id.signup_new);
        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                userName = content_text.getText().toString();
                pass = pass_edit.getText().toString();
                /*Toast.makeText(getActivity(), "Redirecting...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("com.example.ronakpc.gamelogin.GRIDACTIVITY");
                startActivity(intent);*/

                        try{
                            client = new Socket("140.182.66.78",5656);
                            SocketConnection.setSocket(client);
                            System.out.println(client.getInetAddress());
                            System.out.println(SocketConnection.getSocket().getInetAddress());
                            PrintWriter pw = new PrintWriter(client.getOutputStream());
                            pw.println(userName);
                            pw.println(pass);
                            pw.flush();
                            //pw.close();
                            //InputStreamReader isRead = new InputStreamReader(SocketConnection.getSocket().getInputStream());
                            //SocketConnection.getbRead().readLine();
                            result = SocketConnection.getbRead().readLine();
                            System.out.println(result);

                            if(result.equalsIgnoreCase("waiting")){
                                Toast.makeText(getActivity(), "Waiting for other users...",Toast.LENGTH_SHORT).show();
                                result = SocketConnection.getbRead().readLine();
                                System.out.println(result);

                                if(result.contains("Matrix")){

                                   // System.out.println(result);
                                    Toast.makeText(getActivity(), "Redirecting...",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent("com.example.ronakpc.gamelogin.GRIDACTIVITY");
                                    startActivity(intent);
                                }

                            }else{
                                Toast.makeText(getActivity(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                                    content_text.getText().clear();
                                    pass_edit.getText().clear();
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

        });
        signup_new.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                boolean register = getAuthentication("ron","ron");
                if(register){
                    Intent intent = new Intent("com.example.ronakpc.gamelogin.SIGNUPACTIVITY");
                    startActivity(intent);
                }else{
                    content_text.setText("");
                    pass_edit.setText("");
                }
            }
        });
        return view;
    }

    public boolean getAuthentication(String username,String password){

        return true;
    }
}
