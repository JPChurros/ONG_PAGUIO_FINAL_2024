//This class contains the code that sets up the Main JFrame for the player. 

//since its just rough lang naman i am basing off the video sir provided! more specifically 
//(Supplement) Video List: Sending Coordinates Between 2 Players Over a Network in Java
// Part 1 - 9:06
// REFER TO 10 MINUTES ONWARDS FOR HOW TO DO GAME CANVAS I BELIEVE.
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private int width, height;
    private Container contentPane;

    public GameFrame(int w, int h) {
        width = w;
        height = h;
    }

    public void setUpGUI() {
        contentPane = this.getContentPane();
        this.setTitle("Wild Whisker Shootout");
        contentPane.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // idk what this does no lie
        this.setVisible(true);

    }
}
