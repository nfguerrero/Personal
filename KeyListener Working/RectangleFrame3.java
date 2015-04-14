import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

/**                         
   This frame contains a moving rectangle.
*/
public class RectangleFrame3 extends JFrame
{
   private static final int FRAME_WIDTH = 300;
   private static final int FRAME_HEIGHT = 400;

   private RectangleComponent3 scene;

   class MousePressListener implements MouseListener
   {  
      public void mousePressed(MouseEvent event)
      {  
         int x = event.getX();
         int y = event.getY();
         scene.moveRectangleTo(x, y);
      }

      // Do-nothing methods
      public void mouseReleased(MouseEvent event) {}
      public void mouseClicked(MouseEvent event) {}
      public void mouseEntered(MouseEvent event) {}
      public void mouseExited(MouseEvent event) {}
   }

   class KeyStrokeListener implements KeyListener
   {
      public void keyPressed(KeyEvent event) 
      {
         String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("pressed ", ""); 
         if (key.equals("DOWN"))
         {
            scene.moveRectangleBy(0, 1);            
         }
         else if (key.equals("UP"))
         {
            scene.moveRectangleBy(0, -1);            
         }
         else if (key.equals("LEFT"))
         {
            scene.moveRectangleBy(-1, 0);            
         }
         else if (key.equals("RIGHT"))
         {
            scene.moveRectangleBy(1, 0);            
         }
      }
      public void keyTyped(KeyEvent event) {}
      public void keyReleased(KeyEvent event) {}
   }
         
   class frameWindowListener extends WindowAdapter
   {
       public void windowOpened(WindowEvent event)
       {
           scene.requestFocusInWindow();
       }
   }
   
   public RectangleFrame3()
   {
      scene = new RectangleComponent3();
      add(scene);
      
      
      
      // MouseListener listener = new MousePressListener();
      //scene.addMouseListener(listener);

      scene.addKeyListener(new KeyStrokeListener());
      this.addWindowListener(new frameWindowListener());
      

      setSize(FRAME_WIDTH, FRAME_HEIGHT);
      
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      
      scene.requestFocusInWindow();
      
   }
} 

//Nic is the best programmer evar!