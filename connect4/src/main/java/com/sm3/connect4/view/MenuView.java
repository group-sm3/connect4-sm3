package com.sm3.connect4.view;

import com.sm3.connect4.controller.Controller;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

import com.sm3.connect4.model.Model;

public class MenuView{
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;
    public static final String SPGAME = "Singleplayer";
	public static final String MPGAME = "Multiplayer";
	
	public static final String EXIT = "Exit";
    
    private final Model model;
    private final Controller controller;
    private final Stage stage;

    public MenuView(Stage stage, Controller controller, Model model) {
    	this.stage = stage;
        this.model = model;
        this.controller = controller;
        
        //Title Label
        String c4 = "Connect4";
        Reflection r = new Reflection();
        r.setFraction(0.7);
        TextFlow title = new TextFlow();
        for (int i = 0; i < c4.length(); i++) {
        	Text temp = new Text();
        	char c = c4.charAt(i);
        	temp.setX(10.0);
        	temp.setY(50.0);
        	temp.setCache(true);
        	temp.setText( Character.toString(c) );
        	
        	if (i % 2 == 0 ) {
        		temp.setFill(Color.BLUE);
        	} 
        	else {
        		temp.setFill(Color.RED);
        	}
        	
        	temp.setFont(Font.font(null, FontWeight.BOLD, 30));
        	temp.setEffect(r);
        	
        	title.getChildren().add(temp);
        }
        
        //Singleplayer button
        Button b1 = new Button(SPGAME);
        b1.setOnAction(e -> 
        {
        	if (e.getSource() == b1){
                    ViewHandler.selectAction(b1.getText());
                    stage.close();
                    e.consume();
                }
        		
        });
        
        //Multiplayer button
		Button b2 = new Button(MPGAME);
		b2.setOnAction(e -> 
        {
        	if (e.getSource() == b2){
                    ViewHandler.selectAction(b2.getText());
                    stage.close();
                    e.consume();
                }
        });
		
		//Settings button
		
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
        vbox.getChildren().addAll(b1, b2, b4);
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
