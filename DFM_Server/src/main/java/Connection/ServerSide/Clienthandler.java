package Connection.ServerSide;

import DataAccess.DBconnection;

import java.io.*;
import java.net.Socket;

public class Clienthandler implements Runnable{

    private Socket clientSocket;

    public Clienthandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        //************************************for test ONLY*****************************************
        try {
            DBconnection
        }catch (Exception e){}
        //******************************************************************************************
    }
}
