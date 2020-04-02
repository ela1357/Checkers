package game;

import java.awt.Color;
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
		setBackground(Color.blue);
		jumpedOver = Board.nullPawn;
		neighbours = new Field [4];  //0 - lewo_g�ra,	1 - lewo_d�,	2 - prawo_d�,	3 - prawo_g�ra
		for (int i =0; i<4; i++)
			neighbours[i]=Board.nullField;
	}
	
}
