/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;
import client.view.*;
import client.cont.ClientController;
import client.model.ClientModel;
import client.model.ModelEvent;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import server.cont.ServerController;

/**
 *
 * @author anne
 */
public class ClientView extends JFrameView{
    public static final String CONNECT = "Connect";
    private JTextField textFieldPort = new JTextField();
    private JTextField textFieldAddress = new JTextField();
    Handler handler = new Handler();
    
    public ClientView(ClientModel model, ClientController cont){
        super(model, cont);
        textFieldPort.setText("port #");
        textFieldAddress.setText("ip address");
        this.setPreferredSize(new Dimension(250, 120));
        this.getContentPane().add(textFieldPort, BorderLayout.NORTH);
        this.getContentPane().add(textFieldAddress, BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Cleanup networking resources before exiting.
                ((ClientController)getCont()).closeProgram();
            }
        });
       // Button
        JButton buttonConnect = new JButton(CONNECT);
        buttonConnect.addActionListener(handler);
        JPanel buttonPanel = new JPanel();
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(buttonConnect, null);
        pack(); 
    }

    public void modelChanged(ModelEvent event){
        String message = event.getMessage();
        textFieldPort.setText(message);
    }

    class Handler implements ActionListener{
        // event handled locally
        public void actionPerformed(ActionEvent e){
            ((ClientController)getCont()).operation(e.getActionCommand(), textFieldPort.getText(), textFieldAddress.getText());
        }
    }
    public static void main(String [] args) { new ClientController(); }
}
