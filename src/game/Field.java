package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.border.Border;

public class Field extends JButton{
	int x, y;		//field position
	boolean hasPawn;
	Field [] neighbours;
	Pawn jumpedOver;
	FieldListener lis;
	
	
	void setPos(int X, int Y){
		x=X;
		y=Y;
	}
	
	Field(){
		hasPawn=false;
		setBackground(new Color(208, 166, 128));
		jumpedOver = Board.nullPawn;
		neighbours = new Field [4];  //0 - lewo_góra,	1 - lewo_dó³,	2 - prawo_dó³,	3 - prawo_góra
		for (int i =0; i<4; i++)
			neighbours[i]=Board.nullField;
	}
	
	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	if (this.isEnabled() == true) {
			setBackground(new Color(203, 254, 181));
		}
		else if (this.isEnabled() == false){
			setBackground(new Color(208, 166, 128));
		}
    }
}
