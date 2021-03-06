/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import java.awt.Graphics;
import projectpacman.Tile;

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
}
