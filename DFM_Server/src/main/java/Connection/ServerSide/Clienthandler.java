package Connection.ServerSide;

import DataAccess.DBconnection;
import DataAccess.GenericDAOImpl;

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
            //GenericDAOImpl genericDAO = new GenericDAOImpl();
            //**********************Test for the DB**********************
            System.out.println("test SQL work");
            GenericDAOImpl genericDAO = new GenericDAOImpl();
            System.out.println(genericDAO.getPropertyBydbAndColumnAndTable("gui","swname" , "profiles" , ""));

        }catch (Exception e){}
        //******************************************************************************************
    }
}
