import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Dot
{
    private int x;
    private int y;
    private final int RAD = 2; 
    
    public Dot(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Dot(Point2D.Double point)
    {
        this.x = (int) Math.round(point.getX());
        this.y = (int) Math.round(point.getY());
    }
    
    public void draw(Graphics2D g2)
    {
        Ellipse2D.Double dot = new Ellipse2D.Double(this.x-this.RAD, this.y-this.RAD, this.RAD*2, this.RAD*2);
        
        g2.draw(dot);
        g2.fill(dot);
    }
}