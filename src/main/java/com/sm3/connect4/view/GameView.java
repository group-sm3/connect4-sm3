package com.sm3.connect4.view;

import com.sm3.connect4.view.Board;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.sm3.connect4.model.ModelEvent;
import com.sm3.connect4.model.ModelListener;


public class GameView implements Window, ModelListener{
	
	private final String spstart = "SP";
	private final String mpstart = "MP";
	private Stage stage;
	private Board board;
	private TextField gameinfo;
    
    public void displayWindow(String type) {
		stage = new Stage();
		board = new Board();
		if (type == spstart) {
			stage.setScene(new Scene(spmenu()));
			stage.show();
		} 
		else if (type == mpstart) {
			mpmenu();
		}
    	
	}
	
	private Pane spmenu() {
		
		//Setup for difficulty choice row
		ChoiceBox<String> difficultycb = new ChoiceBox<String>(FXCollections.observableArrayList("Easy", "Hard"));
		difficultycb.setValue("Easy");
		HBox hbox1 = new HBox();
		hbox1.setPadding(new Insets(10,5,10,5));
		hbox1.getChildren().addAll(new Text("Difficulty: "), difficultycb);

		//Setup for player color choice row
		ChoiceBox<String> playercolorcb = new ChoiceBox<String>(FXCollections.observableArrayList("Red", "Yellow"));
		playercolorcb.setValue("Red");
		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(10,5,10,5));
		hbox2.getChildren().addAll(new Text("Player Color: "), playercolorcb);

		//Start game button
		Button startbutton = new Button("START");
        startbutton.setOnAction(e -> 
        {
        	if (e.getSource() == startbutton) {
				ViewHandler.spStart(difficultycb.getSelectionModel().getSelectedItem(), playercolorcb.getSelectionModel().getSelectedItem(), this);
				stage.setScene(new Scene(gameView()));
			}
        		
        });

		//combines rows and button
		VBox vbox = new VBox(30);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(hbox1, hbox2, startbutton);

		return vbox;
	}

	private void mpmenu() {
		//TODO
	}


	private Pane gameView() {
		/* REMOVED BACKGROUND IMAGE
		//loads background image
		////ImageView image = new ImageView(new Image(("rsc/background1.jpg")));

		//combines board with background
		StackPane spane = new StackPane();
		//spane.setMargin(board.createBoardPane(), new Insets(60, 160, 40, 160));
		spane.getChildren().addAll(image, board.createBoardPane());
		spane.setMargin(spane.getChildren().get(1), new Insets(60, 160, 40, 160));
		*/

		//creates textfield 
		TextField gameinfo = new TextField();
		gameinfo.setId("gameinfo");
		gameinfo.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
		gameinfo.setEditable(false);
		gameinfo.setPrefHeight(60);
		gameinfo.setText("Game start...");
		this.gameinfo = gameinfo;

		VBox vbox = new VBox(board.createBoardPane(), gameinfo);
    	return vbox;
	}

	// Now implement the necessary event handling code 
	public void modelChanged(ModelEvent event) 
	{
		board.placeDisc(event.getColor(), event.getColumn(), event.getRow());
	}
}
