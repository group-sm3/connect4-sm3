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
    private JLabel userMessage = new JLabel("\nPlease enter port number and ip address.");
    Handler handler = new Handler();
    
    public ClientView(ClientModel model, ClientController cont){
        super(model, cont);
        this.setPreferredSize(new Dimension(275, 160));
        // textfield panel
        textFieldPort.setText("port #");
        textFieldPort.setPreferredSize(new Dimension(200, 20));
        textFieldAddress.setText("address");
        textFieldAddress.setPreferredSize(new Dimension(200, 20));
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(textFieldPort, BorderLayout.NORTH);
        textFieldPanel.add(textFieldAddress, BorderLayout.CENTER);
        // menu 
        this.getContentPane().add(userMessage, BorderLayout.NORTH);
        this.getContentPane().add(textFieldPanel, BorderLayout.CENTER);
        // cleanup on close
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { ((ClientController)getCont()).closeProgram();}
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
        if (event.getID() == 1){ userMessage.setText(event.getMessage()); }
        if (event.getID() == 2){ textFieldPort.setText(event.getMessage()); }
        if (event.getID() == 3){ textFieldAddress.setText(event.getMessage()); }
    }

    class Handler implements ActionListener{
        // event handled locally
        public void actionPerformed(ActionEvent e){
            ((ClientController)getCont()).operation(e.getActionCommand(), textFieldPort.getText(), textFieldAddress.getText());
        }
    }
    public static void main(String [] args) { new ClientController(); }
}
