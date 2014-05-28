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
public class GhostDoor extends Wall
{
    @Override
    public void draw(Graphics g) {
	Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
	
	g.setColor(new Color(255, 255, 255));
	g.fillRect(x, y, tile.getWidth(), tile.getHeight() / 4);
    }
}
