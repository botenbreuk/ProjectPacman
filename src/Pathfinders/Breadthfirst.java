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

    
    @Override
    public Tile getExit(Tile startingTile){
        Queue<Tile> queue = new LinkedList<>();
        LinkedList<Tile> visited = new LinkedList<>();
        HashMap<Tile, Tile> parents = new HashMap<>();
        
        queue.add(startingTile);
        boolean finished = false;
        
        while(!queue.isEmpty() && !finished){
            Tile currentTile = queue.remove();
            visited.add(currentTile);
            LinkedList<Tile> exits = getExits(currentTile, visited);
            
            while(!exits.isEmpty()){
                Tile exit = exits.removeFirst();
                parents.put(exit, currentTile);
                
                if(!(exit.getGameObject() instanceof Pacman)){
                    queue.add(exit);
                }else{
                    return getPath(exit, parents, startingTile).getFirst();
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
        if(canMoveTo(north) && !visited.contains(north))
            exits.add(north); 
        if(canMoveTo(south) && !visited.contains(south))
            exits.add(south);
        if(canMoveTo(east) && !visited.contains(east))
            exits.add(east);
        if(canMoveTo(west) && !visited.contains(west))
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
}
