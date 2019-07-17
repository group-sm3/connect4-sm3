package controller; 

import javafx.application.*;
import javafx.stage.*;
import model.Model;
import view.MenuView;

public class Main extends Application {
	
    private static Model model;
    private static Controller controller;
    private static Stage window;
    
    public static Controller getController() { return controller; }
    
    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Connect 4 - SM3");

        this.model = new Model();
        this.controller = new Controller(model);
        this.window = primaryStage;
        
        MenuView view = new MenuView(primaryStage, controller, model);
        
        primaryStage.show();
    }
    
    public static void main(String[] args)
	{
		launch(args);
	}
}