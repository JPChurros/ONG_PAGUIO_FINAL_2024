import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class AmmoBox {
    private int xPos, yPos, width, height;
    private boolean isFalling, jumpStatus;
    //charType 0 is cat, 1 is hedgehog, 2 is squirrel
    public AmmoBox(int x, int y, int w, int h) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }

    public void draw(Graphics2D g) {
        Rectangle2D.Double temp = new Rectangle2D.Double(xPos, yPos, width, height);
        g.setColor(Color.ORANGE); 
        g.fill(temp);
    }

    public void drop(){
        yPos += 1;
    }

    public void setYPos(int newPos){
        yPos = newPos;
    }

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

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
