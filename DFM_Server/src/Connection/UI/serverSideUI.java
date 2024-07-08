package Connection.UI;

import Connection.ServerSide.ServerSideConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static Connection.ServerSide.ServerSideConnection.*;

public class serverSideUI {
    public static void main (String []args) {
        setUi();
    }

    /*
    Set up th UI for port number choosing
   */
    private static void setUi (){

        //UI with one button for activate the app and add port number
        JFrame simpleUI = new JFrame("DFM Remote Control Server");
        simpleUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simpleUI.setSize(800 , 500);
        simpleUI.setLocationRelativeTo(null);

        simpleUI.setLayout(null);

        JLabel mainLable = new JLabel("Connectet IPs :");

        JLabel clientIPLabel = new JLabel("<html>");

        JScrollPane scroolPanel = new JScrollPane(clientIPLabel);


        //will contain the IP address

        //button for choosing port number
        JButton activateBtn = new JButton("Choose Port Number");
        activateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String portNumberString = JOptionPane.showInputDialog("Insert Port Number");

                if(portNumberString != null) {
                    try {
                        int portNUmber = Integer.parseInt(portNumberString);
                        SwingWorker<Void, List<String>> serverWorker = new SwingWorker<Void, List<String>>() {

                            @Override
                            protected Void doInBackground() throws Exception {
                                ServerSideConnection runServer = new ServerSideConnection(portNUmber , clientIPLabel);
                                runServer.execute();
                                return null;
                            }
                        };

                        activateBtn.setEnabled(false);
                        serverWorker.execute();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(simpleUI, "Worng input , please choose again");
                    }
                }
            }
        });

        //set button location
        activateBtn.setBounds(230,290,350,100);
        activateBtn.setFont(new Font("Arial", Font.PLAIN, 18));

        //set labels
        mainLable.setFont(new Font("Arial", Font.PLAIN, 24));
        mainLable.setBounds(80,10,350,100);

        clientIPLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        clientIPLabel.setBounds(40,50,700,220);

        scroolPanel.setLocation(new Point(40 , 80));
        scroolPanel.setSize(new Dimension(700,200));

        simpleUI.getContentPane().add(activateBtn);
        simpleUI.getContentPane().add(clientIPLabel);
        simpleUI.getContentPane().add(mainLable);
        simpleUI.getContentPane().add(scroolPanel);

        //activate the UI
        simpleUI.setVisible(true);
    }

}
