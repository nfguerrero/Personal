import javax.swing.JFrame;

public class CharacterViewer
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        
        frame.setSize(800, 600);
        frame.setTitle("Triangle Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CharacterComponent component = new CharacterComponent();
        frame.add(component);
        
        frame.setVisible(true);
    }
}