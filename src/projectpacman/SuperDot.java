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
public class SuperDot extends Dot
{
    private static final int score = 50;
    
    @Override
    public void draw(Graphics g) {
	Tile tile = super.getTile();
	int x = (int) ((tile.getWidth() * tile.getXPos()) + (tile.getWidth() - (tile.getWidth() / 4)) / 3);
	int y = (int) ((tile.getHeight() * tile.getYPos()) + (tile.getHeight() - (tile.getHeight() / 4)) / 3);
	
	g.setColor(Color.WHITE);
	g.fillOval(x, y, tile.getWidth() / 2, tile.getHeight() / 2);
    }
}
