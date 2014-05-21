/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.*;
import javax.swing.JComponent;

/**
 *
 * @author Robin
 */
public class Vak extends JComponent
{
    private Spelelement figuur;
    private int xPos;
    private int yPos;
    
    public Vak(int xpos, int ypos)
    {
	this.xPos = xpos;
	this.yPos = ypos;
    }
    
    @Override
    protected void paintComponent(Graphics g) 
    {	
	g.setColor(new Color(255, 0, 52));
	g.fillRect(xPos, yPos, 50, 50);
    }
}
