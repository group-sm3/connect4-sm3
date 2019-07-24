using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class staticValues : MonoBehaviour {

    //class for variables such as user selections
    //most of these functions are called by the start screen buttons
    static public bool isRed = true;
    static public bool enableBot = false;
    static public bool isEasy;
    static public bool playerTurn = true;

    private int x = 0;
    private int y = 0;
    
    void Start()
    {
        DontDestroyOnLoad(this.gameObject); 
    }

    public void Starter() //the player wants to change color
    {
        if (x == 0) //x shows the toggle between red and blue
        {
            Debug.Log("player is blue");
            isRed = false;
            x = 1;
        }
        else
        {
            Debug.Log("player is red");
            isRed = true;
            x = 0;
        }
    }

    public void DisableBot() 
    {
        Debug.Log("bot disabled");
        enableBot = false;
    }

    public void EnableBot()
    {
        Debug.Log("bot enabled");
        enableBot = true;
    }

    public void BotFirst() //Select starting player
    {
        if (y == 0)
        {
            Debug.Log("bot first");
            playerTurn = false;
            y = 1;
        }
        else
        {
            Debug.Log("player first");
            playerTurn = true;
            y = 0;
        }
    }

    public void HardBot()
    {
        Debug.Log("diff = hard");
        isEasy = false;
    }
    public void EasyBot()
    {
        Debug.Log("diff = easy");
        isEasy = true;
    }

    public bool GetDiff()
    {
        return isEasy;
    }

    public bool BotEnabled()
    {
        return enableBot;
    }
    
    public bool PlayerColor()
    {
        return isRed;
    }

    public bool PlayerFirst()
    {
        return playerTurn;
    }
}
