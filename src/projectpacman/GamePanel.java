/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Graphics;

/**
 *
 * @author Bouke
 */
public interface GamePanel {
    public void paintComponent();
    public void addScore(int amount);
}
