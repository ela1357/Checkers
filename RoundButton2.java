package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class RoundButton2 extends JButton {

	Shape shape;
	
	public RoundButton2(String label) {
        super(label);
        setContentAreaFilled(false);        
    }
  
    protected void paintComponent(Graphics g) {
    	g.setColor(new Color(248, 243, 235));
    	g.fillRoundRect(0, 0, 199, 49, 20, 20);

    	super.paintComponent(g);
    }
  
    protected void paintBorder(Graphics g) {
    	int lineWidth = 3;
    	Graphics2D g2d = (Graphics2D) g;
    	BasicStroke bs1 = new BasicStroke(lineWidth);
    	g2d.setStroke(bs1);
        g.setColor(Color.darkGray);
        g.drawRoundRect(0, 0, 199, 49, 20, 20);    
    }
    
    
    public static void main(String[] args) {

	}

}
