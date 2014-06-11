/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.TimerTask;
import javax.swing.*;

/**
 *
 * @author Robin
 */
public class MainScreen extends JFrame 
{
    private JPanel infoPanel;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel curTime;
    private JPanel timeCont;
    private JPanel panel;
    private JPanel fieldCont;
    private JButton close;
    private JButton start;
    private JButton restart;
    
    private Playfield playfield;
    private java.util.Timer timer;
    
    public MainScreen()
    {
	// Het creëren van het frame
	initComponents();
	startTime();
    }
    
    private void initComponents()// <editor-fold defaultstate="collapsed" desc="InitComponents">
        {	
	// Aanmaken van de objecten
	infoPanel = new JPanel();
	scoreLabel = new JLabel("Score:");
	timeLabel = new JLabel("Time:");
	curTime = new JLabel("00:00:00");
	timeCont = new JPanel();
	panel = new JPanel();
	fieldCont = new JPanel();
	restart = new JButton("Herstarten");
	start = new JButton("Starten");
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
	close.addActionListener(this::closeActionPerformed);
	
	// Verandering van de default layout van JComponets
	infoPanel.setLayout(new BorderLayout());
	
	// Afemetingen objecten
	infoPanel.setPreferredSize(new Dimension(this.getWidth(), 20));
	timeCont.setPreferredSize(new Dimension(110, 20));
	panel.setPreferredSize(new Dimension(this.getWidth(), 30));
	fieldCont.setPreferredSize(new Dimension(this.getWidth()- 20, 555));
	start.setPreferredSize(new Dimension(90, 20));
	restart.setPreferredSize(new Dimension(110, 20));
	close.setPreferredSize(new Dimension(80, 20));
	playfield.setPreferredSize(new Dimension(this.getWidth(), 550));
	
	// Toevoegen van objecten aan JComponents
	infoPanel.add(scoreLabel, BorderLayout.WEST);
	infoPanel.add(timeCont, BorderLayout.EAST);
	timeCont.add(timeLabel);
	timeCont.add(curTime);
	panel.add(start);
	panel.add(restart);
	panel.add(close);
	fieldCont.add(playfield);
	
	// JComponents toevoegen aan het frame
	this.add(infoPanel, BorderLayout.NORTH);
	this.add(fieldCont, BorderLayout.CENTER);
	this.add(panel, BorderLayout.SOUTH);
	
	// Zett de focus op het speelveld zodat het spel bestuurd kan worden met de pijl toetsen
	playfield.requestFocus();
	
	pack();
    }//</editor-fold>
    
    private void startTime()
    {
	if(timer != null)
	{
	    timer.cancel();
	    timer = null;
	    startTime();
	}
	else
	{
	    timer = new java.util.Timer();
	    timer.schedule(new TimerTask() 
	    {
		private int minutes = 99;
		private int seconds = 57;
		@Override
		public void run() {

		    if(seconds < 59)
		    {
			seconds += 1;
		    }
		    else
		    {
			seconds = 0;
			minutes += 1;
		    }
		    curTime.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
		}
	    }, 0, 1000);
	}
    }
    
    private void closeActionPerformed(ActionEvent e)
    {
	System.exit(1);
    }
    
    private void startActionPerformed(ActionEvent e)
    {
        
    }
    
    private void restartActionPerformed(ActionEvent e)
    {
        
    }
}
