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
public class Cherry extends GameObject
{
    private static final int scoreValue = 100;
    private String currentImagePath = "";
    
    public int getScoreValue() { return Cherry.scoreValue; }
    
    @Override
    public void draw(Graphics g) 
    {
	Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
	
        g.setColor(Color.GREEN);
        g.drawOval(x + 5, y + 6, 10, 10);
        g.setColor(Color.RED);
	g.fillOval(x + 1, y + 10, 10, 10);
	g.fillOval(x + 10, y + 10, 10, 10);
//        try 
//        {
//            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(currentImagePath));
//            g.drawImage(img, x, y, null);
//        }
//        catch (IOException ex)
//        {
//            Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
