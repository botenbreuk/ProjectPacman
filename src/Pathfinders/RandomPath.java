/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pathfinders;

import Interfaces.Pathfinder;
import Objects.*;
import java.util.LinkedList;
import java.util.Random;
import projectpacman.*;

/**
 *
 * @author Bouke
 */
public class RandomPath implements Pathfinder{
    
    private Direction curDir;
    
    public RandomPath(){ this.curDir = Direction.NONE; };
    public RandomPath(Direction d){ this.curDir = d; };
    
    @Override
    public Tile getExit(Tile currentTile)
    {
        LinkedList<Tile> exits = new LinkedList<>();
        //get all neighbours
        Tile north = currentTile.getNeigbour(Direction.NORTH); 
        Tile south = currentTile.getNeigbour(Direction.SOUTH);
        Tile east = currentTile.getNeigbour(Direction.EAST);
        Tile west = currentTile.getNeigbour(Direction.WEST);
        //add the neighbours you can move towards to the list
        if(canMoveTo(north) && curDir != Direction.SOUTH)
            exits.add(north); 
        if(canMoveTo(south) && curDir != Direction.NORTH)
            exits.add(south);
        if(canMoveTo(east) && curDir != Direction.WEST)
            exits.add(east);
        if(canMoveTo(west) && curDir != Direction.EAST)
            exits.add(west);
        
        if(!exits.isEmpty()){
            Random random = new Random();
            Tile exit = exits.get(random.nextInt(exits.size()));
            
            if(exit.equals(north)){
                curDir = Direction.NORTH;
            }else if(exit.equals(south)){
                curDir = Direction.SOUTH;
            }else if(exit.equals(east)){
                curDir = Direction.EAST;
            }else if(exit.equals(west)){
                curDir = Direction.WEST;
            }
            
            return exit;
        }else
            curDir = Direction.NONE;
            return null;
    }
    
    private boolean canMoveTo(Tile tile)
    {
        return tile != null && (
               tile.getGameObject() instanceof Wall == false ||
               tile.getGameObject() instanceof GhostWall == true
               );
    }
    
    @Override
    public Direction reverseDirection(){
        switch(curDir){
            case NORTH:
                curDir = Direction.SOUTH;
                break;
            case SOUTH:
                curDir = Direction.NORTH;
                break;
            case EAST:
                curDir = Direction.WEST;
                break;
            case WEST:
                curDir = Direction.EAST;
                break;
        }
        return curDir;
    }
    
    @Override
    public Direction getCurDir(){
        return curDir;
    }
}