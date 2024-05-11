import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class EndScreen {
    private int x, y, picLocX, picLocY, width, height, WLX, WLY, WLWidth, WLHeight;
    private Image pictureNow, winOrLose;
    private Rectangle2D blackScreen;
    public EndScreen(){
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
        winOrLose = new ImageIcon("YOUWIN.png").getImage();
        WLX = 345;
        WLY = 50;
        WLWidth = 310;
        WLHeight = 42;

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
        winOrLose = new ImageIcon("YOULOSE.png").getImage();
        WLX = 321;
        WLY = 50;
        WLWidth = 358;
        WLHeight = 42;
    }

    public void draw(Graphics2D g){
        g.setColor(new Color(0, 0, 0, 99));
        g.fill(blackScreen);
        g.drawImage(pictureNow, picLocX, picLocY, 200, 200, null);
        g.drawImage(winOrLose, WLX, WLY, WLWidth, WLHeight, null);
    }

    public void gameEnd(){
        blackScreen = new Rectangle2D.Double(x, y, width, height);
        picLocX = 400;
        picLocY = 200;
    }

}
