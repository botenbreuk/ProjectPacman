/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import Enums.GameState;
import Enums.GhostState;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import Enums.Direction;
import Interfaces.GamePanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import projectpacman.Tile;
/**
 *
 * @author Robin
 */
public class Pacman extends MovingObject implements KeyListener
{
    private Timer timer;
    private Direction direction = Direction.WEST;
    private Direction preferredDirection = Direction.WEST;
    private String currentImagePath = "/images/PacManClosed.png";
    
    public Pacman(GamePanel playfield, Tile startingTile)
    {
        super(playfield, startingTile);
	startTimer();
    }
    
    private void startTimer()
    {
        if(timer != null)
        {
            stopTimer();
        }
        else
        {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run(){
                    setDirection();
                    move(getTile(direction));
                }
            }, 0, 250);
        }
    }
    
    public void stopTimer()
    {
        if(timer != null)
        {
            timer.purge();
            timer.cancel();
            timer = null;
        }
    }
    
    public void pauzeTimer(boolean pauzed)
    {
        if(pauzed)
        {
            stopTimer();
        }
        else
        {
            startTimer();
        }
    }
    
    private Tile getTile(Direction d){
        return super.getTile().getNeigbour(d);
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
        
    }
    
    @Override
    protected void move(Tile t)
    {
        if(t != null){
	    GameObject object = t.getGameObject();
            if(object instanceof Wall == false)
            {
                eat(object, t);
                super.getTile().removeGameObject();
                t.addGameObject(this);
                super.setTile(t);
                super.gamePanel.redraw();
                setImagePath();
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
                super.gamePanel.addScore(((SuperDot)object).getScoreValue());
                energize();
                tile.removeGameObject();
                return;
            }
            super.gamePanel.addScore(((Dot)object).getScoreValue());
            tile.removeGameObject();
        }else if(object instanceof Ghost){
            if(((Ghost)object).getState() == GhostState.SCARED){
                ((Ghost)object).setState(GhostState.EATEN);
            }else if(((Ghost)object).getState() != GhostState.EATEN){
                gamePanel.restartPositions();
            }
        }
    }

    @Override
    public void resetPosition() {
        super.getTile().removeGameObject();
        startingTile.addGameObject(this);
        super.setTile(super.startingTile);
        direction = Direction.WEST;
        currentImagePath = "/images/PacManClosed.png";
    }
    
    private void energize(){
        gamePanel.setGameState(GameState.SUPERPACMAN);
    }
    
    private void setImagePath(){
            if(!currentImagePath.equals("/images/PacManClosed.png")){
                currentImagePath = "/images/PacManClosed.png";
            }else{
                switch(direction){
                    case NORTH:
                        currentImagePath = "/images/PacManUp.png";
                        break;
                    case SOUTH:
                        currentImagePath = "/images/PacManDown.png";
                        break;
                    case EAST:
                        currentImagePath = "/images/PacManRight.png";
                        break;
                    case WEST:
                        currentImagePath = "/images/PacManLeft.png";
                        break;
                }
            }
    }

    @Override
    public void draw(Graphics g) {
	Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
	
	//g.setColor(Color.YELLOW);
	//g.fillOval(x, y, tile.getWidth(), tile.getHeight());
        try 
        {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(currentImagePath));
            g.drawImage(img, x, y, null);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
