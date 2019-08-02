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
        this.setPreferredSize(new Dimension(250, 100));
        this.getContentPane().add(textFieldPort, BorderLayout.NORTH);
        this.getContentPane().add(textFieldAddress, BorderLayout.CENTER);
       // Button
        JButton buttonConnect = new JButton(CONNECT);
        buttonConnect.addActionListener(handler);
        JPanel buttonPanel = new JPanel();
        this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.add(buttonConnect, null);
        pack(); 
    }
    
    // implement event-handling code
    // i believe this is how the model alerts the view of a change.
    public void modelChanged(ModelEvent event){
        String message = event.getMessage();
        textFieldPort.setText(message);
//        String msg = event.getAmount() + "";
//        textFieldPort.setText(msg);
    }

    class Handler implements ActionListener{
        // event handled locally
        public void actionPerformed(ActionEvent e){
            ((ClientController)getCont()).operation(e.getActionCommand());
        }
    }
    public static void main(String [] args) { new ClientController(); }
}
