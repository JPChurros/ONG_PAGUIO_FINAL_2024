import java.awt.*;
import java.awt.geom.*;

public class Platform{

    private int x, y, width, height;
    private boolean isSoft;
    private Color color;

    public Platform(int x, int y, int width, int height, Color color){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics2D g2d){
        Rectangle2D.Double temp = new Rectangle2D.Double(x, y, width, height);
        g2d.setColor(color);
        g2d.fill(temp);
    }

    public void adjustX(double distance){

    }

    public void animateX(double multiplier){
        
    }

    public double getXPos(){
        return this.x;
    }

    public double getYPos(){
        return this.y;
    }
    
    public double getWidth(){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }

}
