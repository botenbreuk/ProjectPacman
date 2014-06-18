/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pathfinders;

import Interfaces.Pathfinder;
import Objects.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import projectpacman.*;

/**
 *
 * @author Bouke
 */
public class Breadthfirst implements Pathfinder{

    private Direction curDir = Direction.NONE;
    boolean checkLastDirection;
    
    @Override
    public Tile getExit(Tile startingTile){
        checkLastDirection = true;
        Queue<Tile> queue = new LinkedList<>();
        LinkedList<Tile> visited = new LinkedList<>();
        HashMap<Tile, Tile> parents = new HashMap<>();
        
        queue.add(startingTile);
        boolean finished = false;
        
        while(!queue.isEmpty() && !finished){
            Tile currentTile = queue.remove();
            visited.add(currentTile);
            LinkedList<Tile> exits = getExits(currentTile, visited);
            checkLastDirection = false;
            
            while(!exits.isEmpty()){
                Tile exit = exits.removeFirst();
                parents.put(exit, currentTile);
                
                if(!(exit.getGameObject() instanceof Pacman)){
                    queue.add(exit);
                }else{
                    Tile nextTile = getPath(exit, parents, startingTile).getFirst();
                    setCurDir(startingTile, nextTile);
                    return nextTile;
                }
            }
        }
        return null;
    }
    
    private LinkedList<Tile> getExits(Tile currentTile, LinkedList<Tile> visited){
        LinkedList<Tile> exits = new LinkedList<>();
        //get all neighbours
        Tile north = currentTile.getNeigbour(Direction.NORTH); 
        Tile south = currentTile.getNeigbour(Direction.SOUTH);
        Tile east = currentTile.getNeigbour(Direction.EAST);
        Tile west = currentTile.getNeigbour(Direction.WEST);
        //add the neighbours you can move towards to the list
        if(canMoveTo(north) && !visited.contains(north) && (curDir != Direction.SOUTH || !checkLastDirection))
            exits.add(north); 
        if(canMoveTo(south) && !visited.contains(south) && (curDir != Direction.NORTH || !checkLastDirection))
            exits.add(south);
        if(canMoveTo(east) && !visited.contains(east) && (curDir != Direction.WEST || !checkLastDirection))
            exits.add(east);
        if(canMoveTo(west) && !visited.contains(west) && (curDir != Direction.EAST || !checkLastDirection))
            exits.add(west);
        
        return exits;
    }
    
    private LinkedList<Tile> getPath(Tile exit, HashMap<Tile, Tile> parents, Tile startingTile){
        Tile currentTile = exit;
        LinkedList<Tile> path = new LinkedList<>();
        
        while(!currentTile.equals(startingTile)){
            path.add(currentTile);
            currentTile = parents.get(currentTile);
        }
        
        Collections.reverse(path);
        
        return path;
    }
    
    private boolean canMoveTo(Tile tile)
    {
        return tile != null && (
               tile.getGameObject() instanceof Wall == false ||
               tile.getGameObject() instanceof GhostWall == true
               );
    }
    
    private void setCurDir(Tile oldTile, Tile newTile){
        if(/*oldTile.getXPos() != newTile.getXPos() && */Math.abs(oldTile.getXPos() - newTile.getXPos()) == 1){
            if(oldTile.getXPos() > newTile.getXPos()){
                curDir = Direction.WEST;
            }else{
                curDir = Direction.EAST;
            }
        }else if(/*oldTile.getYPos() != newTile.getYPos() && */Math.abs(oldTile.getYPos() - newTile.getYPos()) == 1){
            if(oldTile.getYPos() > newTile.getYPos()){
                curDir = Direction.NORTH;
            }else{
                curDir = Direction.SOUTH;
            }
        }else{
            curDir = Direction.NONE;
        }
    }
    
    @Override
    public Direction getCurDir(){
        return curDir;
    }
}