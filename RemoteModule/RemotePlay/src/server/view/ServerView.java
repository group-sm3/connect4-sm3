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
    
    public ServerView(ServerModel model, ServerController cont){
        // vars
        super(model, cont);
        textField.setText("port #");
        // jframe
        this.setPreferredSize(new Dimension(250, 100));
        this.getContentPane().add(textField, BorderLayout.NORTH);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Cleanup networking resources before exiting.
                ((ServerController)getCont()).closeProgram();
            }
        });
        // panels
        JPanel buttonPanel = new JPanel();
        Handler handler = new Handler();
        JButton buttonListen = new JButton(LISTEN);
        buttonListen.addActionListener(handler);
        // buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.add(buttonListen, null);
        pack(); 
    }
    
    // implement event-handling code
    // i believe this is how the model alerts the view of a change.
    public void modelChanged(ModelEvent event){
        String message = event.getMessage();
        textField.setText(message);
    }

    class Handler implements ActionListener{
        // event handled locally
        public void actionPerformed(ActionEvent e){
            ((ServerController)getCont()).operation(e.getActionCommand(), textField.getText());
        }
    }
    public static void main(String [] args) { new ServerController(); }
}
