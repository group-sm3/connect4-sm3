package view;
import view.ClientView;
import cont.ClientController;
import model.ClientModel;
import model.ModelEvent;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

/**
 * ClientView is the forefront of user interaction.  It presents the user with
 * information the software requires - port number and ip address - and alerts the 
 * user if the input is invalid, and alerts the user if the input is valid and 
 * a connection is made.
 * @author Anne Leach
 */
public class ClientView extends JFrameView{
    
    public static final String CONNECT = "Connect";
    private JTextField textFieldPort = new JTextField();
    private JTextField textFieldAddress = new JTextField();
    private JLabel userMessage = new JLabel("\nPlease enter port number and ip address.");
    Handler handler = new Handler();
    
    /**
     * Construct which sets the ClientModel and ClientController objects.
     * @param model ClientModel object
     * @param cont ClientController object
     */
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

    /**
     * Upon change in state of model, this method updates the view based upon
     * the information provided by the ServerModel object.  The possible changes 
     * are the user message space, the textfield for the port number,
     * or the textfield for the ip address.
     * @param event ModelEvent object which provides information on how to 
     * update the view.
     */
    public void modelChanged(ModelEvent event){
        if (event.getID() == 1){ userMessage.setText(event.getMessage()); }
        if (event.getID() == 2){ textFieldPort.setText(event.getMessage()); }
        if (event.getID() == 3){ textFieldAddress.setText(event.getMessage()); }
    }
    
    /**
     * Inner class which locally handles ActionEvent objects (e) by invoking 
     * the controller object to determine which operation the model object 
     * should perform.
     */
    class Handler implements ActionListener{
        // event handled locally
        public void actionPerformed(ActionEvent e){
            ((ClientController)getCont()).operation(e.getActionCommand(), textFieldPort.getText(), textFieldAddress.getText());
        }
    }

    /**
     * Main method which initiates a ClientController object.
     * @param args Command line arguments which are not in use for this version.
     * Time permitting would like to add commandline option to run GUI or 
     * terminal version.
     */
    public static void main(String [] args) { new ClientController(); }
}
