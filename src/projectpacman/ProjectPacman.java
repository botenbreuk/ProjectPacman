/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Robin
 */
public class ProjectPacman 
{
    public static void main(String[] args) 
    {
	Scherm frame = new Scherm();
	frame.setSize(new Dimension(600, 600));
	frame.setResizable(false);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
    }

}
