/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import projectpacman.Direction;
import projectpacman.GamePanel;
import projectpacman.Tile;
/**
 *
 * @author Robin
 */
public class Pacman extends MovingObject implements KeyListener
{
    //private long lastPressProcessed = 0;
    private Direction direction = Direction.WEST;
    private Direction preferredDirection = Direction.WEST;
    
    public Pacman(GamePanel playfield, Tile startingTile)
    {
        super.gamePanel = playfield;
        Timer timer = new Timer();
	timer.schedule(new TimerTask() {
            @Override
            public void run(){
                setDirection();
                move(direction);
            }
        }, 0, 250);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                preferredDirection = Direction.NORTH;
                break;
            case KeyEvent.VK_RIGHT:
                preferredDirection = Direction.EAST;
                break;
            case KeyEvent.VK_DOWN:
                preferredDirection = Direction.SOUTH;
                break;
            case KeyEvent.VK_LEFT:
                preferredDirection = Direction.WEST;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { 
	//lastPressProcessed = 0;
    }
    
    @Override
    protected void move(Direction d)
    {
	Tile tile = super.getTile().getNeigbour(d);
        if(tile != null){
           GameObject object = tile.getGameObject();
            //System.out.println(object);
            if(object instanceof Wall == false)
            {
                eat(object, tile);
                tile.addGameObject(this);
                super.setTile(tile);
                super.gamePanel.paintComponent();
            } 
        }
    }
    
    private void setDirection(){
	Tile tile = super.getTile().getNeigbour(preferredDirection);
        if(canMoveTo(tile)) direction = preferredDirection;
    }
    
    private boolean canMoveTo(Tile tile) {
        return tile != null && tile.getGameObject() instanceof Wall == false;
    }
    
    private void eat(Object object, Tile tile){
        if(object instanceof Dot) 
        {
            if (object instanceof SuperDot){
                SuperDot superDot = (SuperDot) object;
                super.gamePanel.addScore(superDot.getScoreValue());
                tile.removeGameObject();
                return;
            }
            Dot dot = (Dot) object;
            super.gamePanel.addScore(dot.getScoreValue());
            tile.removeGameObject();
        }
    }

    @Override
    public void draw(Graphics g) {
	Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
	
	g.setColor(Color.YELLOW);
	g.fillOval(x, y, tile.getWidth(), tile.getHeight());
    }
}
