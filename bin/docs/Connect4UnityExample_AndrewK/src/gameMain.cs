using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using UnityEngine.Networking;

//The main script for offline games
public class gameMain : NetworkBehaviour
{
    public Material red;
    public Material blue;
    public GameObject piece;
    public Transform[] spawnpoint = new Transform[7];
    public float cooldown;

    //UI elements
    public Text playerText;
    public Text victoryText;
    public GameObject endPanel;
    public GameObject mainScreen;

    private int[] counter = new int[7] { 0, 0, 0, 0, 0, 0, 0 };

    //grid will map placements with a buffer for the search algorithms - see CmdButtonSpawn function
    private int[,] grid = new int[20,20];

    private float timer = 2.0f;
    private bool redTurn;
    private bool isRed;
    private bool botEnabled;
    private bool easyBot;
    private bool playerTurn = true;
    private bool multiPlayer = false;
    private string objectToPlace;

    private Renderer pieceMat;
    private bool gameOver = false;
    private staticValues variables;
    private int aiColor;
    private int playerColor;

    void Start()
    {

        ClientScene.RegisterPrefab(piece);

        //Get info from main menu options
        GameObject vars = GameObject.FindGameObjectWithTag("variables");
        if (vars != null)
        {
            //Debug.Log("Found");
            //get the variables class
            variables = vars.GetComponent<staticValues>();

            //store data locally
            botEnabled = variables.BotEnabled();  //is the bot enabled
            isRed = variables.PlayerColor(); //is the player red
            easyBot = variables.GetDiff();  //is the bot easy
            playerTurn = variables.PlayerFirst();  //does the player go first
            //Debug.Log(botEnabled + "<eb  rs>" + redStarts + " diff>" + easyBot);
        }

        if (playerTurn) //if the player is going first
        {
            if (isRed) //and is red
            {
                playerText.text = ("RED");
                playerText.color = (Color.red);
                redTurn = true;
                playerColor = 1;
                aiColor = 2; //2=blue
            }
            else
            {
                playerText.text = ("BLUE");
                playerText.color = (Color.blue);
                redTurn = false;
                playerColor = 2;
                aiColor = 1; 
            }
            
        }
        else   //the bot is going first
        {
            if (isRed)  
            {
                playerText.text = ("BLUE");
                playerText.color = (Color.blue);
                redTurn = false;
                playerColor = 1;
                aiColor = 2; //2=blue
            }
            else
            {
                playerText.text = ("RED");
                playerText.color = (Color.red);
                redTurn = true;
                playerColor = 2;
                aiColor = 1;
            }
        }

        pieceMat = piece.GetComponent<Renderer>();
        int j = 3;
        for (int x = 0; x < 20; x++) //initialize grid with different numbers to prevent algorithm confusion
        {
            for (int y = 0; y < 20; y++)
            {
                grid[x, y] = j;
                j++;
            }
        }
    }

    void Update() //update is called once a frame
    {
        if(timer < cooldown)  //timer ensures a delay between placements
        {
            timer += Time.deltaTime;
        }
        if(timer >= cooldown && botEnabled && !playerTurn && !gameOver)
        {
            //DisplayGrid();
            AI(); //Call AI function here to ensure a delay
        }
    }

    void AI()  //Main AI script, no difficulty setting yet
    {
        int placement = 3;  //column to place piece

        int[] score = new int[7] { 1, 1, 1, 1, 1, 1, 1 };
        //each column will be scored each turn, higher score = better place for the piece
        //max score is 5

        //1st - check for a winning move
        //ai winning move = score 5
        //enemy winning move = score 4
        //check rows - four variations
        for(int i = 0; i < 7; i++) //check each column
        {
            int y = counter[i] + 4;
            //if 3 same to the left
            if(grid[i + 1, y] == grid[i + 2, y] && grid[i + 2, y] == grid[i + 3, y])
            {
                //Debug.Log("a case 1");
                if(grid[i + 1, y] == playerColor)
                {
                    if(score[i] < 4)
                    {
                        score[i] = 4;
                    }
                }
                if(grid[i+1, y] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //if 3 same to the right
            if (grid[i + 5, y] == grid[i + 6, y] && grid[i + 6, y] == grid[i + 7, y])
            {
                //Debug.Log("a case 2");
                if (grid[i + 5, y] == playerColor)
                {

                    if (score[i] < 4)
                    {
                        score[i] = 4;
                    }
                }
                if (grid[i + 5, y] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //if 2 left, 1 right
            if (grid[i + 2, y] == grid[i + 3, y] && grid[i + 3, y] == grid[i + 5, y])
            {
                //Debug.Log("a case 3");
                if (grid[i + 5, y] == playerColor)
                {
                    if (score[i] < 4)
                    {
                        score[i] = 4;
                    }
                }
                if (grid[i + 5, y] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //if 1 left, 2 right
            if (grid[i + 3, y] == grid[i + 5, y] && grid[i + 5, y] == grid[i + 6, y])
            {
                //Debug.Log("a case 4");
                if (grid[i + 5, y] == playerColor)
                {
                    if (score[i] < 4)
                    {
                        score[i] = 4;
                    }
                }
                if (grid[i + 5, y] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //check column - 1 variation
            //if 3 below are the same
            if (grid[i + 4, y - 3] == grid[i + 4, y - 2] && grid[i + 4, y - 2] == grid[i + 4, y - 1])
            {
                //Debug.Log("a case 5");
                if (grid[i + 4, y - 1] == playerColor)
                {
                    score[i] = 4;
                }
                else
                {
                    score[i] = 5;
                }

            }
            
            //check diagonal - 8 variations
            //seek upper left
            if (grid[i + 3, y + 1] == grid[i + 2, y + 2] && grid[i + 2, y + 2] == grid[i + 1, y + 3])
            {
                //Debug.Log("a case 6");
                if (grid[i + 3, y + 1] == playerColor && score[i] < 4)
                {
                    score[i] = 4;
                }
                else if (grid[i + 3, y + 1] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //seek upper right
            if (grid[i + 5, y + 1] == grid[i + 6, y + 2] && grid[i + 6, y + 2] == grid[i + 7, y + 3])
            {
                //Debug.Log("a case 7");
                if (grid[i + 5, y + 1] == playerColor && score[i] < 4)
                {
                    score[i] = 4;
                }
                else if (grid[i + 5, y + 1] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //seek lower left
            if (grid[i + 3, y - 1] == grid[i + 2, y - 2] && grid[i + 2, y - 2] == grid[i + 1, y - 3])
            {
                //Debug.Log("a case 8");
                if (grid[i + 3, y - 1] == playerColor && score[i] < 4)
                {
                    score[i] = 4;
                }
                else if (grid[i + 3, y - 1] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //seek lower right
            if (grid[i + 5, y - 1] == grid[i + 6, y - 2] && grid[i + 6, y - 2] == grid[i + 7, y - 3])
            {
                //Debug.Log("a case 9");
                if (grid[i + 5, y - 1] == playerColor && score[i] < 4)
                {
                    score[i] = 4;
                }
                else if (grid[i + 5, y - 1] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //seek partial right upper
            if (grid[i + 3, y + 1] == grid[i + 2, y + 2] && grid[i + 2, y + 2] == grid[i + 5, y - 1])
            {
                //Debug.Log("a case 10");
                if (grid[i + 3, y + 1] == playerColor && score[i] < 4)
                {
                    score[i] = 4;
                }
                else if (grid[i + 3, y + 1] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //seek partial right lower
            if (grid[i + 3, y + 1] == grid[i + 5, y - 1] && grid[i + 5, y - 1] == grid[i + 6, y - 2])
            {
               // Debug.Log("a case 11");
                if (grid[i + 3, y + 1] == playerColor && score[i] < 4)
                {
                    score[i] = 4;
                }
                else if (grid[i + 3, y + 1] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //seek partial left upper
            if (grid[i + 2, y + 2] == grid[i + 3, y + 1] && grid[i + 3, y + 1] == grid[i + 5, y - 1])
            {
                //Debug.Log("a case 12");
                if (grid[i + 3, y + 1] == playerColor && score[i] < 4)
                {
                    score[i] = 4;
                }
                else if (grid[i + 3, y + 1] == aiColor)
                {
                    score[i] = 5;
                }
            }
            //seek partial left lower
            if (grid[i + 6, y - 2] == grid[i + 3, y + 1] && grid[i + 3, y + 1] == grid[i + 5, y - 1])
            {
                //Debug.Log("a case 13");
                if (grid[i + 3, y + 1] == playerColor && score[i] < 4)
                {
                    score[i] = 4;
                }
                else if (grid[i + 3, y + 1] == aiColor)
                {
                    score[i] = 5;
                }
            }
        }

        //If no winning move is available
        //2nd - put a piece adjacent to another if able 
        //3 in a row = score 3
        for (int i = 4; i < 11; i++) //adjusted for buffer
        {
            int y = counter[i - 4] + 4;
            //check rows - 3 variants
            if (grid[i - 2, y] == grid[i-1, y]) //left
            {
                //Debug.Log("b case 1");
                if(score[i-4] < 3)
                { score[i - 4] = 3; }
            }
            if (grid[i - 1, y] == grid[i + 1, y]) //middle
            {
                //Debug.Log("b case 2");
                if (score[i - 4] < 3)
                { score[i - 4] = 3; }
            }
            if (grid[i + 1, y] == grid[i + 2, y]) //right
            {
               // Debug.Log("b case 3");
                if (score[i - 4] < 3)
                { score[i - 4] = 3; }
            }
            //check column - 1 variant
            if (grid[i, y - 1] == grid[i, y - 2]) //down
            {
                //Debug.Log("b case 4");
                if (score[i - 4] < 3)
                { score[i - 4] = 3; }
            }
            //check diagonal - 6 variants
            if (grid[i+2, y+2] == grid[i+1, y+1]) // upper right
            {
                //Debug.Log("b case 5");
                if (score[i - 4] < 3)
                { score[i - 4] = 3; }
            }
            if (grid[i + 1, y + 1] == grid[i - 1, y - 1]) // mid right
            {

                //Debug.Log("b case 6");
                if (score[i - 4] < 3)
                { score[i - 4] = 3; }

            }
            if (grid[i - 2, y - 2] == grid[i - 1, y - 1]) // lower right
            {
                //Debug.Log("b case 7");
                if (score[i - 4] < 3)
                { score[i - 4] = 3; }
            }
            if (grid[i - 1, y + 1] == grid[i - 2, y + 2]) // upper left
            {
                //Debug.Log("b case 8");
                if (score[i - 4] < 3)
                { score[i - 4] = 3; }
            }
            if (grid[i - 1, y + 1] == grid[i + 1, y - 1]) // mid left
            {
                //Debug.Log("b case 9");
                if (score[i - 4] < 3)
                { score[i - 4] = 3; }
            }
            if (grid[i + 1, y - 1] == grid[i + 2, y - 2]) // lower left
            {
                //Debug.Log("b case 10");
                if (score[i - 4] < 3)
                { score[i - 4] = 3; }
            }
        }
        //otherwise
        //3rd - 2 in a row = score 2
        for (int i = 4; i < 11; i++) //adjusted for buffer
        {
            int y = counter[i - 4] + 4;
            //check rows - 2 variants
            if (grid[i - 1, y] == aiColor) //left
            {
                //Debug.Log("c case 1");
                if (score[i - 4] < 2)
                { score[i - 4] = 2; }
            }
            if (grid[i + 1, y] == aiColor) //right
            {
                //Debug.Log("c case 2");
                if (score[i - 4] < 2)
                { score[i - 4] = 2; }
            }

            //check column - 1 variant
            if (grid[i, y - 1] == aiColor) //down
            {
                //Debug.Log("c case 3");
                if (score[i - 4] < 2)
                { score[i - 4] = 2; }
            }
            //check diagonal - 4 variants
            if (grid[i - 1, y + 1] == aiColor) //upper left
            {
                //Debug.Log("c case 4");
                if (score[i - 4] < 2)
                { score[i - 4] = 2; }
            }
            if (grid[i + 1, y - 1] == aiColor) //lower left
            {
                //Debug.Log("c case 5");
                if (score[i - 4] < 2)
                { score[i - 4] = 2; }
            }
            if (grid[i + 1, y + 1] == aiColor) //upper right
            {
                //Debug.Log("c case 6");
                if (score[i - 4] < 2)
                { score[i - 4] = 2; }
            }
            if (grid[i - 1, y - 1] == aiColor) //lower right
            {
                //Debug.Log("c case 7");
                if (score[i - 4] < 2)
                { score[i - 4] = 2; }
            }

        }

        //else, try to block the player horizontally


        //if column is full, score = 0
        for (int i = 0; i < 7; i++)
        {
            if (counter[i] == 6)
            {
                score[i] = 0;
            }
        }

        //place piece in best placement position
        int max = 0;
        for (int i = 1; i < 7; i++)
        {
            //Debug.Log("Score of " + (i-1) + " is " + score[i-1]);
            if (score[i] > score[max])
            {
                max = i;
            }
        }
        placement = max;  //best placement

        //Spawn the piece and update the game state
        if (redTurn)
        {
            //change font to signal the next player's turn
            playerText.text = ("BLUE");
            playerText.color = (Color.blue);
            //blue's turn is next
            redTurn = false;
            //set piece to current player's color
            pieceMat.material = red;
            //record the placement, with a buffer of 4 to prevent out of bounds searches
            grid[placement + 4, counter[placement] + 4] = 1;
        }
        else
        {
            playerText.text = ("RED");
            playerText.color = (Color.red);
            redTurn = true;
            pieceMat.material = blue;
            grid[placement + 4, counter[placement] + 4] = 2;
        }
        Instantiate(piece, spawnpoint[placement].position, spawnpoint[placement].rotation); //create piece at best column position

        if (checkWin(placement + 4, counter[placement] + 4) == 1) //check for wins
        {
            //Red won
            Debug.Log("Red won - ai");
            GameOver(1); 
        }
        else if (checkWin(placement + 4, counter[placement] + 4) == 2)
        {
            //Blue won
            Debug.Log("Blue won - ai");
            GameOver(2); 
        }

        counter[placement]++; //this column has +1 pieces
        playerTurn = true;  //end ai turn
    }

    public void Restart() //game restart
    {
        gameOver = false;
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }

    public void Quit() //game quit
    {
        Application.Quit();
    }

    void GameOver(int x) //endgame function
    {
        gameOver = true;
        if(x == 1)
        {
            victoryText.text = ("Red Wins!");
        }
        if (x == 2)
        {
            victoryText.text = ("Blue Wins!");
        }
        endPanel.SetActive(true);
        mainScreen.SetActive(false);
    }

    int checkWin(int x, int y)  //check grid for a win condition wherever the new piece was dropped
    {
        int count = 0;  //how many similar pieces are in a row
        //check row
        for (int i = -3; i < 4; i++)
        {
            if(grid[x+i, y] == grid[x + i + 1, y])
            {
                count++; //piece is similar
            }
            else
            {
                count = 0; //piece is not similar
            }
            if(count == 3)
            {
                //Debug.Log("Won by row");
                return grid[x, y];  //return color of winning player
            }
        }
        //check column
        for (int i = -3; i < 4; i++)
        {
            if (grid[x, y + i] == grid[x, y + i + 1])
            {
                count++;
            }
            else
            {
                count = 0;
            }
            if (count == 3)
            {
                //Debug.Log("Won by col");
                return grid[x, y];
            }
        }
        //check diagonal
        for (int i = -3; i < 4; i++)
        {
            if(grid[x + i, y + i] == grid[x + i + 1, y + i + 1]) // check right diagonal
            {
                count++;
            }
            else
            {
                count = 0;
            }
            if (count == 3)
            {
                //Debug.Log("Won by diag");
                return grid[x, y];
            }

        }
        for (int i = -3; i < 4; i++)
        {
            if (grid[x + i, y - i] == grid[x + i + 1, y - i - 1]) // check right diagonal
            {
                count++;
            }
            else
            {
                count = 0;
            }
            if (count == 3)
            {
                //Debug.Log("Won by diag");
                return grid[x, y];
            }

        }
        return 0; //no winner
    }

    public void CmdButtonSpawn(int num) //a player spawns a piece at column num, provided by the button pressed by the user
    {
        //Debug.Log("Spawn");

        if (counter[num] != 6 && timer >= cooldown && !gameOver && playerTurn)  //if column isn't full, no cooldown, game not over and not the AI's turn
        {
            if(redTurn)  //same as AI spawn
            {
                //change font to signal the next player's turn
                playerText.text = ("BLUE");
                playerText.color = (Color.blue);
                //it will be blue's turn
                redTurn = false;
                //set piece to player's color
                pieceMat.material = red;
                //record the placement, with a buffer of 4 to prevent out of bounds searches
                grid[num + 4, counter[num] + 4] = 1;
            }
            else
            {

                playerText.text = ("RED");
                playerText.color = (Color.red);
                redTurn = true;
                pieceMat.material = blue;
                grid[num + 4, counter[num] + 4] = 2;

            }

                Instantiate(piece, spawnpoint[num].position, spawnpoint[num].rotation); //drop piece in selected column

            timer = 0; //reset cooldown
            if (checkWin(num + 4, counter[num] + 4) == 1) //check for win conditions
            {
                //Red won
                Debug.Log("Red won");
                GameOver(1);
            }
            else if (checkWin(num + 4, counter[num] + 4) == 2)
            {
                //Blue won
                Debug.Log("Blue won");
                GameOver(2);
            }
            counter[num]++; //increment column piece count
            if(botEnabled&&playerTurn)
            {
                playerTurn = false; //bot's turn
            }

        }

    }

    void DisplayGrid()
    {
        for (int i = 4; i < 10; i++)
        {
            Debug.Log(grid[4, i] + " " + grid[5, i] + " " + grid[6, i] + " " + grid[7, i] + " " + grid[8, i] + " " + grid[9, i] + " " + grid[10, i]);
        }
    }
}
