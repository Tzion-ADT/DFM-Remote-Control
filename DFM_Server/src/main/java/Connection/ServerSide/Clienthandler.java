package Connection.ServerSide;

import DataAccess.GenericDAOImpl;
import LogInfo.LoggerInfo;

import java.net.Socket;
import java.util.Set;

////////////////////////////////////////////////////////////////////////////////
//
//  COPYRIGHT (c) 2024 ADT, INC.
//
//  This software is the property of ADT Industries, Inc.
//  Any reproduction or distribution to a third party is
//  strictly forbidden unless written permission is given by an
//  authorized agent of ADT.
//
//  DESCRIPTION
//		Definition for client handler class
//      Handel the communication with the user: 1.Sending data, events ,etc...
//                                              2.Get data to change and events from the user
//
//
//	Date		Name								Description
//	----------------------------------------------------------------------------
// 2024         Tzion
//
//=============================================================================

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
                    //if here there is an exception --> connection closed
                    clientSocket.getOutputStream().write(0);

                }catch (Exception e){
                    System.out.println("Clienthandler --> catch: "+e.toString());
                    LoggerInfo.getLogger().error("Connection closed: "+currentIP);
                    break;
                }

                //GenericDAOImpl genericDAO = new GenericDAOImpl();
                //**********************Test for the DB**********************
                System.out.println("test SQL work");
                GenericDAOImpl genericDAO = new GenericDAOImpl();
                System.out.println(genericDAO.getPropertyBydbAndColumnAndTable("gui", "swname", "profiles", ""));
            }
            clientSocket.close();
            LoggerInfo.getLogger().error("Connection closed: "+currentIP);

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
