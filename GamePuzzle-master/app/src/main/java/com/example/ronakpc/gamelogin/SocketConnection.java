package com.example.ronakpc.gamelogin;

import android.app.Application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Created by ParikshithKulkarni on 11/29/2015.
 */
public class SocketConnection{

    private static Socket socket;

    public static BufferedReader getbRead() {
        return bRead;
    }

    public static void setbRead(BufferedReader bRead) {
        SocketConnection.bRead = bRead;
    }

    private static BufferedReader bRead;

    public static synchronized Socket getSocket() {
        return socket;
    }

    public static synchronized void setSocket(Socket socket) {
        SocketConnection.socket = socket;
        try {
            bRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
