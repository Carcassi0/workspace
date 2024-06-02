
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

/**
 * This program demonstrates the use of inner classes.
 * @version 1.11 2017-12-14
 * @author Cay Horstmann
 */
public class InnerClassTest
{
   public static void main(String[] args) throws InterruptedException
   {
      TalkingClock clock = new TalkingClock(1000, true);
      TalkingClock.TimePrinter a = clock.new TimePrinter();
      Timer newT = new Timer(1000, a);
      newT.start();

      Thread.sleep(10000); // main thread sleeps here for 10 seconds
      // keep program running until the user selects "OK"
      // JOptionPane.showMessageDialog(null, "Quit program?");
      System.exit(0);
   }
}

/**
 * A clock that prints the time in regular intervals.
 */
class TalkingClock
{
   private int interval;
   private boolean beep;

   /**
    * Constructs a talking clock
    * @param interval the interval between messages (in milliseconds)
    * @param beep true if the clock should beep
    */
   public TalkingClock(int interval, boolean beep)
   {
      this.interval = interval;
      this.beep = beep;
   }

   /**
    * Starts the clock.
    */
   public void start()
   {
      var listener = new TimePrinter();
      var timer = new Timer(interval, listener);
      timer.start();
   }

   public class TimePrinter implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         System.out.println("At the tone, the time is " 
            + Instant.ofEpochMilli(event.getWhen()));
         //   if (beep) Toolkit.getDefaultToolkit().beep();
         // printing BEEP instead of making a sound
         if (beep) System.out.println("---BEEP---"); 
      }
   }
}
