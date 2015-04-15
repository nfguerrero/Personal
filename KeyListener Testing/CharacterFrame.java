import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

/**                         
   This frame contains a moving rectangle.
*/
public class CharacterFrame extends JFrame
{
   private static final int FRAME_WIDTH = 800;
   private static final int FRAME_HEIGHT = 600;

   private CharacterComponent scene;
   
   class KeyStrokeListener implements KeyListener
   {
      public void keyPressed(KeyEvent event) 
      {
         String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("pressed ", ""); 

         if (key.equals("LEFT"))
         {
            scene.moveLeft();            
         }
         else if (key.equals("RIGHT"))
         {
            scene.moveRight();            
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
   
   public CharacterFrame()
   {
      scene = new CharacterComponent();
      this.add(scene);

      scene.addKeyListener(new KeyStrokeListener());
      this.addWindowListener(new frameWindowListener());
      
      this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      this.setTitle("Character Testing");      
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }
} 
