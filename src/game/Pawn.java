package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.sun.nio.sctp.SendFailedNotification;

public class Pawn extends JButton{
	Field pos;
	Board thisB;
	boolean side, picked; //(side) 1 - bia³e,    0 - czarne
	boolean isKing;
	
	
	public Pawn(Field info, boolean side) {
		this.side = side;
		picked = false;
		isKing = false;
        setContentAreaFilled(false);   
	}
	
	public Pawn(Field info, boolean side, Board thisB) {
		this.side = side;
		this.thisB = thisB;
		picked = false;
		isKing = false;
        setContentAreaFilled(false);   
	}
	
	void addLis(PawnListener lis) {
		this.addActionListener(lis);
	}
	
	protected void paintComponent(Graphics g) {
		if (side) {
			g.setColor(new Color(250, 238, 216));
			if (isKing) g.setColor(Color.green);
		}
		else{
			g.setColor(new Color(63, 39, 28));
			if (isKing) g.setColor(Color.blue);
		}
		g.fillOval(3, 10, 60, 60);

    	super.paintComponent(g);
    }
  
    protected void paintBorder(Graphics g) {
    	int lineWidth = 3;
    	Graphics2D g2d = (Graphics2D) g;
    	BasicStroke bs1 = new BasicStroke(lineWidth);
    	g2d.setStroke(bs1);
    	if (side) {
			g.setColor(new Color(250, 238, 216));
			if (isKing) g.setColor(Color.green);
		}
		else{
			g.setColor(new Color(63, 39, 28));
			if (isKing) g.setColor(Color.blue);
		}
    	g.fillOval(3, 10, 60, 60);
    	if (picked == true && this.isEnabled() == true) {
			g.setColor(new Color(0, 172, 0));
			g2d.setStroke(new BasicStroke(3	));
			g.drawOval(3, 10, 60, 60);
		}
    	else if (this.isEnabled() == true) {
    		g.setColor(new Color(115, 120, 193));
			g2d.setStroke(new BasicStroke(3));
			g.drawOval(3, 10, 60, 60);
    	}
    }
   
    Boolean canCapture() {
    	if (!isKing) {
	    	for (int j=0; j<4; j++) {
				if(pos.neighbours[j]!=Board.nullField) {
					if(pos.neighbours[j].hasPawn) {
						if (pos.neighbours[j].neighbours[j]!=Board.nullField) {
							if (!pos.neighbours[j].neighbours[j].hasPawn) {
								if (((Pawn)pos.neighbours[j].getComponent(0)).side!=side)
									return true;
							}
						}
					}
				}
			}
    	}
    	else {
    		for (int i=0; i<4; i++) {
    			Boolean a = seeFurth(pos.neighbours[i], i);
    			if (a)
    				return a;
    		}
    	}
    	return false;
    }
    
    Boolean seeFurth (Field neighbour, int i) {
    	if (neighbour==Board.nullField||neighbour.neighbours[i]==Board.nullField) {
    		return false;				//0 - brak biæ
    	}
    	else {
    		if (neighbour.hasPawn&&(((Pawn)neighbour.getComponent(0)).side!=side)) {
    			if (!neighbour.neighbours[i].hasPawn) {
    				return true;
    			}
    			else {
    				return false;
    			}
    		}
    	}
    	return seeFurth(neighbour.neighbours[i], i);
    }
}

