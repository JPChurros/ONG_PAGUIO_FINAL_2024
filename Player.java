
//This class contains the code that manages the player's appearance and functionality.
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Player {
    private Image playerSpriteLeft, playerSpriteRight, usedImageRN;
    private int xSpeed, ySpeed, xPos, yPos, width, height, charType, shootDelay, totalAmmo, rightIsTrue;
    private boolean isFalling, jumpStatus;
    //charType 0 is cat, 1 is hedgehog, 2 is squirrel
    public Player(int x, int y, int w, int h, int xs, int ys, int charTypein) {
        xSpeed = xs;
        ySpeed = ys;
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        charType = charTypein;
        isFalling = true;
        jumpStatus = false;
        rightIsTrue = 0;
        if(charType == 0){
            playerSpriteLeft = new ImageIcon("catpistol_LEFT.png").getImage();
            playerSpriteRight = new ImageIcon("catpistol_RIGHT.png").getImage();
            usedImageRN = playerSpriteLeft;
            width = 42;
            height = 59;
            shootDelay = 15;
            totalAmmo = 3;
        }
        else if(charType == 1){
            playerSpriteLeft = new ImageIcon("hedgehog_LEFT.png").getImage();
            playerSpriteRight = new ImageIcon("hedgehog_RIGHT.png").getImage();
            usedImageRN = playerSpriteLeft;
            width = 52;
            height = 55;
            shootDelay = 60;
            totalAmmo = 2;
        }
        else if(charType == 2){
            playerSpriteLeft = new ImageIcon("squirrel_LEFT.png").getImage();
            playerSpriteRight = new ImageIcon("squirrel_RIGHT.png").getImage();
            usedImageRN = playerSpriteLeft;
            width = 51;
            height = 53;
            shootDelay = 5;
            totalAmmo = 7;
        }
    }

    public void move() {
        this.setXPos(this.xPos += xSpeed);
        this.setYPos(this.yPos += ySpeed);
        // System.out.println(this.getXPos());
        // System.out.println(this.getYPos());
    }

    public void draw(Graphics2D g) {
        Rectangle2D.Double temp = new Rectangle2D.Double(xPos, yPos, width, height);
        g.setColor(new Color(0, 0, 0, 0));
        g.fill(temp);
        g.drawImage(usedImageRN, xPos, yPos, width, height, null);
    }

    public void lookRight(){
        usedImageRN = playerSpriteRight;
        rightIsTrue = 1;
    }

    public void lookLeft(){
        usedImageRN = playerSpriteLeft;
        rightIsTrue = 0;
    }

    public void changeXSpeed(int x) {
        this.xSpeed = x;
    }

    public void changeYSpeed(int y) {
        this.ySpeed = y;
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
    public void setFalling(boolean a){
        isFalling = a;
    }
    public boolean isFalling(){
        return this.isFalling;
    }
    public void setJumpStatus(boolean b){
        jumpStatus = b;
    }
    public boolean isJumping(){
        return jumpStatus;
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
    public int getCharType(){
        return charType;
    }
    public int getLookRight(){
        return rightIsTrue;
    }

    public void setLookRight(int temp){
        rightIsTrue = temp;
    }

    public int getShootDelay(){
        return shootDelay;
    }
    
    public int getTotalAmmo(){
        return totalAmmo;
    }

    public boolean isColliding(Platform other) {
        if (xPos + this.width < other.getXPos() ||
                xPos >= other.getXPos() + other.getWidth() ||
                yPos + this.height <= other.getYPos() ||
                yPos >= other.getYPos() + other.getHeight()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isCollidingAmmo(AmmoBox other) {
        if (xPos + this.width < other.getXPos() ||
                xPos >= other.getXPos() + other.getWidth() ||
                yPos + this.height <= other.getYPos() ||
                yPos >= other.getYPos() + other.getHeight()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isCollidingBullet(Bullet other) {
        if (xPos + this.width < other.getXPos() ||
                xPos >= other.getXPos() + other.getWidth() ||
                yPos + this.height <= other.getYPos() ||
                yPos >= other.getYPos() + other.getHeight()) {
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
