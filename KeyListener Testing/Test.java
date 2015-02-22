import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Test extends Applet implements KeyListener
{
    private Rectangle rect;
    
    public void init()
    {
        this.addKeyListener(this);
        
        rect = new Rectangle(0, 0, 50, 50);
    }
    
    public void paint(Graphics g)
    {
        setSize(500, 500);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(rect);
    }
    
    @Override
    public void keyPressed(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rect.setLocation(rect.x + 2, rect.y);
        }
        if (event.getKeyCode() == KeyEvent.VK_LEFT)
        {
            rect.setLocation(rect.x - 2, rect.y);
        }
        if (event.getKeyCode() == KeyEvent.VK_UP)
        {
            rect.setLocation(rect.x, rect.y - 2);
        }
        if (event.getKeyCode() == KeyEvent.VK_DOWN)
        {
            rect.setLocation(rect.x, rect.y + 2);
        }
        
        repaint();
    }
    public void keyReleased(KeyEvent event){}
    public void keyTyped(KeyEvent event){}
}