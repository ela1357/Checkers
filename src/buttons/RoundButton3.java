package buttons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class RoundButton3 extends JButton {

	public RoundButton3(String label) {
		super(label);
        setContentAreaFilled(false);   
	}

    protected void paintComponent(Graphics g) {
    	g.setColor(new Color(248, 243, 235));
    	g.fillRoundRect(0, 0, 64, 25, 10, 10);

    	super.paintComponent(g);
    }
  
    protected void paintBorder(Graphics g) {
    	int lineWidth = 3;
    	Graphics2D g2d = (Graphics2D) g;
    	BasicStroke bs1 = new BasicStroke(lineWidth);
    	g2d.setStroke(bs1);
        g.setColor(Color.darkGray);
        g.drawRoundRect(0, 0, 64, 25, 10, 10);    
    }

}
