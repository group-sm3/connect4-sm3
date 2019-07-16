package view;

import controller.Controller;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import model.Model;

public class MainMenuView{
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;
    public static final String SPGAME = "Singleplayer";
	public static final String MPGAME = "Multiplayer";
	public static final String SETTINGS = "Settings";
	public static final String EXIT = "Exit";
    
    private final Model model;
    private final Controller controller;
    private final Stage stage;

    public MainMenuView(Stage stage, Controller controller, Model model) {
    	this.stage = stage;
        this.model = model;
        this.controller = controller;
        
        //Title Label
        Text title = new Text();
        title.setX(10.0);
        title.setY(50.0);
        title.setCache(true);
        title.setText("TicChess4 Game");
        title.setFill(Color.DARKGRAY);
        title.setFont(Font.font(null, FontWeight.BOLD, 30));
        Reflection r = new Reflection();
        r.setFraction(0.7);
        title.setEffect(r);
        
        //Singleplayer button
        Button b1 = new Button(SPGAME);
        b1.setOnAction(e -> 
        {
        	System.out.println("SP Button press");//TODO
        });
        
        //Multiplayer button
		Button b2 = new Button(MPGAME);
		b2.setOnAction(e -> 
        {
        	System.out.println("MP Button press");//TODO
        });
		
		//Settings button
		Button b3 = new Button(SETTINGS);
		b3.setOnAction(e -> 
        {
        	System.out.println("Settings Button press");//TODO
        });
		
		//Exit button
		Button b4 = new Button(EXIT);
		b4.setOnAction(e -> 
    	{
    		if (e.getSource() == b4)
			{
				stage.close();
				e.consume();
			}
    	});
		
		//Create horizontal column for title
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(20,15,35,15));
		hbox.getChildren().add(title);
		hbox.setAlignment(Pos.CENTER);

		//Create vertical column layout with buttons
        VBox vbox = new VBox(15);
        vbox.getChildren().addAll(b1, b2, b3, b4);
        vbox.setAlignment(Pos.CENTER);
        
        //Sets the Pane
        BorderPane bpane = new BorderPane();
		bpane.setId("Main");
		bpane.setTop(hbox);
		bpane.setCenter(vbox);	

        // Set the scene with pane and dimensions
        Scene scene = new Scene(bpane, WIDTH, HEIGHT);
        stage.setScene(scene);
    }
}
