
/**
 * @author Charles Matthew L. Ong (234579)
 * @author Gabriel Syd O. Paguio (234725)
 * @version May 11, 2024
 * 
 *          I have not discussed the Java language code in my program
 *          with anyone other than my instructor or the teaching assistants
 *          assigned to this course.
 * 
 *          I have not used Java language code obtained from another student,
 *          or any other unauthorized source, either modified or unmodified.
 * 
 *          If any Java language code or documentation used in my program
 *          was obtained from another source, such as a textbook or website,
 *          that has been clearly noted with a proper citation in the comments
 *          of my program.
 **/

import java.awt.*;

import javax.swing.ImageIcon;

//when calling class bullet, always make initial values x1 and y1 = -1000 then x2 and y2 = -1001

//This class is in charge of handling the bullet, and its movement. We have three types of bullets that change depending on the type of character the player is using.
public class Bullet {
    private Image projectileSprite;
    int x1, y1, x2, y2, xSpeed, ySpeed, charType, bulletLife, constantSpeed, boxX, boxY, width, height, boxHeight,
            boxWidth;
    float xVariable, yVariable;
    Color color;

    // Constructor initializes the values
    public Bullet(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed, Color color, int charType) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = color;
        this.charType = charType;
        constantSpeed = 300;
        bulletLife = 0;
        boxX = -1000;
        boxY = -1000;
        boxHeight = 10;
        boxWidth = 10;
        if (this.charType == 0) {
            projectileSprite = new ImageIcon("hairball_CATPROJ.png").getImage();
            width = 5;
            height = 5;
        } else if (this.charType == 1) {
            projectileSprite = new ImageIcon("spike_HEDGEHOGPROJ.png").getImage();
            width = 6;
            height = 5;
        } else if (this.charType == 2) {
            projectileSprite = new ImageIcon("acorn_SQUIRRELPROJ.png").getImage();
            width = 7;
            height = 5;
        }
    }

    // This method is responsible for drawing the bullet.
    public void draw(Graphics2D g2d) {
        g2d.drawImage(projectileSprite, boxX, boxY, width * 2, height * 2, null);
    }

    // This method changes the angle of the bullet as determined by xVar and yVar
    public void setVariables(float xVar, float yVar) {
        this.xVariable = xVar;
        this.yVariable = yVar;
    }

    // Accessor method returns the left x position.
    public int getX() {
        return x1;
    }

    // Accessor method that returns the top y position
    public int getY() {
        return y1;
    }

    // Mutator method to move the bullet.
    public void shootMove() {
        this.setXPos(this.x1 += xVariable * 18, this.x2 += xVariable * 18);
        this.setYPos(this.y1 += yVariable * 18, this.y2 += yVariable * 18);
    }

    // This method Changes the X Position of the Bullet
    public void setXPos(int tempVal, int tempVal2) {
        this.x1 = tempVal;
        this.x2 = tempVal2;
    }
    // This method Changes the Y Position of the Bullet

    public void setYPos(int tempVal, int tempVal2) {
        this.y1 = tempVal;
        this.y2 = tempVal2;
    }

    // Accessor Method returns the time of the bullet.
    public int getBulletLife() {
        return bulletLife;
    }

    // Accessor method that increases the live time of the bullet.
    public void increaseBulletLife() {
        bulletLife += 1;
    }

    // Accessor method that resets the live time of the bullet.
    public void resetBulletLife() {
        bulletLife = 0;
    }

    // Mutator method returns the X Position of the bullet's hitbox.
    public int getXPos() {
        return boxX;
    }

    // Mutator method that returns the Y Position of the bullet's hitbox.
    public int getYPos() {
        return boxY;
    }

    // Accessor method returns the width of the hitbox.
    public int getWidth() {
        return boxWidth;
    }

    // Accessor method returns the height of the hitbox.
    public int getHeight() {
        return boxHeight;
    }

    //Mutator method that changes character projectile type;
    public void setChar(int temp){
        charType = temp;
        if (this.charType == 0) {
            projectileSprite = new ImageIcon("hairball_CATPROJ.png").getImage();
            width = 5;
            height = 5;
        } else if (this.charType == 1) {
            projectileSprite = new ImageIcon("spike_HEDGEHOGPROJ.png").getImage();
            width = 6;
            height = 5;
        } else if (this.charType == 2) {
            projectileSprite = new ImageIcon("acorn_SQUIRRELPROJ.png").getImage();
            width = 7;
            height = 5;
        }
    }

    // This method is responsible for checking if the bullet is colliding with a
    // platform.
    public boolean isCollidingBullet(Platform other) {

        boxX = Math.min(x1, x2);
        boxY = Math.min(y1, y2);
        boxWidth = Math.abs(x2 - x1);
        boxHeight = Math.abs(y2 - y1);

        if (boxX + boxWidth < other.getXPos() ||
                boxX >= other.getXPos() + other.getWidth() ||
                boxY + boxHeight <= other.getYPos() ||
                boxY >= other.getYPos() + other.getHeight()) {
            return false;
        } else {
            return true;
        }
    }

    // This method is responsible for checking if the bullet is colliding with a
    // player
    public boolean isCollidingPlayer(Player other) {

        boxX = Math.min(x1, x2);
        boxY = Math.min(y1, y2);
        boxWidth = Math.abs(x2 - x1);
        boxHeight = Math.abs(y2 - y1);

        if (boxX + boxWidth < other.getXPos() ||
                boxX >= other.getXPos() + other.getWidth() ||
                boxY + boxHeight <= other.getYPos() ||
                boxY >= other.getYPos() + other.getHeight()) {
            return false;
        } else {
            return true;
        }
    }
}
