/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import projectpacman.Direction;
import Interfaces.GamePanel;
import projectpacman.Tile;

/**
 *
 * @author Robin
 */
public class Ghost extends MovingObject
{
    private Direction curDir = Direction.SOUTH;
    private int imageNumber = 1;
    
    public Ghost(GamePanel playfield, Tile startingTile)
    {
        super(playfield, startingTile);
	Timer timer = new Timer();
	timer.schedule(new TimerTask() {

            private final Random random = new Random();
	    @Override
	    public void run() 
	    {
                LinkedList<Direction> exits = getExits();
                if(exits != null)
		{
                    int randomDirection = random.nextInt(exits.size());
                    move(exits.get(randomDirection));
                }
		else
		{
                    //Omdat een spook niet opeens om mag draaien, 
                    //word de direction gereset bij een doodlopend punt.
                    curDir = null;
                    //Roep daarna run recursief aan.
                    //NOTE: als een spook ingesloten is tussen 4 muren vormt dit een endless loop
                    //run();
                }
	    }
	    
	} , 0, 250);
    }
    
    protected LinkedList<Direction> getExits()
    {
        Tile currentTile = super.getTile();
        LinkedList<Direction> exits = new LinkedList<>();
        //get all neighbours
        Tile north = currentTile.getNeigbour(Direction.NORTH); 
        Tile south = currentTile.getNeigbour(Direction.SOUTH);
        Tile east = currentTile.getNeigbour(Direction.EAST);
        Tile west = currentTile.getNeigbour(Direction.WEST);
        //add the neighbours you can move towards to the list
        if(north != null && canMoveTo(north) && curDir != Direction.SOUTH)
            exits.add(Direction.NORTH); 
        if(south != null && canMoveTo(south) && curDir != Direction.NORTH)
                exits.add(Direction.SOUTH);
        if(east != null && canMoveTo(east) && curDir != Direction.WEST)
            exits.add(Direction.EAST);
        if(west != null && canMoveTo(west) && curDir != Direction.EAST)
            exits.add(Direction.WEST);
        
        if(!exits.isEmpty())
            return exits;
        else
            return null;
    }
    
    private boolean canMoveTo(Tile tile)
    {
        return tile.getGameObject() instanceof Wall == false ||
               tile.getGameObject() instanceof GhostWall == true ||
               tile.getGameObject() instanceof Ghost == true;
    }
    
    @Override
    protected void move(Direction d)
    {
	Tile tile = super.getTile().getNeigbour(d);
        curDir = d;
        
        if(tile.getGameObject() instanceof Pacman){
            //System.out.println("Ik ben op pacman");
            gamePanel.restart();
        }else{
            super.getTile().removeGameObject();
            tile.addGameObject(this);
            super.setTile(tile);
            super.gamePanel.paintComponent();
            nextImage();
        }
    }
    
    private void nextImage()
    {
        imageNumber++;
        if(imageNumber > 4){ imageNumber = 1; }
    }

    @Override
    public void resetPosition() {
        super.getTile().removeGameObject();
        startingTile.addGameObject(this);
        super.setTile(super.startingTile);
    }
    
    @Override
    public void draw(Graphics g) 
    {	
	Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
        
	//g.setColor(Color.RED);
        //g.fillOval(x, y, tile.getWidth(), tile.getHeight());
        try 
	{
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/images/Derpy"+imageNumber+".png"));
            g.drawImage(img, x, y, null);
        }
	catch (IOException ex)
	{
            Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
