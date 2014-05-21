/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Robin
 */
public class Scherm extends JFrame 
{
    private JPanel panel;
    
    public Scherm()
    {
	initComponents();
    }
    
    private void initComponents()// <editor-fold defaultstate="collapsed" desc="InitComponents">
    {	
	this.panel = new JPanel();
	
	panel.setBackground(Color.red);
	panel.setSize(new Dimension(200, 200));
	panel.add(new Vak(15, 20));
	
	repaint();
    }//</editor-fold>
}
