/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import java.awt.Color;
import java.awt.Graphics;
import projectpacman.Tile;

/**
 *
 * @author Robin
 */
public class Dot extends GameObject
{
    private static final int scoreValue = 10;
    
    public int getScoreValue() { return Dot.scoreValue; }
    
    @Override
    public void draw(Graphics g) {
	Tile tile = super.getTile();
	int x = (int) ((tile.getWidth() * tile.getXPos()) + (tile.getWidth() - (tile.getWidth() / 4)) / 2);
	int y = (int) ((tile.getHeight() * tile.getYPos()) + (tile.getHeight() - (tile.getHeight() / 4)) / 2);
	
	g.setColor(Color.WHITE);
	g.drawOval(x, y, tile.getWidth() / 4, tile.getHeight() / 4);
    }
    
}
