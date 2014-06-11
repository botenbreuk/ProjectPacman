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
 * @author Bouke
 */
public class SmartGhost extends GameObject{

    @Override
    public void draw(Graphics g) {
        Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
	
	g.setColor(Color.RED);
	g.fillOval(x, y, tile.getWidth(), tile.getHeight());
    }
    
}
