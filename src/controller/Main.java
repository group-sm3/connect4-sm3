package controller; 

import javafx.application.*;
import javafx.stage.*;
import model.Model;
import view.MainMenuView;

public class Main extends Application {
	
    private static Model model;
    private static Controller controller;
    private static Stage window;
    
    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("MVC TEST");

        this.model = new Model();
        this.controller = new Controller(model);
        this.window = primaryStage;
        
        MainMenuView view = new MainMenuView(primaryStage, controller, model);
        
        primaryStage.show();
    }
    
    public static void main(String[] args)
	{
		launch(args);
	}
}