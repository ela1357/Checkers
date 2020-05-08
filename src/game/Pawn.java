package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

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
	
	/*protected void paintComponent(Graphics g) {
		
		File imageFile = new File("crown.png");
        BufferedImage image = null;
 		try {
 			image = ImageIO.read(imageFile);
 		} catch (IOException e) {
 			System.err.println("Blad odczytu obrazka");
 			e.printStackTrace();
 		}
		
		if (side) {
			g.setColor(new Color(250, 238, 216));
			g.fillOval(0, 10, 55, 55);
			//if (isKing) g.drawImage(image, getX()-8, getY()+10, getWidth()-15, getHeight()-35, null);
		}
		else{
			g.setColor(new Color(63, 39, 28));
			g.fillOval(0, 10, 55, 55);
			//if (isKing) g.drawImage(image, getX()-8, getY()+10, getWidth()-15, getHeight()-35, null);
		}
		//g.fillOval(0, 10, 55, 55);

    	super.paintComponent(g);
    }*/
  
    protected void paintBorder(Graphics g) {
    	File imageFile = new File("crown.png");
        BufferedImage image = null;
 		try {
 			image = ImageIO.read(imageFile);
 		} catch (IOException e) {
 			System.err.println("Blad odczytu obrazka");
 			e.printStackTrace();
 		}
    	int lineWidth = 3;
    	Graphics2D g2d = (Graphics2D) g;
    	BasicStroke bs1 = new BasicStroke(lineWidth);
    	g2d.setStroke(bs1);
    	if (side) {
			g.setColor(new Color(250, 238, 216));
			g.fillOval(0, 10, 55, 55);
			g.setColor(new Color(63, 39, 28));
			g.drawOval(0, 10, 55, 55);
			if (isKing) g.drawImage(image, getX()-8, getY()+10, getWidth()-15, getHeight()-35, null);
			//if (isKing) g.setColor(Color.green);
		}
		else{
			g.setColor(new Color(63, 39, 28));
			g.fillOval(0, 10, 55, 55);
			g.setColor(new Color(103, 125, 142));
			g.drawOval(0, 10, 55, 55);
			if (isKing) g.drawImage(image, getX()-8, getY()+10, getWidth()-15, getHeight()-35, null);
			//if (isKing) g.setColor(Color.blue);
		}
    	//g.fillOval(0, 10, 55, 55);
    	if (picked == true && this.isEnabled() == true) {
			g.setColor(new Color(0, 172, 0));
			g2d.setStroke(new BasicStroke(3	));
			g.drawOval(0, 10, 55, 55);
		}
    	else if (this.isEnabled() == true) {
    		g.setColor(new Color(115, 120, 193));
			g2d.setStroke(new BasicStroke(3));
			g.drawOval(0, 10, 55, 55);
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

