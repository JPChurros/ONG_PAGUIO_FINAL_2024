import java.awt.*;
import java.awt.geom.*;

public class Platform {

    private int x, y, width, height;
    private boolean isSoft;
    private Color color;

    public Platform(int x, int y, int width, int height, Color color, boolean isSoft) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.isSoft = isSoft;
    }

    public void draw(Graphics2D g2d) {
        Rectangle2D.Double temp = new Rectangle2D.Double(x, y, width, height);
        g2d.setColor(color);
        g2d.fill(temp);
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isSoft(){
        return isSoft;
    }

}
