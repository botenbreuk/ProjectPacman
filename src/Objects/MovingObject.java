/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import Interfaces.GamePanel;
import projectpacman.Tile;

/**
 *
 * @author Bouke
 */
public abstract class MovingObject extends GameObject{
    
    protected Tile startingTile;
    protected GamePanel gamePanel;
    
    public MovingObject(GamePanel playfield, Tile startingTile){
        this.gamePanel = playfield;
        this.startingTile = startingTile;
    }
    
    protected abstract void move(Tile t);
    public abstract void resetPosition();
}
