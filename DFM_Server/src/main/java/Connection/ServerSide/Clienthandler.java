package Connection.ServerSide;

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
            BufferedReader br = new BufferedReader(new FileReader("D:\\subversion\\Java\\outFile.txt"));
            byte[] b = new byte[30];
            String k = br.readLine();
            DataOutputStream dos = new DataOutputStream(this.clientSocket.getOutputStream());
            dos.writeUTF(k);

            /*********get file from a client********/
            DataInputStream dis = new DataInputStream(this.clientSocket.getInputStream());
            String inputString = dis.readUTF();
            FileOutputStream fos = new FileOutputStream("D:\\subversion\\Java\\inputFile.txt");
            fos.write(inputString.getBytes());
            /***************************************/
        }catch (Exception e){}
        //******************************************************************************************
    }
}
