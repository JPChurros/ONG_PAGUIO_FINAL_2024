import java.awt.*;
import javax.swing.*;

public class Background {
    private int x, y, width, height;
    private Image backgroundImage;
    public Background(){
        x = 0;
        y = 0;
        width = 1000;
        height = 600;
        backgroundImage = new ImageIcon("background.jpg").getImage();
    }
    public void draw(Graphics2D g){

        g.drawImage(backgroundImage, x, y, width, height, null);
    }
}
