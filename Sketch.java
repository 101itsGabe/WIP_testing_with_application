import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Sketch extends JPanel
{
  private int mx = 0, my = 0;
  private String str;


  public Sketch()
  {
   addMouseListener(new MouseAdapter()
   {
     Timer timer = new Timer();
     TimerTask task = new MyTimerTask();
     boolean ifPressed = false;
     private class MyTimerTask extends TimerTask
     {
      public void run()
      {
       PointerInfo a = MouseInfo.getPointerInfo();
       Point p = a.getLocation();
       mx = (int) p.getX();
       my = (int) p.getY();
       repaint();
      }
     }

     public void mousePressed(MouseEvent e)
     {
      timer.schedule(task,0,80);
     }

    public void mouseReleased(MouseEvent e)
    {
     timer.stop();
    }

/*
   public static void runDraw(MouseEvent m)
   {
     while(m.ifPressed)
     {
       timer.schedule(task,0,80);
       if(!m.ifPressed)
       task.cancel();
     }
   }
 */

   });
  }
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);            //call superclass's paintComponent
    Graphics2D g2d = (Graphics2D) g;

    str = new String("X: " + mx + "Y: " + my);
    g.drawString(str,300,150);

    g.setColor(Color.red);
    g.drawOval(mx,my,5,5);
    g.fillOval(mx,my,5,5);

  }



    public static void main(String args[])
   {
      JFrame frame = new JFrame("Swiffers Sketchbook");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    Sketch sketchJPanel = new Sketch();             //create SketchJPanel
    frame.add(sketchJPanel);    //add sketchJPanel to the frame
    sketchJPanel.setBackground(Color.lightGray);
    frame.setSize(600,300);
    frame.setVisible(true);


   }
}
