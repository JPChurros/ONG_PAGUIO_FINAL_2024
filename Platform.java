
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

import javax.swing.ImageIcon;

//This class is responsible for handling the platform. It is coded in such a way that there are two variations of platforms, a hard platform and a soft platform. 
//Hard platforms are platforms that you collide with in all 4 directions, but soft platforms allow you to jump ontop of them from the bottom.
public class Platform {

    private int x, y, width, height, setSkin;
    private boolean isSoft;
    private Image platformSprite;

    // Initializes the platform
    public Platform(int x, int y, int width, int height, Color color, boolean isSoft, int setSkin) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isSoft = isSoft;
        this.setSkin = setSkin;
        if (setSkin == 0) {
            platformSprite = new ImageIcon("platform0.png").getImage();
        }
        if (setSkin == 1) {
            platformSprite = new ImageIcon("platform1.png").getImage();
        }
        if (setSkin == 2) {
            platformSprite = new ImageIcon("platform2.png").getImage();
        }
    }

    // Draws the Platform
    public void draw(Graphics2D g2d) {
        g2d.drawImage(platformSprite, x, y, width, height, null);
    }

    // Accessor method returns the top left corner of the platform's x value of the
    public int getXPos() {
        return x;
    }

    // Accessor method returns the top left corner of the platform's y value of the
    public int getYPos() {
        return y;
    }

    // Accessor method returns the width of the platform.
    public int getWidth() {
        return width;
    }

    // Accessor mmethod returns the height of the platform.
    public int getHeight() {
        return height;
    }

    // Returns whether or not the platform is soft.
    public boolean isSoft() {
        return isSoft;
    }

    // mutator to update platform x
    public void setX(int temp) {
        x = temp;
    }

    // This is in charge of returning the skin type.
    public int returnSkin() {
        return setSkin;
    }

}
