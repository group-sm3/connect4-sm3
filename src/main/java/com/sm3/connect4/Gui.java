package com.sm3.connect4;

import javafx.application.*;
import javafx.stage.*;
import com.sm3.connect4.controller.Controller;
import com.sm3.connect4.model.Model;
import com.sm3.connect4.view.MenuView;

/**
 * This class is used to link the model, controller, and view of the
 * application. The extends the JavaFX Application class in order to start a new
 * JavaFX stage.
 * 
 */
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

    /**
     * Gets the model.
     * 
     * @return A Model class object to represent the model.
     */
    public static Model getModel() {
        return model;
    }

    /**
     * Gets the controller.
     * 
     * @return A Controller class object to represent the controller.
     */
    public static Controller getController() {
        return controller;
    }

    /**
     * Gets the stage.
     * 
     * @return A Stage class object to represent the window.
     */
    public static Stage getStage() {
        return window;
    }

    public static void main(String[] args) {
        launch(args);
    }
}