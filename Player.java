
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
        this.setXPos(this.xPos += xSpeed);
        this.setYPos(this.yPos += ySpeed);
        // System.out.println(this.getXPos());
        // System.out.println(this.getYPos());
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
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public void setXPos(int tempVal) {
        this.xPos = tempVal;
    }

    public void setYPos(int tempVal) {
        this.yPos = tempVal;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public boolean isColliding(Platform other) {
        if (xPos + this.width < other.getXPos() ||
                xPos >= other.getXPos() + other.getWidth() ||
                yPos + this.height <= other.getYPos() ||
                yPos >= other.getYPos() + other.getHeight()) {
            // System.out.println(this.getXPos());
            // System.out.println(this.getYPos());
            return false;
        } else {
            return true;
        }
    }

    public int collidingDirection(Platform other) {
        if (yPos + height >= other.getYPos() && yPos + height < other.getYPos() + other.getHeight()
                && yPos < other.getYPos()) {
            // NORTH/TOP OF PLATFORM HIT
            return 1;
        } else if (yPos <= other.getYPos() + other.getHeight() && yPos + height > other.getYPos() + other.getHeight()) {
            // SOUTH/BOTTOM OF PLATFORM HIT ->>>>> FIX THIS ONE
            return 2;
        } else if (xPos + width >= other.getXPos() && xPos < other.getXPos()) {
            // WEST/LEFT OF PLATFORM HIT
            return 3;
        } else if (xPos <= other.getXPos() + other.getWidth() && xPos + width > other.getXPos() + other.getWidth()) {
            // EAST/RIGHT OF PLATFORM HIT
            return 4;
        } else {
            return 0;
        }
    }
}

// do we want to make a player class or do we want to make three classes for
// each player type and then have them all implement
// an interface called player
