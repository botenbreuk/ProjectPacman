/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Enums.GameState;

/**
 *
 * @author Bouke
 */
public interface GamePanel {
    public void redraw();
    public void addScore(int amount);
    public void restartPositions();
    public void setGameState(GameState gameState);
    public GameState getGameState();
    public void removeDot();
}
