package Connection.ServerSide;

import DataAccess.DBconnection;
import DataAccess.GenericDAOImpl;
import LogInfo.LoggerInfo;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Set;

public class Clienthandler implements Runnable{

    private Socket clientSocket;
    private String currentIP;
    private Set <String> Ips;

    public Clienthandler(Socket clientSocket , Set<String> Ips){
        this.clientSocket = clientSocket;
        currentIP = this.clientSocket.getInetAddress().getHostAddress();
        this.Ips = Ips;
    }
    @Override
    public void run() {
        //************************************for test ONLY*****************************************
        try {
            while(!clientSocket.isClosed()) {
                try {
                    clientSocket.getOutputStream().write(0);
                }catch (Exception e){
                    System.out.println("Clienthandler --> catch: "+e.toString());
                    LoggerInfo.getLogger().error("Connection closed: "+currentIP);
                    break;
                }

                //GenericDAOImpl genericDAO = new GenericDAOImpl();
                //**********************Test for the DB**********************
                //System.out.println("test SQL work");
                //GenericDAOImpl genericDAO = new GenericDAOImpl();
                //System.out.println(genericDAO.getPropertyBydbAndColumnAndTable("gui", "swname", "profiles", ""));
            }
            clientSocket.close();
            System.out.println("Out of While loop");


        }catch (Exception e){
            Ips.remove(currentIP);
            LoggerInfo.getLogger().error("******************************************");
            LoggerInfo.getLogger().error("Clienthandler --> Thread exception");
            LoggerInfo.getLogger().error(e.toString());
            LoggerInfo.getLogger().error("******************************************\n");
        }
        //******************************************************************************************
    }
}
