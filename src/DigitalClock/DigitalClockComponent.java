package GUITools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

import GUITools.GUITools;

@SuppressWarnings("serial")
public class DigitalClockComponent extends JComponent implements Runnable {

	DigitalClock digitalClock;
	private int hight = 0;
	private int width = 0;
	private Color digitColor;
	private int digitSize = 0;
	private Font digitFont;// = GUITools.loadTTFFontFromFile("Geo.ttf", 10);
	private Thread runner;
	
	public DigitalClockComponent(Color digitColor, int digitSize, Font digitFont){
		super();
		this.digitalClock = new DigitalClock();
		this.digitalClock.setFormat(DigitalClock.TWELVE_HOUR);
		//this.digitFont = digitFont;
		this.digitColor = digitColor;
		this.digitSize = digitSize;
		if(this.digitColor == null){
			this.digitColor = GUITools.getRandomColor();
		}
		if(this.digitFont == null){
			this.digitFont = GUITools.getRandomFont(this.digitSize);
		}
		this.hight = digitSize + 15;
		this.setFont(this.digitFont);
		this.width = getFontMetrics(this.digitFont).stringWidth(this.digitalClock.timeNow()) + 50;
		
		//this.setSize(this.width, this.hight);
		this.setBorder(new LineBorder(Color.WHITE, 4, false));
		this.setBackground(Color.WHITE);
		this.validate();
		this.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(this.digitFont);
        g2.setColor(this.digitColor);//this.digitColor);
        g2.drawString(this.digitalClock.timeNow(), 15, this.digitSize + 5);
	}
	
	public void start(){
      if(this.runner == null) this.runner = new Thread(this);
      this.runner.start();
                                                            //method to start thread
    }
	
	public Dimension getPreferredSize(){
		return new Dimension(this.width, this.hight);
	}
	public Dimension getMinimumSize(){
		return new Dimension(this.width, this.hight);
	}
	public Dimension getMaximumSize(){
		return new Dimension(this.width, this.hight);
	}
	
	public void run(){
      while (runner == Thread.currentThread() ){
       this.repaint();
                                                        //define thread task
          try
            {
              Thread.sleep(1000);
            }
             catch(InterruptedException e)
                 {
                   System.out.println("Thread failed");
                 }
                 
      }
    }
}
