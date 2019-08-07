package com.sm3.connect4; 

import javafx.application.*;
import javafx.stage.*;
import com.sm3.connect4.controller.Controller;
import com.sm3.connect4.model.Model;
import com.sm3.connect4.view.MenuView;



public class Gui extends Application {
    private static Model model;
    private static Controller controller;
    private static Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connect 4 - SM3");

        this.model = new Model();
        this.controller = new Controller(model);
        this.window = primaryStage;
        
        MenuView view = new MenuView(primaryStage, controller, model);
        
        primaryStage.show();
    }

    public static Model getModel() { return model; }
    public static Controller getController() { return controller; }
    public static Stage getStage() { return window; }
    public static void main(String[] args) {
        launch(args);
    }
}