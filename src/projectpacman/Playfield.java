/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

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
	    {1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 4, 1},
	    {1, 4, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 4, 4},
	    {1, 1, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 1, 1},
	    {0, 0, 0, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 0, 0, 0},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 6, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {4, 4, 4, 4, 4, 4, 4, 1, 0, 0, 0, 1, 4, 4, 4, 4, 4, 4, 4},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {0, 0, 0, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 0, 0, 0},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 1, 4, 4, 4, 4, 4, 8, 4, 4, 4, 4, 4, 1, 4, 4, 1},
	    {1, 1, 4, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 4, 1, 1},
	    {1, 4, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1}
	};
    }
    
    public void initLevel()
    {	
	setLayout();
	tiles = new Tile[layout.length][layout[0].length];
	for (int i = 0; i < layout.length; i++) 
	{
	    for (int j = 0; j < layout[0].length; j++) 
	    {	
		switch(layout[i][j])
		{
		    default:
		    case 0:
			Tile empty = new Tile(j, i);
			empty.setGameObject(null);
			tiles[i][j] = empty;
			break;
		    case 1:
			Tile wallTile = new Tile(j, i);
                        Wall wall = new Wall();
                        wall.setTile(wallTile);
			wallTile.setGameObject(wall);
			tiles[i][j] = wallTile;
			break;
		    case 4:
			Tile dotTile = new Tile(j, i);
			Dot dot = new Dot();
			dot.setTile(dotTile);
			dotTile.setGameObject(dot);
			tiles[i][j] = dotTile;
			break;
		    case 6:
			Tile doorTile = new Tile(j, i);
			GhostWall door = new GhostWall();
			door.setTile(doorTile);
			doorTile.setGameObject(door);
			tiles[i][j] = doorTile;
			break;
		    case 8:
			Tile pacTile = new Tile(j, i);
			Pacman pacman = new Pacman();
                        pacman.setTile(pacTile);
			pacTile.setGameObject(pacman);
			this.addKeyListener((KeyListener) pacman);
			tiles[i][j] = pacTile;
			break;
		}
	    }
	}
        setNeighbours();
	
    }
    
    private void setNeighbours()
    {
        for (int i = 0; i < tiles.length; i++) 
        {
            for (int j = 0; j < tiles[0].length; j++) 
            {
                if(i > 0)
                {
                    tiles[i][j].setNeighbour(Direction.NORTH, tiles[i - 1][j]);
                }
                if(i < tiles.length - 1)
                {
                    tiles[i][j].setNeighbour(Direction.SOUTH, tiles[i + 1][j]);
                }
                if(j > 0)
                {
                    tiles[i][j].setNeighbour(Direction.WEST, tiles[i][j - 1]);
                }
                if(j < tiles[0].length - 1)
                {
                    tiles[i][j].setNeighbour(Direction.EAST, tiles[i][j + 1]);
                }
                
                if(i == 0 && tiles[i][j].getGameObject() instanceof Wall == false)
                {
                    tiles[i][j].setNeighbour(Direction.NORTH, tiles[tiles.length - 1][j]);
                }
                if(i == tiles.length - 1 && tiles[i][j].getGameObject() instanceof Wall == false)
                {
                    tiles[i][j].setNeighbour(Direction.SOUTH, tiles[0][j]);
                }
                if(j == 0 && tiles[i][j].getGameObject() instanceof Wall == false)
                {
                    tiles[i][j].setNeighbour(Direction.WEST, tiles[i][tiles[0].length - 1]);
                }
                if(j == tiles[0].length - 1 && tiles[i][j].getGameObject() instanceof Wall == false)
                {
                    tiles[i][j].setNeighbour(Direction.EAST, tiles[i][0]);
                }
            }
        }
    }
    
    @Override
    public void paint(Graphics g) 
    {	
	for (int i = 0; i < tiles.length; i++) 
	{
	    for (int j = 0; j < tiles[0].length; j++) 
	    {
		GameObject gmob = tiles[i][j].getGameObject();
		Tile tile = tiles[i][j];
		
		if(tile != null) tile.draw(g);
		if(gmob != null) gmob.draw(g);
	    }
	}
	repaint();
	this.requestFocus();
    }
}
