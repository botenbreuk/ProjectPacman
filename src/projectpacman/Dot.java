/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Robin
 */
public class Dot extends GameObject
{

    @Override
    public void draw(Graphics g) {
	Tile tile = super.getTile();
	int x = (int) (tile.getWidth() * tile.getXPos() + 10);
	int y = (int) (tile.getHeight() * tile.getYPos() + 10);
	
	g.setColor(Color.PINK);
	g.drawOval(x, y, tile.getWidth() / 4, tile.getHeight() / 4);
    }
    
}
