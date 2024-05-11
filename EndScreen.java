import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class EndScreen {
    private int x, y, picLocX, picLocY, width, height;
    private Image pictureNow;
    private Rectangle2D blackScreen;
    public EndScreen(){
        x = 0;
        y = 0;
        picLocX = 900;
        picLocY = -900;
        width = 1000;
        height = 600;
        blackScreen = new Rectangle2D.Double(-500, -500, 10, 10); 
    }

    public void win(int charType){
        if(charType == 0){
            pictureNow = new ImageIcon("catWIN.gif").getImage();
        }
        if(charType == 1){
            pictureNow = new ImageIcon("hedgehogWIN.gif").getImage();
        }
        if(charType == 2){
            pictureNow = new ImageIcon("squirrelWIN.gif").getImage();
        }
    }

    public void lose(int charType){
        if(charType == 0){
            pictureNow = new ImageIcon("catLOSE.gif").getImage();
        }
        if(charType == 1){
            pictureNow = new ImageIcon("hedgehogLOSE.gif").getImage();
        }
        if(charType == 2){
            pictureNow = new ImageIcon("squirrelLOSE.gif").getImage();
        }
    }

    public void draw(Graphics2D g){
        g.setColor(new Color(0, 0, 0));
        g.fill(blackScreen);
        g.drawImage(pictureNow, picLocX, picLocY, 300, 300, null);
    }

    public void gameEnd(){
        blackScreen = new Rectangle2D.Double(x, y, width, height);
        picLocX = 350;
        picLocY = 150;
    }

}
