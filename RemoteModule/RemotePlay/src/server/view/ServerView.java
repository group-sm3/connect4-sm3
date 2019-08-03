/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.view;
import server.cont.ServerController;
import server.model.ServerModel;
import server.model.ModelEvent;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

/**
 *
 * @author anne
 */
public class ServerView extends JFrameView{
    public static final String LISTEN = "Listen";
    private JTextField textField = new JTextField();
    private JLabel userMessage = new JLabel("Please enter a port number.");
    
    public ServerView(ServerModel model, ServerController cont){
        super(model, cont);
        // menu
        this.setPreferredSize(new Dimension(275, 150));
        // panel
        textField.setText("port #");
        textField.setPreferredSize(new Dimension(200, 25));
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(225, 75));
        panel.add(userMessage, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.SOUTH);
        this.getContentPane().add(panel, BorderLayout.NORTH);
        // cleanup on close
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Cleanup networking resources before exiting.
                ((ServerController)getCont()).closeProgram();
            }
        });
        // button
        JPanel buttonPanel = new JPanel();
        Handler handler = new Handler();
        JButton buttonListen = new JButton(LISTEN);
        buttonListen.addActionListener(handler);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(buttonListen, null);
        pack(); 
    }
    
    // implement event-handling code
    // i believe this is how the model alerts the view of a change.
    public void modelChanged(ModelEvent event){
        if (event.getID() == 1){ userMessage.setText(event.getMessage());}
        if (event.getID() == 2){ textField.setText(event.getMessage());}
    }

    class Handler implements ActionListener{
        // event handled locally
        public void actionPerformed(ActionEvent e){
            ((ServerController)getCont()).operation(e.getActionCommand(), textField.getText());
        }
    }
    
    public static void main(String [] args) { new ServerController(); }
}
