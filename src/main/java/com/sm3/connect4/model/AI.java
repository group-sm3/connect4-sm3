package com.sm3.connect4.model;

public class AI {
private int ROWS;
private int COLUMNS;
private int[][] grid;
private int[]counter;
private int botColor;
private int playerColor;

public AI(int color, int rows, int columns, int grid[][], int counter[]) {
	this.ROWS = rows;
	this.COLUMNS = columns;
	this.botColor = color;
	this.grid = grid;
	this.counter = counter;
	if (botColor == 1) {
		playerColor = 2;
	} else {
		playerColor = 1;
	}
}

public int BotTurn()
{
	int bestCol = 0; //optimal position, to be returned
	int score[] = {2,2,2,2,2,2,2}; //score of each column
	//CHECK FOR THREE IN A ROW
	//check columns
	for (int i = 0; i < 7; i++)
	{
		int y = counter[i]; //height determined by piece count
		try {
			if(grid[i][y-1] == grid[i][y-2] && grid[i][y-2] == grid[i][y-3]) 
			{
				if(grid[i][y-1] == botColor)
				{
					score[i] = 7;
				}
				if(grid[i][y-1] == playerColor)
				{
					score[i] = 6;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
	}
	//check rows
	for(int i = 0; i < 7; i++)
	{
		int y = counter[i];
		try {
		if(grid[i + 1][y] == grid[i +-2][y] && grid[i + 2][y] == grid[i + 3][y])
        {
            //Debug.Log("a case 1");
            if(grid[i + 1][y] == playerColor)
            {
                if(score[i] < 5)
                {
                    score[i] = 6;
                }
            }
            if(grid[i+1][y] == botColor)
            {
                score[i] = 7;
            }
        }
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
        if (grid[i - 1][y] == grid[i - 2][y] && grid[i - 2][y] == grid[i - 3][y])
        {
            //Debug.Log("a case 2");
            if (grid[i - 1][y] == playerColor)
            {

                if (score[i] < 6)
                {
                    score[i] = 6;
                }
            }
            if (grid[i - 1][y] == botColor)
            {
                score[i] = 7;
            }
        }
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
        if (grid[i - 2][y] == grid[i - 1][y] && grid[i - 1][y] == grid[i + 1][y])
        {
            //Debug.Log("a case 3");
            if (grid[i + 1][y] == playerColor)
            {
                if (score[i] < 6)
                {
                    score[i] = 6;
                }
            }
            if (grid[i + 1][y] == botColor)
            {
                score[i] = 7;
            }
        }
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
        if (grid[i - 1][y] == grid[i + 1][y] && grid[i + 1][y] == grid[i + 2][y])
        {
            //Debug.Log("a case 4");
            if (grid[i + 1][y] == playerColor)
            {
                if (score[i] < 6)
                {
                    score[i] = 6;
                }
            }
            if (grid[i + 1][y] == botColor)
            {
                score[i] = 7;
            }
        }
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
	
	//check diagonals
	try {
	if (grid[i - 3][y - 3] == grid[i - 2][y - 2] && grid[i - 2][y - 2] == grid[i - 1][y - 1])
    {
        //Debug.Log("a case 6");
        if (grid[i - 3][y - 3] == botColor)
        {
            score[i] = 7;
        }
        else if (grid[i - 3][y - 3] == playerColor && score[i] < 6)
        {
            score[i] = 6;
        }
    }}
    catch(ArrayIndexOutOfBoundsException exception) {		}
    //seek upper right
	try {
    if (grid[i - 2][y - 2] == grid[i - 1][y - 1] && grid[i - 1][y - 1] == grid[i + 1][y + 1])
    {
        //Debug.Log("a case 7");
        if (grid[i - 2][y - 2] == playerColor && score[i] < 6)
        {
            score[i] = 6;
        }
        else if (grid[i - 2][y - 2] == botColor)
        {
            score[i] = 7;
        }
    }}
    catch(ArrayIndexOutOfBoundsException exception) {		}
    //seek lower left
	try {
    if (grid[i - 1][y - 1] == grid[i + 1][y + 1] && grid[i + 1][y + 1] == grid[i + 2][y + 2])
    {
        //Debug.Log("a case 8");
        if (grid[i - 1][y - 1] == playerColor && score[i] < 6)
        {
            score[i] = 6;
        }
        else if (grid[i - 1][y - 1] == botColor)
        {
            score[i] = 7;
        }
    }}
    catch(ArrayIndexOutOfBoundsException exception) {		}
    //seek lower right
	try {
    if (grid[i + 1][y + 1] == grid[i + 2][y + 2] && grid[i + 2][y + 2] == grid[i + 3][y + 3])
    {
        //Debug.Log("a case 9");
        if (grid[i + 1][y + 1] == playerColor && score[i] < 6)
        {
            score[i] = 6;
        }
        else if (grid[i + 1][y + 1] == botColor)
        {
            score[i] = 7;
        }
    }}
    catch(ArrayIndexOutOfBoundsException exception) {		}
    //seek partial right upper
	try {
    if (grid[i - 3][y + 3] == grid[i - 2][y + 2] && grid[i - 2][y + 2] == grid[i - 1][y + 1])
    {
        //Debug.Log("a case 10");
        if (grid[i - 3][y + 3] == playerColor && score[i] < 6)
        {
            score[i] = 6;
        }
        else if (grid[i - 3][y + 3] == botColor)
        {
            score[i] = 7;
        }
    }}
    catch(ArrayIndexOutOfBoundsException exception) {		}
    //seek partial right lower
	try {
    if (grid[i - 2][y + 2] == grid[i - 1][y + 1] && grid[i - 1][y + 1] == grid[i + 1][y - 1])
    {
       // Debug.Log("a case 11");
        if (grid[i - 2][y + 2] == playerColor && score[i] < 6)
        {
            score[i] = 6;
        }
        else if (grid[i - 2][y + 2] == botColor)
        {
            score[i] = 7;
        }
    }}
    catch(ArrayIndexOutOfBoundsException exception) {		}
    //seek partial left upper
	try {
    if (grid[i - 1][y + 1] == grid[i + 1][y - 1] && grid[i + 1][y - 1] == grid[i + 2][y - 2])
    {
        //Debug.Log("a case 12");
        if (grid[i - 1][y + 1] == playerColor && score[i] < 6)
        {
            score[i] = 6;
        }
        else if (grid[i - 1][y + 1] == botColor)
        {
            score[i] = 7;
        }
    }}
    catch(ArrayIndexOutOfBoundsException exception) {		}
    //seek partial left lower
	try {
    if (grid[i + 1][y - 1] == grid[i + 2][y - 2] && grid[i + 2][y - 2] == grid[i + 3][y - 3])
    {
        //Debug.Log("a case 13");
        if (grid[i + 1][y - 1] == playerColor && score[i] < 6)
        {
            score[i] = 6;
        }
        else if (grid[i + 1][y - 1] == botColor)
        {
            score[i] = 7;
        }
    }}
    catch(ArrayIndexOutOfBoundsException exception) {		}
	}
	//CHECK FOR 2 IN A ROW
	for(int i = 0; i < 7; i++)
	{
		int y = counter[i];
	//by column
		try {
			if(grid[i][y-1] == grid[i][y-2]) 
			{
				if(grid[i][y-1] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i][y-1] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
	//by row
		try {
			if(grid[i- 2][y] == grid[i - 1][y]) 
			{
				if(grid[i - 1][y] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i - 1][y] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
			if(grid[i - 1][y] == grid[i + 1][y]) 
			{
				if(grid[i - 1][y] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i - 1][y] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
			if(grid[i + 1][y] == grid[i + 2][y]) 
			{
				if(grid[i + 1][y] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i + 1][y] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
	//by diagonal
		try {
			if(grid[i - 2][y - 2] == grid[i - 1][y - 1]) 
			{
				if(grid[i - 1][y - 1] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i - 1][y - 1] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
			if(grid[i - 1][y - 1] == grid[i + 1][y + 1]) 
			{
				if(grid[i - 1][y - 1] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i - 1][y - 1] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
			if(grid[i + 1][y + 1] == grid[i + 2][y + 2]) 
			{
				if(grid[i + 1][y + 1] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i + 1][y + 1] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
			if(grid[i - 2][y + 2] == grid[i - 1][y + 1]) 
			{
				if(grid[i - 1][y + 1] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i - 1][y + 1] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
			if(grid[i - 1][y + 1] == grid[i + 1][y - 1]) 
			{
				if(grid[i - 1][y + 1] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i - 1][y + 1] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
			if(grid[i + 1][y - 1] == grid[i + 2][y - 2]) 
			{
				if(grid[i + 1][y - 1] == botColor && score[i] < 5)
				{
					score[i] = 5;
				}
				if(grid[i + 1][y - 1] == playerColor && score[i] < 4)
				{
					score[i] = 4;
				}
			}
		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
	}
	//Else, place piece next to another
	for(int i = 0; i < 7; i++)
	{
		int y = counter[i];
		try {
		if (grid[i - 1][y] != 0) //left
        {
            //Debug.Log("c case 1");
            if (score[i] < 3)
            { score[i] = 3; }
        }		}
	    catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
		if (grid[i + 1][y] != 0) //left
        {
            //Debug.Log("c case 1");
            if (score[i] < 3)
            { score[i] = 3; }
        }		}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
		if (grid[i][y + 1] != 0) //left
        {
            //Debug.Log("c case 1");
            if (score[i] < 3)
            { score[i] = 3; }
        }		}
    	catch(ArrayIndexOutOfBoundsException exception) {		}
	}
	
	for (int i = 0; i < 7; i++) //check for full column
	{
		if(counter[i] < 0)
		{
			score[i] = 0; //cannot place here
		}
	}
	//Check if placement would set up player for a winning move
	for (int i = 0; i < 7; i++)
	{
		int y = counter[i] - 1;
		//row
		try {
        if(grid[i-3][y] == grid[i-2][y] && grid[i-2][y] == grid[i-1][y] && grid[i-1][y] == playerColor)
        {
            if (score[i] < 7)
            {
                score[i] = 1;
            }
        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i-2][y] == grid[i-1][y] && grid[i-1][y] == grid[i+1][y] && grid[i-1][y] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i-1][y] == grid[i+1][y] && grid[i+1][y] == grid[i+2][y] && grid[i-1][y] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i+1][y] == grid[i+2][y] && grid[i+2][y] == grid[i+3][y] && grid[i+1][y] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		//diagonal
		try {
	        if(grid[i-3][y-3] == grid[i-2][y-2] && grid[i-2][y-2] == grid[i-1][y-1] && grid[i-1][y-1] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i-2][y-2] == grid[i-1][y-1] && grid[i-1][y-1] == grid[i+1][y+1] && grid[i+1][y+1] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i-1][y-1] == grid[i+1][y+1] && grid[i+1][y+1] == grid[i+2][y+2] && grid[i+1][y+1] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i+1][y+1] == grid[i+2][y+2] && grid[i+2][y+2] == grid[i+3][y+3] && grid[i+1][y+1] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i-3][y+3] == grid[i-2][y+2] && grid[i-2][y+2] == grid[i-1][y+1] && grid[i-1][y+1] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i-2][y+2] == grid[i-1][y+1] && grid[i-1][y+1] == grid[i+1][y-1] && grid[i+1][y-1] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i-1][y+1] == grid[i+1][y-1] && grid[i+1][y-1] == grid[i+2][y-2] && grid[i+1][y-1] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
		try {
	        if(grid[i+1][y-1] == grid[i+2][y-2] && grid[i+2][y-2] == grid[i+3][y-3] && grid[i+1][y-1] == playerColor)
	        {
	            if (score[i] < 7)
	            {
	                score[i] = 1;
	            }
	        }}
		catch(ArrayIndexOutOfBoundsException exception) {		}
	}

	//if column is full, score = 0
	for (int i = 0; i < 7; i++)
	{
		if (counter[i] == 6)
		{
			score[i] = 0;
		}
	}

	//get highest scoring column
	int max = 0;
	for (int i = 0; i < 7; i++)
	{
		if(score[i] > score[max])
		{
			max = i;
		}
	}

	bestCol = max;
	System.out.println("Scores: " + score[0] + " "+ score[1] + " "+ score[2] + " "+ score[3] + " "+ score[4] + " "+ score[5] + " "+ score[6] + "\n");
	
return bestCol;
}

}
