/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton start;
    
    private Playfield veld;
    
    public MainScreen()
    {
	initComponents();
    }
    
    
    
    private void initComponents()// <editor-fold defaultstate="collapsed" desc="InitComponents">
    {	
	panel = new JPanel();
	veldCont = new JPanel();
	start = new JButton("Starten");
	close = new JButton("Sluiten");
	veld = new Playfield();
	
	this.setSize(475, 620);
	this.setTitle("Pacman game");
	this.setResizable(false);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	
	start.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		startActionPerformed(e);
	    }
	});
	
	close.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		closeActionPerformed(e);
	    }
	});
	
	panel.setPreferredSize(new Dimension(this.getWidth(), 30));
	veldCont.setPreferredSize(new Dimension(this.getWidth()- 20, 550));
	start.setPreferredSize(new Dimension(80, 20));
	close.setPreferredSize(new Dimension(80, 20));
	veld.setPreferredSize(new Dimension(this.getWidth(), 550));
	
	panel.add(start);
	panel.add(close);
	veldCont.add(veld);
	
	this.add(veldCont, BorderLayout.CENTER);
	this.add(panel, BorderLayout.NORTH);
	
	veld.requestFocus();
	
	pack();
    }//</editor-fold>
    
    private void closeActionPerformed(ActionEvent e)
    {
	this.dispose();
    }
    
    private void startActionPerformed(ActionEvent e)
    {	
//	veld = new Playfield();
//	veld.setPreferredSize(new Dimension(this.getWidth(), 550));
//	veldCont.add(veld);
//	veld.requestFocus();
    }
}
