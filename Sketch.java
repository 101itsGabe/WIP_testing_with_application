import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Sketch extends JPanel
{
  private int mx = 0, my = 0;
  private String str;
  public Vector<Integer> drawx = new Vector<Integer>();
  public Vector<Integer> drawy = new Vector<Integer>();

  public Sketch(JFrame frame)
  {
   JPanel temp = this;
   addMouseListener(new MouseAdapter()
   {
     Timer timer;
     TimerTask task;
     boolean ifPressed = false;
     int cx = 0, cy = 0;
     private class MyTimerTask extends TimerTask
     {
      public void run()
      {
       PointerInfo a = MouseInfo.getPointerInfo();
       Point p = a.getLocation();

       mx = (int)p.getX() - (frame.getX() - temp.getX());
       my = (int)p.getY() - (frame.getY() + 28);
       drawx.add(mx);
       drawy.add(my);
       repaint();
      }
     }

     public void mousePressed(MouseEvent e)
     {
      timer = new Timer();
      task = new MyTimerTask();
      timer.schedule(task,0,10);
     }

    public void mouseReleased(MouseEvent e)
    {
     task.cancel();
     timer.cancel();
     timer.purge();
    }

   });
  }
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);            //call superclass's paintComponent
    Graphics2D g2d = (Graphics2D) g;

    str = new String("X: " + mx + " Y: " + my);
    g.drawString(str,300,150);

    g.setColor(Color.red);

    for(int i = 0; i < drawx.size(); i++)
    {
       g.fillOval(drawx.get(i),drawy.get(i),5,5);
    }

  }



    public static void main(String args[])
   {
      JFrame frame = new JFrame("Swiffers Sketchbook");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    Sketch sketchJPanel = new Sketch(frame);             //create SketchJPanel
    frame.add(sketchJPanel);    //add sketchJPanel to the frame
    sketchJPanel.setBackground(Color.lightGray);
    frame.setSize(800,500);
    frame.setVisible(true);


   }
}
