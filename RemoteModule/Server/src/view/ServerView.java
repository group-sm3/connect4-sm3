package view;
import cont.ServerController;
import model.ServerModel;
import model.ModelEvent;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

/**
 * ServerView is the main menu that interacts with the user.  From here
 * the user can set the port to listen to.  Corrective messages are displayed
 * should the user input invalid data.  The user is also alerted that the server
 * is actively listening.
 * 
 * @author Anne Leach - AL1404
 */
public class ServerView extends JFrameView{
    
    public static final String LISTEN = "Listen";
    private JTextField textField = new JTextField();
    private JLabel userMessage = new JLabel("Please enter a port number.");
    
    /**
     *
     * @param model ServerModel object
     * @param cont ServerController object
     */
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

    /**
     *
     * @param event ModelEvent object containing information, such as id and 
     * event messages.
     */
    public void modelChanged(ModelEvent event){
        if (event.getID() == 1){ userMessage.setText(event.getMessage());}
        if (event.getID() == 2){ textField.setText(event.getMessage());}
    }
    /**
     *  Inner class which takes an ActionEvent and alerts the controller to
     * select the appropriate operation based on the GUI action.
     */
    class Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ((ServerController)getCont()).operation(e.getActionCommand(), textField.getText());
        }
    }
    
    /**
     *
     * @param args In event of arguments passed via command line.  Not used here.
     * Time permitting, will add a command option to run the server with a GUI
     * or from terminal only.
     */
    public static void main(String [] args) { new ServerController(); }
}
