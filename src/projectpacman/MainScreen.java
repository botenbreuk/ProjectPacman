/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Robin
 */
public class MainScreen extends JFrame 
{
    private JPanel panel;
    private JPanel veldCont;
    private JButton close;
    private JButton restart;
    
    private Playfield playfield;
    
    public MainScreen()
    {
	initComponents();
    }
    
    private void initComponents()// <editor-fold defaultstate="collapsed" desc="InitComponents">
    {	
	panel = new JPanel();
	veldCont = new JPanel();
	restart = new JButton("Herstarten");
	close = new JButton("Sluiten");
	playfield = new Playfield();
        
	this.setSize(480, 620);
	this.setTitle("Pacman game");
	this.setResizable(false);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	
	restart.addActionListener(this::restartActionPerformed);
	close.addActionListener(this::closeActionPerformed);
	
	panel.setPreferredSize(new Dimension(this.getWidth(), 30));
	veldCont.setPreferredSize(new Dimension(this.getWidth()- 20, 560));
	restart.setPreferredSize(new Dimension(110, 20));
	close.setPreferredSize(new Dimension(80, 20));
	playfield.setPreferredSize(new Dimension(this.getWidth(), 550));
	
	panel.add(restart);
	panel.add(close);
	veldCont.add(playfield);
	
	this.add(veldCont, BorderLayout.CENTER);
	this.add(panel, BorderLayout.SOUTH);
	
	playfield.requestFocus();
	
	pack();
    }//</editor-fold>
    
    private void closeActionPerformed(ActionEvent e)
    {
	System.exit(1);
    }
    
    private void restartActionPerformed(ActionEvent e)
    {
        
    }
    
    public Playfield getPlayfield(){
        return playfield;
    }
}
