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
	this.setSize(new Dimension(600, 600));
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	
	this.panel = new JPanel();
	
	this.add(panel, BorderLayout.CENTER);
    }//</editor-fold>
}
