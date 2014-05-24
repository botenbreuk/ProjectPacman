/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Robin
 */
public class Speelveld extends JPanel
{
    private Vak[][] vakken = new Vak[10][10];
    
    public Speelveld()
    {
	this.setVisible(true);
	createVakjes();
    }
    
    private void createVakjes()
    {	
	
	for (int i = 0; i < vakken.length; i++) 
	{
	    for (int j = 0; j < vakken[1].length; j++) 
	    {	
		if((i == 0 || j == 0 || i == (vakken[i].length - 1) || j == (vakken.length - 1)))
		{
		    Vak vak = new Vak();
		    vak.setSpelelement(new Muur());
		    vakken[i][j] = vak;
		}
		else
		{
		    vakken[i][j] = new Vak();
		}
	    }
	}
	
	vakken[2][2].setSpelelement(new Muur());
	vakken[3][2].setSpelelement(new Muur());
	vakken[4][2].setSpelelement(new Muur());
	vakken[6][2].setSpelelement(new Muur());
	vakken[7][2].setSpelelement(new Muur());
	
	vakken[1][4].setSpelelement(new Muur());
	vakken[3][4].setSpelelement(new Muur());
	vakken[4][4].setSpelelement(new Muur());
	vakken[6][4].setSpelelement(new Muur());
	vakken[8][4].setSpelelement(new Muur());
	
	vakken[5][5].setSpelelement(new Pacman());
	
	vakken[2][6].setSpelelement(new Muur());
	vakken[3][6].setSpelelement(new Muur());
	vakken[4][6].setSpelelement(new Muur());
	vakken[6][6].setSpelelement(new Muur());
	vakken[7][6].setSpelelement(new Muur());
	
	vakken[1][6].setSpelelement(new Muur());
	vakken[3][8].setSpelelement(new Muur());
	vakken[6][8].setSpelelement(new Muur());
	vakken[6][7].setSpelelement(new Muur());
    }

    @Override
    public void paint(Graphics g) 
    {	
	
	for (int i = 0; i < vakken.length; i++) 
	{
	    for (int j = 0; j < vakken[i].length; j++) 
	    {		
		int x = 25 * i;
		int y = 25 * j;
		
		if(vakken[i][j].getSpelelement() == null)
		{
		    g.setColor(Color.BLACK);
		    g.fillRect(x, y, 25, 25);
		}
		else if(vakken[i][j].getSpelelement() instanceof Muur)
		{
		    g.setColor(Color.BLUE);
		    g.fillRect(x, y, 25, 25);
		}
		else if(vakken[i][j].getSpelelement() instanceof Pacman)
		{
		    g.setColor(Color.YELLOW);
		    g.fillRect(x, y, 25, 25);
		}
	    }
	}
    }
}
