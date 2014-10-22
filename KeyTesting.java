import java.awt.event.KeyEvent;

public class KeyTesting
{
    public static void main(String[] args) throws Exception
    {
        System.out.print(" ");
        while (true)
        {
            int keyPressed = KeyEvent.KEY_TYPED;
            if (keyPressed != 400)
            {
                System.out.print(keyPressed);
                String key = "" + keyPressed;
                System.out.print(key);
                if (key.equals("w"))
                {
                    System.out.print("w has been hit");
                }
            }
            
            
            Thread.sleep(33);
        }
    }
}