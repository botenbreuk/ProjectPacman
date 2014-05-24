/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Dimension;
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
	this.setSize(new Dimension(600, 600));
	this.setResizable(false);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	
	Speelveld veld = new Speelveld();
	this.add(veld);
    }//</editor-fold>
    
}
