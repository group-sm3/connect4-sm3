using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

//class for all the UI elements, probably not relevant
public class buttonManager : MonoBehaviour
{
    public GameObject startScreen;
    public GameObject playScreen;
    public GameObject optionsScreen;
    public GameObject singleScreen;
    public GameObject multiScreen;

    public GameObject spbutton;
    public Text spbuttonText;
    public Text startingButtonText;

    private Image render;
    private int x = 0;
    private int y = 0;

    void Start()
    {
        render = spbutton.GetComponent<Image>();

    }

    public void play()
    {
        startScreen.SetActive(false);
        playScreen.SetActive(true);
    }

    public void back1()
    {
        //back from play screen
        playScreen.SetActive(false);
        startScreen.SetActive(true);
    }

    public void back2()
    {
        //back from singleScreen
        singleScreen.SetActive(false);
        playScreen.SetActive(true);
    }

    public void back3()
    {
        //back from options screen
        optionsScreen.SetActive(false);
        playScreen.SetActive(true);
    }

    public void back4()
    {
        //back from multiplayer screen
        multiScreen.SetActive(false);
        playScreen.SetActive(true);
    }

    public void singlePlay()
    {
        playScreen.SetActive(false);
        singleScreen.SetActive(true);

    }
    public void multiPlay()
    {
        playScreen.SetActive(false);
        multiScreen.SetActive(true);
    }

    public void options1()
    {
        singleScreen.SetActive(false);
        optionsScreen.SetActive(true);
    }
    public void options2()
    {
        multiScreen.SetActive(false);
        optionsScreen.SetActive(true);
    }

    public void start()
    {
        SceneManager.LoadScene("GameScene");
    }

    public void ChangeStartingPlayer()
    {
        if (y == 0)
        {
            startingButtonText.text = ("BOT");
            y = 1;
        }
        else
        {
            startingButtonText.text = ("PLAYER");
            y = 0;
        }
    }

    public void startPlayerButton()
    {
        if(x == 0)
        {
            render.color = (Color.blue);
            spbuttonText.text = ("BLUE");
            x = 1;
        }
        else
        {
            render.color = (Color.red);
            spbuttonText.text = ("RED");
            x = 0;
        }
    }

}
