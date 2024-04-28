import java.awt.*;

//This interface provides us with four key methods. Each of these methods serve as a means to draw our shapes and allow them to move through the Horizontal Axis.
public interface DrawingObject {
    public void draw(Graphics2D g2d);

    public void adjustX(double distance);

    public void animateX(double multiplier);

    public double getX();
}