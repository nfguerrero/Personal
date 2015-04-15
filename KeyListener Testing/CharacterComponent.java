import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class CharacterComponent extends JComponent
{
    //frame width:  784
    //frame height: 562
    //ship width: 34
    //ship height: 34
    //ship reset coors: (x, y) = (375, 528)
    private int x = 0;
    private int y = 0;
    
    private Ship ship;
    
    public CharacterComponent()
    {
        ship = new Ship(375, 528);
        this.setFocusable(true);
    }
    
    public void paintComponent(Graphics g)
    {
        this.ship.draw(g);
        
        requestFocusInWindow();
    }
    
    public void moveLeft()
    {
        ship.moveLeft();
        this.repaint();
    }
    
    public void moveRight()
    {
        ship.moveRight();
        this.repaint();
    }
}