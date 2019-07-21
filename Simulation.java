import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Simulation {

    public static void main( String[] args ) {
        Simulator simulator = new Simulator();
        JFrame f = new JFrame( "Test Bouncing Sprites" );
        f.addKeyListener(simulator);
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Image background = null;
        Image sprite = null;
        try {
            background = ImageIO.read( new File( "Resoures/ruckus_field648.bmp" ) );
            sprite = ImageIO.read( new File( "Resoures/Screen Shot 2019-07-10 at 8.07.02 PM.png" ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Renderer bs = new Renderer( background );
        for ( int i = 0; i < 1; i++ ) {
            bs.addSprite( sprite );
        }
        f.getContentPane().add( bs );
        f.pack();
        f.show();
    }
}
