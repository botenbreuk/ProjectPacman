/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author Robin
 */
public class Playfield extends JPanel
{
    private Tile[][] tiles;
    private int[][] layout;
    
    public Playfield()
    {
	this.setVisible(true);
	initLevel();
    }
    
    private void setLayout()
    {
	layout = new int[][]
	{
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
	    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
	    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
	    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	    {1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1},
	    {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
	    {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1},
	    {1, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 1},
	    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
	    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
	    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
	    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
	    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
	    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	    {1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1},
	    {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
	    {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1},
	    {1, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 1},
	    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
	    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
	    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};
    }
    
    private void initLevel()
    {	
	setLayout();
	tiles = new Tile[layout.length][layout[0].length];
	for (int i = 0; i < layout[i].length; i++) 
	{
	    for (int j = 0; j < layout.length; j++) 
	    {	
		switch(layout[j][i])
		{
		    default:
		    case 0:
			Tile empty = new Tile(i, j);
			tiles[j][i] = empty;
			break;
		    case 1:
			Tile wall = new Tile(i, j);
			wall.setGameObject(new Wall());
			tiles[j][i] = wall;
			break;
		    case 8:
			Tile pacman = new Tile(i, j);
			Pacman pac = new Pacman();
			pacman.setGameObject(pac);
			this.addKeyListener((KeyListener) pac);
			tiles[j][i] = pacman;
			break;
		}
	    }
	}	
    }

    @Override
    public void paint(Graphics g) 
    {	
	
	for (int i = 0; i < tiles.length; i++) 
	{
	    for (int j = 0; j < tiles[i].length; j++) 
	    {		
		int x = 25 * i;
		int y = 25 * j;
		
		if(tiles[i][j].getGameObject() == null)
		{
		    g.setColor(Color.BLACK);
		    g.fillRect(x, y, 25, 25);
		}
		else if(tiles[i][j].getGameObject() instanceof Wall)
		{
		    g.setColor(Color.BLUE);
		    g.fillRect(x, y, 25, 25);
		}
		else if(tiles[i][j].getGameObject() instanceof Pacman)
		{
		    g.setColor(Color.YELLOW);
		    g.fillRect(x, y, 25, 25);
		}
	    }
	}
    }
}
