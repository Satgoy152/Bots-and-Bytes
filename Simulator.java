import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Double;
public class Simulator implements KeyListener {
    static int  speedx = 0;
    static int  speedy = 0;
    static double xfriction = speedx;
    static double yfriction = speedy;
    static double friction = 0.1;
    //static int roundSpeedx = xfriction.intValue();

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            System.out.println("Right");
            speedx++;
            //Right arrow key code
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            System.out.println("Left");
            speedx--;
            //Left arrow key code
        } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            System.out.println("Up");
            speedy--;
            //Up arrow key code
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            System.out.println("Down");
            speedy++;
            //Down arrow key code
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            //System.out.println("Right");
            xfriction=speedx*friction;
            //speedx=xfriction;
            if(xfriction<0.2 && xfriction>-0.2 ) {
                speedx = 0;

            }
            //Right arrow key code
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            System.out.println("Left");
            xfriction=speedx*friction;
            if(speedx<0.2 && speedx>-0.2 ) {
                speedx = 0;

            }
            //Left arrow key code
        } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            System.out.println("Up");
            speedy=0;
            //Up arrow key code
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            System.out.println("Down");
            speedy=0;
            //Down arrow key code
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
