/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Graphics;

/**
 *
 * @author Robin
 */
public abstract class GameObject 
{
    private Tile tile;
    
    public Tile getTile() { return this.tile; }
    
    public void setTile(Tile tile) { this.tile = tile; }
    
    public abstract void draw(Graphics g);
    
    @Override
    public String toString()
    {
	String string = "";
	
	if(this instanceof Wall)
	{
	    string = "Ik ben een muur";
	}
	if(this instanceof Dot)
	{
	    string = "Ik ben een punt";
	}
	if(this instanceof Ghost)
	{
	    string = "Ik ben een geest";
	}
	return string;
    }
}
