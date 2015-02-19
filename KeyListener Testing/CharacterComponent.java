import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class CharacterComponent extends JComponent
{
    private int x = 0;
    private int y = 0;
    
    public CharacterComponent()
    {
        KeyPressListener listener = new KeyPressListener();
        this.addKeyListener(listener);
    }
    
    public void setDirection(char key)
    {
        this.x = 400;
        this.y = 300;
        
        this.repaint();
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        Dot dot = new Dot(this.x, this.y);
        dot.draw(g2);
    }
    
    public class KeyPressListener implements KeyListener
    {
        public void keyTyped(KeyEvent event)
        {
            setDirection(event.getKeyChar());
        }
        
        public void keyPressed(KeyEvent event){}
        public void keyReleased(KeyEvent event){}
    }
}