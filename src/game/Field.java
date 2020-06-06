package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;

public class Field extends JButton{
	int x, y;		//field position
	boolean hasPawn;
	Field [] neighbours;
	Pawn jumpedOver;
	FieldListener lis;
	Random r = new Random();
	int m = r.nextInt(60);
	int n = r.nextInt(60);
	int o = r.nextInt(60);
	Boolean b = false;
	
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
    	if(b == true) {
    		setBackground(new Color(188, 214, 237));
    	}
    	if (this.isEnabled() == true) {
			setBackground(new Color(203, 254, 181));
		}
		else if (this.isEnabled() == false && this.b == false){
			if(Settings.color == true) {
				setBackground(new Color(208, 166, 128));
			}
			else if(Settings.color == false) {
				setBackground(new Color(m+75, n+75, o+75));
			}
		}
    	
    }
}
