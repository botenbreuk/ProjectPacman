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
public class Wall extends GameObject
{
    public Wall() { }

    @Override
    public void draw(Graphics g) {
	Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
	
	g.setColor(new Color(67, 95, 222));
        //Random random = new Random();
	//g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	g.fillRect(x, y, tile.getWidth() - 3, tile.getHeight() - 3);
    }
}
