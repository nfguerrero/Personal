import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Ship
{
    private int x;
    private int y;
    
    public Ship(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics g)
    {
        ImageIcon icon = new ImageIcon("ship.png");
        Image image = icon.getImage();
        
        g.drawImage(image, x, y, null);
    }
    
    public void moveLeft()
    {
        this.x -= 5;
    }
    
    public void moveRight()
    {
        this.x += 5;
    }
}