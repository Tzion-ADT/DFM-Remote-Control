package Connection.ServerSide;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerSideConnection extends SwingWorker<Void , List<String>> {

    private static ServerSocket serverSide;
    private int portNumber;
    private JLabel clientIPLabel;

    public  ServerSideConnection(int portNumber , JLabel clientIPLabel){
        this.portNumber = portNumber;
        this.clientIPLabel = clientIPLabel;
    }

    /*
    Opening a server that will listen and wait for the client
    When the client is connected --> will establish socket to talk for every client
     */

    @Override
    protected Void doInBackground() throws Exception {
        try{
            serverSide = new ServerSocket(portNumber);
            System.out.println("Port number is: "+ portNumber);

            while(true){
                try{
                    Socket clientSide = serverSide.accept();
                    System.out.println("connected to " + clientSide.getInetAddress().getHostAddress());

                    Thread clientSideHandlerThread = new Thread(new Clienthandler(clientSide));
                    clientSideHandlerThread.start();

                    List<String> clientDetailsList = new ArrayList<>();
                    clientDetailsList.add(clientSide.getInetAddress().getHostAddress());
                    clientDetailsList.add(clientSide.getInetAddress().getHostName());

                    publish(clientDetailsList);//this will help to use the data in every new connection

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    if(serverSide != null){
//                        serverSide.close();
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
            }
            System.out.println(this.clientIPLabel.getText() + "</html>");
        }
    }

    //talks to the client side
}