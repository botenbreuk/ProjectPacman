/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import Enums.GameState;
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
    private JPanel fieldCont;
    private JButton start;
    private JButton restart;
    private JButton pauze;
    private JButton close;
    
    private Playfield playfield;
    
    public MainScreen()
    {
	// Het creëren van het frame
	initComponents();
    }
    
    private void initComponents()// <editor-fold defaultstate="collapsed" desc="InitComponents">
        {	
	// Aanmaken van de objecten
	panel = new JPanel();
	fieldCont = new JPanel();
	restart = new JButton("Herstarten");
	start = new JButton("Starten");
	pauze = new JButton("Pauzeren");
	close = new JButton("Sluiten");
	playfield = new Playfield();
        
	// Setup JFrame
	this.setSize(475, 620);
	this.setTitle("Pacman game");
	this.setResizable(false);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	
	// Knop acties
	start.addActionListener(this::startActionPerformed);
	restart.addActionListener(this::restartActionPerformed);
	pauze.addActionListener(this::pauzeActionPerformed);
	close.addActionListener(this::closeActionPerformed);
	
	// Verandering van de default layout van JComponets
	
	// Afemetingen objecten
	panel.setPreferredSize(new Dimension(this.getWidth(), 30));
	fieldCont.setPreferredSize(new Dimension(this.getWidth()- 20, 555));
	start.setPreferredSize(new Dimension(90, 20));
	restart.setPreferredSize(new Dimension(100, 20));
	pauze.setPreferredSize(new Dimension(90, 20));
	close.setPreferredSize(new Dimension(80, 20));
	playfield.setPreferredSize(new Dimension(this.getWidth(), 550));
	
	// Toevoegen van objecten aan JComponents
	panel.add(start);
	panel.add(restart);
	panel.add(pauze);
	panel.add(close);
	fieldCont.add(playfield);
	
	// JComponents toevoegen aan het frame
	this.add(fieldCont, BorderLayout.CENTER);
	this.add(panel, BorderLayout.SOUTH);
	
	// Zett de focus op het speelveld zodat het spel bestuurd kan worden met de pijl toetsen
	playfield.requestFocus();
	
	pack();
    }//</editor-fold>
    
    private void closeActionPerformed(ActionEvent e)
    {
	System.exit(1);
    }
    
    private void startActionPerformed(ActionEvent e)
    {
        
    }
    
    private void restartActionPerformed(ActionEvent e)
    {
        playfield.setGameState(GameState.PLAY);
        playfield.restart();
	playfield.requestFocus();
    }
    
    private void pauzeActionPerformed(ActionEvent e)
    {
        GameState state = playfield.getGameState();
        
        if(state == GameState.PAUSE)
        {
            playfield.setGameState(GameState.PLAY);
            playfield.start();
            playfield.requestFocus();
        }
        else
        {
            playfield.setGameState(GameState.PAUSE);
            playfield.pauze();
        }
    }
}
