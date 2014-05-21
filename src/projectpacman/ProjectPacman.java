/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Dimension;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

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
	frame.setVisible(true);
	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
    }

}
