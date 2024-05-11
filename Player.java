
/**
	@author Charles Matthew L. Ong (234579)
    @author Gabriel Syd O. Paguio (234725)
	@version May 11, 2024
	
	I have not discussed the Java language code in my program 
	with anyone other than my instructor or the teaching assistants 
	assigned to this course.

	I have not used Java language code obtained from another student, 
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program 
	was obtained from another source, such as a textbook or website, 
	that has been clearly noted with a proper citation in the comments 
	of my program.
**/

import java.awt.*;
import javax.swing.*;

//This class contains the code that draws the player. It also contains the methods that allow the player to check collission and move.
public class Player {
    private Image playerSpriteLeft, playerSpriteRight, usedImageRN;
    private int xSpeed, ySpeed, xPos, yPos, width, height, charType, shootDelay, totalAmmo, rightIsTrue, currentHP;
    private boolean isFalling, jumpStatus;

    // Constructor initializes the player values.
    // charType 0 is cat, 1 is hedgehog, 2 is squirrel
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
        currentHP = 3;
        if (charType == 0) {
            playerSpriteLeft = new ImageIcon("catpistol_LEFT.png").getImage();
            playerSpriteRight = new ImageIcon("catpistol_RIGHT.png").getImage();
            usedImageRN = playerSpriteLeft;
            width = 42;
            height = 59;
            shootDelay = 15;
            totalAmmo = 3;
        } else if (charType == 1) {
            playerSpriteLeft = new ImageIcon("hedgehog_LEFT.png").getImage();
            playerSpriteRight = new ImageIcon("hedgehog_RIGHT.png").getImage();
            usedImageRN = playerSpriteLeft;
            width = 52;
            height = 55;
            shootDelay = 60;
            totalAmmo = 2;
        } else if (charType == 2) {
            playerSpriteLeft = new ImageIcon("squirrel_LEFT.png").getImage();
            playerSpriteRight = new ImageIcon("squirrel_RIGHT.png").getImage();
            usedImageRN = playerSpriteLeft;
            width = 51;
            height = 53;
            shootDelay = 5;
            totalAmmo = 7;
        }
    }

    // This function constantly gets called during the timer to move the player
    // depending on their speed.
    public void move() {
        this.setXPos(this.xPos += xSpeed);
        this.setYPos(this.yPos += ySpeed);
        // System.out.println(this.getXPos());
        // System.out.println(this.getYPos());
    }

    // This method draws the player.
    public void draw(Graphics2D g) {
        g.drawImage(usedImageRN, xPos, yPos, width, height, null);
    }

    // Mutator method that changes the playerSprite so that they look right.
    public void lookRight() {
        usedImageRN = playerSpriteRight;
        rightIsTrue = 1;
    }

    // Mutator method that changes the playerSprite so that they look left
    public void lookLeft() {
        usedImageRN = playerSpriteLeft;
        rightIsTrue = 0;
    }

    // Mutator method that changes the player's horizontal speed.
    public void changeXSpeed(int x) {
        this.xSpeed = x;
    }

    // Mutator method that changes the player's vertical speed.
    public void changeYSpeed(int y) {
        this.ySpeed = y;
    }

    // Accessor method for the player's x position
    public int getXPos() {
        return this.xPos;
    }

    // Accessor method for the player's y position
    public int getYPos() {
        return this.yPos;
    }

    // Mutator method to change the Player's X position
    public void setXPos(int tempVal) {
        this.xPos = tempVal;
    }

    // Mutator method to change the Player's Y Position
    public void setYPos(int tempVal) {
        this.yPos = tempVal;
    }

    // Mutator method to set the player to fall
    public void setFalling(boolean a) {
        isFalling = a;
    }

    // Accessor method to check if the player is falling
    public boolean isFalling() {
        return this.isFalling;
    }

    // Mutator Method to change whether or not the player is jumping
    public void setJumpStatus(boolean b) {
        jumpStatus = b;
    }

    // Accessor method to check if the player is jumping
    public boolean isJumping() {
        return jumpStatus;
    }

    // Accessor Method that returns the player's width.
    public int getWidth() {
        return width;
    }

    // Accessor Method that returns the player's height.
    public int getHeight() {
        return height;
    }

    // Accessor Method that returns the player's horizontal Speed.
    public int getXSpeed() {
        return xSpeed;
    }

    // Accessor Method that returns the player's Vertical Speed.
    public int getYSpeed() {
        return ySpeed;
    }

    // Accessor Method that returns the player's Character Type
    public int getCharType() {
        return charType;
    }

    // Accessor Method that returns the direction the character is facing.
    public int getLookRight() {
        return rightIsTrue;
    }

    // Mutator Method that sets the player to look left or right.
    public void setLookRight(int temp) {
        rightIsTrue = temp;
    }

    // Accessor Method that returns the player's shoot delay.
    public int getShootDelay() {
        return shootDelay;
    }

    // Accessor Method that returns the player's total ammo.
    public int getTotalAmmo() {
        return totalAmmo;
    }

    // Accessor Method that returns the player's current Health.
    public int currentHP() {
        return currentHP;
    }

    // Mutator Method that subtracts a healthpoint. Typically called once the player
    // gets hit.
    public void minusHP() {
        currentHP -= 1;
    }

    //Mutator Method that changes the character type
    //done in initializing w/ server
    public void setCharType(int cType){
        charType = cType;
        if (charType == 0) {
            playerSpriteLeft = new ImageIcon("catpistol_LEFT.png").getImage();
            playerSpriteRight = new ImageIcon("catpistol_RIGHT.png").getImage();
            usedImageRN = playerSpriteLeft;
            width = 42;
            height = 59;
            shootDelay = 15;
            totalAmmo = 3;
        } else if (charType == 1) {
            playerSpriteLeft = new ImageIcon("hedgehog_LEFT.png").getImage();
            playerSpriteRight = new ImageIcon("hedgehog_RIGHT.png").getImage();
            usedImageRN = playerSpriteLeft;
            width = 52;
            height = 55;
            shootDelay = 60;
            totalAmmo = 2;
        } else if (charType == 2) {
            playerSpriteLeft = new ImageIcon("squirrel_LEFT.png").getImage();
            playerSpriteRight = new ImageIcon("squirrel_RIGHT.png").getImage();
            usedImageRN = playerSpriteLeft;
            width = 51;
            height = 53;
            shootDelay = 5;
            totalAmmo = 7;
        }
    }

    // Accessor Method that returns false if the player is not colliding with a
    // platform, returns true if it is.
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

    // Accessor method that checks if the player is colliding with the ammobox.
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

    // Method that checks which side of the platform got hit so that we can adjust
    // the player's position accordingly.
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
