package Connection.ServerSide;
import LogInfo.LoggerInfo;

import javax.swing.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
//		Definition for Server side connection : listening and waiting for client sides to connect
//      This operation is in the background so the UI available for the user
//
//
//	Date		Name								Description
//	----------------------------------------------------------------------------
// 2024         Tzion
//
//=============================================================================


public class ServerSideConnection extends SwingWorker<Void , List<String>> {

    private static ServerSocket serverSide;
    private int portNumber;
    private Set<String> Ips;//using set because it is unique datastructures
    private JLabel clientIPLabel;

    public  ServerSideConnection(int portNumber , JLabel clientIPLabel){
        this.portNumber = portNumber;
        this.clientIPLabel = clientIPLabel;
        Ips = new HashSet<>();
    }

    /*
    Opening a server that will listen and wait for the client
    When the client is connected --> will establish socket to talk for every client
     */

    @Override
    protected Void doInBackground() {
        try{
            serverSide = new ServerSocket(portNumber);
            System.out.println("Port number is: "+ portNumber);

            while(true){
                try{

                        Socket clientSide = serverSide.accept();
                        if(!Ips.contains(clientSide.getInetAddress().getHostAddress())) {
                            LoggerInfo.getLogger().info("connection established for "+clientSide.getInetAddress().getHostAddress()+ ", on port "+portNumber);

                            System.out.println("connected to " + clientSide.getInetAddress().getHostAddress());

                            //in the following , the connection to the DB will establish
                            Thread clientSideHandlerThread = new Thread(new Clienthandler(clientSide , Ips));
                            clientSideHandlerThread.start();

                            List<String> clientDetailsList = new ArrayList<>();
                            clientDetailsList.add(clientSide.getInetAddress().getHostAddress());
                            clientDetailsList.add(clientSide.getInetAddress().getHostName());

                            publish(clientDetailsList);//this will help to use the data in every new connection
                        }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    if(serverSide != null){
                        //serverSide.close();
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void process(List<List<String>> chunks) {
        if(chunks.get(0).size() > 1) {
            for (List<String> clientData : chunks) {
                this.clientIPLabel.setText(this.clientIPLabel.getText() + "<br>"
                        + "IP :" + clientData.get(0) + " , PC Name :" + clientData.get(1));
                Ips.add(clientData.get(0));
            }
            System.out.println(this.clientIPLabel.getText() + "</html>");
        }
    }

    //talks to the client side
}