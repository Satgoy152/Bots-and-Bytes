import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Renderer extends JComponent {

    private Image background;
    private Vector sprites;
    private Runnable repainter;
    private Random random;


    public Renderer() {
        this( null );
    }

    public Renderer(Image background ) {
        this.background = background;
        sprites = new Vector();
        random = new Random();
        repainter = new BounceUpdate();
        Thread t = new Thread( repainter );
        t.start();
    }

    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        if( background != null ) {
            d = new Dimension( background.getWidth( this ), background.getHeight( this ) );
        }
        return( d );
    }

    public void paintComponent( Graphics g ) {
        if ( background != null ) {
            g.drawImage( background, 0, 0, this );
        }
        Sprite s = null;
        int size = sprites.size();
        for ( int i = 0; i < size; i++ ) {
            s = (Sprite)sprites.get( i );
            s.paintSprite( g );
        }
    }

    public Image getBackgroundImage() {
        return background;
    }

    public void setBackgroundImage( Image i ) {
        background = i;
        validate();
        repaint();
    }

    public void addSprite( Image sprite ) {
        Sprite s = new Sprite( sprite );
        s.setXSpeed(Simulator.xfriction);
        s.setYSpeed( Simulator.speedy);
        s.setX( 300 );
        s.setY( 100 );

        System.out.println( "Sprite at " + sprites.size() + " created with data:" );
        System.out.println( "\tX : " + s.getX() + "\tSpeed : " + s.getXSpeed() );
        System.out.println( "\tY : " + s.getY() + "\tSpeed : " + s.getYSpeed() );
        sprites.add( s );
        repaint();
    }

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

    private static class Sprite {
        private boolean drawBorder;
        private Image image;
        private int y, dy;
        private double x, dx;
        public Sprite( Image image ) {
            this.image = image;
        }

        public void paintSprite( Graphics g ) {
            if ( image != null ) {
                g.drawImage( image, (int)x, y, null );
            }
            if ( drawBorder ) {
                g.setColor( Color.red );
                g.drawRect( (int)x, y, getWidth(), getHeight() );
            }
        }

        public double getX() {
            return x;
        }

        public void setX( double x ) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY( int y ) {
            this.y = y;
        }

        public int getWidth() {
            int w = 0;
            if ( image != null ) {
                w = image.getWidth( null );
            }
            return w;
        }

        public int getHeight() {
            int h = 0;
            if ( image != null ) {
                h = image.getHeight( null );
            }
            return h;
        }

        public double getXSpeed() {
            return dx;
        }

        public void setXSpeed( double dx ) {
            this.dx = dx;
        }

        public int getYSpeed() {
            return dy;
        }

        public void setYSpeed( int dy ) {
            this.dy = dy;
        }

        public void setBorderOn( boolean b ) {
            drawBorder = b;
        }

        public boolean isBorderOn() {
            return drawBorder;
        }
    }

    private class BounceUpdate implements Runnable {
        public void run() {
            while ( true ) {
                try {
                    Thread.sleep( 80 );
                } catch ( InterruptedException x ) {}
                Dimension size = getPreferredSize();
                int indices = sprites.size();
                Sprite s = null;
                for ( int i = 0; i < indices; i++ ) {
                    System.out.println("Speed is "+ Simulator.speedx);
                    System.out.println("Speed is "+ Simulator.speedy);
                    System.out.println(Simulator.xfriction);
                    s = ( Sprite )sprites.get( i );
                    s.setXSpeed(Simulator.xfriction );
                    s.setYSpeed( Simulator.speedy);

                    double x = s.getX() + Simulator.speedx;
                    int y = s.getY() + Simulator.speedy;
                    if ( x + s.getWidth() > size.width || x < 0 ) {
                        s.setXSpeed( -s.getXSpeed() );
                    }
                    if ( y + s.getHeight() > size.height || y < 0 ) {
                        s.setYSpeed( -s.getYSpeed() );
                    }
                    s.setX( s.getX() + s.getXSpeed() );
                    s.setY( s.getY() + s.getYSpeed() );
                }
                repaint();
            }
        }
    }
}