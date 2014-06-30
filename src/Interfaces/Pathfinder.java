/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Enums.Direction;
import projectpacman.*;

/**
 *
 * @author Bouke
 */
public interface Pathfinder {
    public Tile getExit(Tile tile);
    public Direction reverseDirection();
    public Direction getCurDir();
}
