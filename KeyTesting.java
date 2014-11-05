import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class KeyTesting
{
    public static void main(String[] args) throws Exception
    {
        while (true)
        {
            int key = KeyEvent.KEY_TYPED;
            System.out.print(key);
        }
        Thread.sleep(100);
    }
}