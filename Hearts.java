
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

//This class is in charge of drawing the players hearts. It draws a new image depending on the amount of health the player has.
public class Hearts {
    public int currentHealth, x, y, width, height;
    public Image imageNow, health1, health2, health3;

    // Construcotr initializes values and initializes the image to one that has 3
    // hearts.
    public Hearts(int x, int y) {
        this.x = x;
        this.y = y;
        width = 39;
        height = 80;
        imageNow = new ImageIcon("3HP.png").getImage();
    }

    // Draws the image to show only one heart.
    public void setHP1() {
        imageNow = new ImageIcon("1HP.png").getImage();
    }

    // Draws the image to show two hearts.
    public void setHP2() {
        imageNow = new ImageIcon("2HP.png").getImage();
    }

    // Draws the image to show three hearts.
    public void setHP3() {
        imageNow = new ImageIcon("3HP.png").getImage();
    }

    // Draws the image to show zero hearts.
    public void setHP0() {
        imageNow = new ImageIcon("0HP.png").getImage();
    }

    // Draws the image
    public void draw(Graphics2D g) {
        g.drawImage(imageNow, this.x, this.y, width, height, null);
    }

    // Accessor method responsible for returning x.
    public int getX() {
        return x;
    }

    // Accessor method responsible for returning y.
    public int getY() {
        return y;
    }

    // Accessor method responsible for returning width.
    public int getWidth() {
        return width;
    }

    // Accessor method responsible for returning height.
    public int getHeight() {
        return height;
    }
}
