
//This class contains the code that manages the player's appearance and functionality.
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Player {
    private int xSpeed, ySpeed, xPos, yPos, width, height;

    public Player(int x, int y, int w, int h, int xs, int ys) {
        xSpeed = xs;
        ySpeed = ys;
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }

    public void move() {
        xPos += xSpeed;
        yPos += ySpeed;
    }

    public void draw(Graphics2D g) {
        Rectangle2D.Double temp = new Rectangle2D.Double(xPos, yPos, width, height);
        g.setColor(Color.BLUE); // blue cuz arneow yfm
        g.fill(temp);
    }

    public void changeXSpeed(int x) {
        xSpeed = x;
    }

    public void changeYSpeed(int y) {
        ySpeed = y;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
    
    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public boolean isColliding(Platform other){
        if(
        xPos + this.width < other.getXPos() ||
        xPos >= other.getXPos() + other.getWidth() ||
        yPos + this.height <= other.getYPos() ||
        yPos >= other.getYPos() + other.getHeight()
        ){
            return false;
        }
        else{
            return true;
        }
    }
}

// do we want to make a player class or do we want to make three classes for
// each player type and then have them all implement
// an interface called player
