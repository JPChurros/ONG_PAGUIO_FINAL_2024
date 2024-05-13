
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
import java.awt.geom.*;

//This class is in charge of the end screen. This screen is displayed once the game ends, this game screen changes depending on the character type and if they won or lost.
public class EndScreen {
    private int x, y, picLocX, picLocY, width, height, WLX, WLY, WLWidth, WLHeight;
    private Image pictureNow, winOrLose;
    private Rectangle2D blackScreen;

    // Constructor is in charge of initializing values.
    public EndScreen() {
        x = 0;
        y = 0;
        picLocX = 900;
        picLocY = -900;
        width = 1000;
        height = 600;
        WLX = 900;
        WLY = -900;
        WLWidth = 1;
        WLHeight = 1;
        blackScreen = new Rectangle2D.Double(-500, -500, 10, 10);
    }

    // if the player wins, it sets the image to match with the player's
    // corresponding character type.
    public void win(int charType) {
        if (charType == 0) {
            pictureNow = new ImageIcon("catWIN.gif").getImage();
        }
        if (charType == 1) {
            pictureNow = new ImageIcon("hedgehogWIN.gif").getImage();
        }
        if (charType == 2) {
            pictureNow = new ImageIcon("squirrelWIN.gif").getImage();
        }
        winOrLose = new ImageIcon("YOUWIN.png").getImage();
        WLX = 345;
        WLY = 50;
        WLWidth = 310;
        WLHeight = 42;

    }

    // if the player loses, it sets the image to match with the player's
    // corresponding character type.
    public void lose(int charType) {
        if (charType == 0) {
            pictureNow = new ImageIcon("catLOSE.gif").getImage();
        }
        if (charType == 1) {
            pictureNow = new ImageIcon("hedgehogLOSE.gif").getImage();
        }
        if (charType == 2) {
            pictureNow = new ImageIcon("squirrelLOSE.gif").getImage();
        }
        winOrLose = new ImageIcon("YOULOSE.png").getImage();
        WLX = 321;
        WLY = 50;
        WLWidth = 358;
        WLHeight = 42;
    }

    // This method is in charge of drawing the image in the middle of the screen and
    // the background is set to black with an opacity of 99.
    public void draw(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 99));
        g.fill(blackScreen);
        g.drawImage(pictureNow, picLocX, picLocY, 200, 200, null);
        g.drawImage(winOrLose, WLX, WLY, WLWidth, WLHeight, null);
    }

    // This method is in charge of initializing the black screen and setting the
    // picture's location to (400,200)
    public void gameEnd() {
        blackScreen = new Rectangle2D.Double(x, y, width, height);
        picLocX = 400;
        picLocY = 200;
    }

}
