import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class mainDraw extends JComponent {
    
    public int x = 50;
    public int y = 50;
    private mainDot dot = new mainDot(x,y);
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(x, y, 50, 50);
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.BLACK);
        
        //dot = new mainDot(x,y);
        //dot.draw(g2);
    }

    public void moveRight() {
        x = x + 5;
        //dot.moveRight();
        repaint();
    }

    public void moveLeft() {
        x = x - 5;
        repaint();
    }

    public void moveDown() {
        y = y + 5;
        repaint();
    }

    public void moveUp() {
        y = y - 5;
        repaint();
    }
}