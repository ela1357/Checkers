package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.sun.nio.sctp.SendFailedNotification;

public class Pawn extends JButton{
	Field pos;
	boolean side, picked; //(side) 1 - bia³e,    0 - czarne
	
	
	public Pawn(Field info, boolean side) {
		this.side = side;
		picked = false;
		if (side) setBackground(Color.white);
		else setBackground(Color.black);
		setPreferredSize(info.getSize());
	}
	
	void addLis(PawnListener lis) {
		this.addActionListener(lis);
	}
	
}

