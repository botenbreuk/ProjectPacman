/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import projectpacman.Direction;
import projectpacman.GamePanel;

/**
 *
 * @author Bouke
 */
public abstract class MovingObject extends GameObject{
    protected GamePanel gamePanel;
    protected abstract void move(Direction d);
}
