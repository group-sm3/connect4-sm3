package model;


public class Game extends Model {

    private boolean redTurn;
    private boolean isEasy;
    private boolean playerRed;

    //Constructor for Singleplayer Game
    public Game(String difficulty, String color) {
        if (difficulty == "Easy")
            isEasy = true;
        else
            isEasy = false;

        if (color == "Red")
            playerRed = true;
        else
            playerRed = false;

        startSPGame(); //TODO notify the View to change stage

    }

    private void startSPGame() {
        ////ModelEvent me = new ModelEvent(this, 1, "");
        ///notifyChanged(me);
    }
}