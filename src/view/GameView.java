package view;

import view.Board;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GameView implements Window{
	
	private final String spstart = "SP";
	private final String mpstart = "MP";
	private Stage stage = new Stage();
	private Board board = new Board();
    
    public void displayWindow(String type) {
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
				ViewHandler.spStart(difficultycb.getSelectionModel().getSelectedItem(), playercolorcb.getSelectionModel().getSelectedItem());

				stage.setScene(new Scene(board.createBoardPane()));//TODO //FOR TESTING
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
}
