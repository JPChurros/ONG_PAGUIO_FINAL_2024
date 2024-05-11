
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
import javax.swing.*;
import java.awt.geom.*;

//This class is responsible for the Spawning of the AmmoBox. When the player touches the AmmoBox it will provide them with ammo to shoot.
public class AmmoBox {
    private int xPos, yPos, width, height;
    private boolean isFalling, jumpStatus;

    // Constructor initializes the size of the AmmoBox
    public AmmoBox(int x, int y, int w, int h) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }

    // This method is responsible for drawing the ammobox.
    public void draw(Graphics2D g) {
        Rectangle2D.Double temp = new Rectangle2D.Double(xPos, yPos, width, height);
        g.setColor(Color.ORANGE);
        g.fill(temp);
    }

    // This method is responsible for making the ammobox gradually fall until it
    // touches a platform.
    public void drop() {
        yPos += 1;
    }

    // This method is responsible for changing the horizontal position of the ammo
    // box.
    public void setXPos(int newPos) {
        xPos = newPos;
    }

    // This method is responsible for changing the vertical position of the ammo
    // box.
    public void setYPos(int newPos) {
        yPos = newPos;
    }

    // Accessor method that returns the horizontal position of the AmmoBox
    public int getXPos() {
        return this.xPos;
    }

    // Accessor method that returns the vertical position of the AmmoBox
    public int getYPos() {
        return this.yPos;
    }

    // Accessor method that returns the width of the Ammo Box
    public int getWidth() {
        return width;
    }

    // Accessor method that returns the height of the ammo box
    public int getHeight() {
        return height;
    }

    // This method is responsible for checking whether or not the ammo box is
    // colliding with a platform.
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

}
