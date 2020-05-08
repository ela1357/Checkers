package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import buttons.RoundButton3;

public class Board extends JFrame implements ActionListener{
	Field [][] fields = new Field [10][5];
	Pawn [] whites = new Pawn [20];
	Pawn [] blacks = new Pawn [20];
	public static final Field nullField = new Field();
	public static final Pawn nullPawn = new Pawn(nullField, true);
	Pawn pickedPawn;
	
	JPanel panel, center;
	JButton back;
	static Boolean res;
	Boolean language = Menu.language;
	public void language() {
		if(language == true) {
			back.setText("Wróæ");
		}
		else if(language == false) {
			back.setText("Back");
		}
	}
	
	Board(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1100, 1000);
		setMinimumSize(new Dimension(1100, 1000));
		
		panel = new JPanel(new GridBagLayout());
		center = new JPanel(new GridLayout(10, 10));
		center.setPreferredSize(new Dimension(900, 900));
		center.setBorder(new LineBorder(Color.GRAY));
		
		panel.setBackground(new Color(245, 234, 225));
		center.setBackground(Color.white);
		
		setFields();
		setNeis();	
		setPawns();
		
		back = new RoundButton3("");
		GridBagConstraints b = new GridBagConstraints();
		GridBagConstraints c = new GridBagConstraints();
		b.anchor = GridBagConstraints.LAST_LINE_START;
		c.anchor = GridBagConstraints.CENTER;
		//b.ipady = 0;
		//b.weightx = 0.1;
		b.fill = GridBagConstraints.HORIZONTAL;
		b.insets = new Insets(500,0,0,75);
		panel.add(back, b);
		panel.add(center, c);
		
		language();

		back.addActionListener(this);
		
		add(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Menu frame2 = new Menu();
		frame2.setVisible(true);
		this.setVisible(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.dispose();
		
	}
	
	
	void setFields() {
		for (int i=0; i<10; i++) {
			for (int j=0; j<5; j++) {
				fields[i][j]=new Field();
				//fields[i][j].setText("i = " + i + "\nj = " + j);
				fields[i][j].setPos(j, i);
				fields[i][j].setEnabled(false);
				fields[i][j].setLayout(new GridLayout(1, 1));
				if(i%2==1) {
					center.add(fields[i][j]);
					center.add(new BlankField());
				}
				else {
					center.add(new BlankField());
					center.add(fields[i][j]);
				}
			}
		}
	}
	
	void setPawns() {
		for (int i=0; i<20; i++) {
			whites[i] = new Pawn(fields[0][0], true, this);
			blacks[i] = new Pawn(fields[0][0], false, this);
		}
		for (int i=0; i<4; i++) {
			for (int j=0; j<5; j++) {
				fields[i][j].add(blacks[i*5+j]);
				fields[i][j].hasPawn=true;
				blacks[i*5+j].pos = fields[i][j];
				blacks[i*5+j].addLis(new PawnListener(blacks[i*5+j], this));
			}
		}
		for (int i=6; i<10; i++) {
			for (int j=0; j<5; j++) {
				fields[i][j].add(whites[(i-6)*5+j]);
				fields[i][j].hasPawn=true;
				whites[(i-6)*5+j].pos = fields[i][j];
				whites[(i-6)*5+j].addLis(new PawnListener(whites[(i-6)*5+j], this));
			}
		}
	}
	
	// ***NUMERACJA PÓL OD LEWEJ DÓ³***
	
	void setNeis () {		//Pobranie adresów s¹siadów. (Mo¿liwe ruchy.)
		for (int i=0; i<10; i++) {
			for (int j=0; j<5; j++) {
				if (i%2==0) {
					if(i>0) {
						fields[i][j].neighbours[0] = fields[i-1][j];
						if(j<4)
							fields[i][j].neighbours[3] = fields[i-1][j+1];
					}
					if(i<9){
						fields[i][j].neighbours[1] = fields[i+1][j];
						if(j<4)
							fields[i][j].neighbours[2] = fields[i+1][j+1];
					}
				}
				else {
					if(i>0) {
						if(j>0)
							fields[i][j].neighbours[0] = fields[i-1][j-1];
						fields[i][j].neighbours[3] = fields[i-1][j];
					}
					if(i<9){
						if(j>0)
							fields[i][j].neighbours[1] = fields[i+1][j-1];
						fields[i][j].neighbours[2] = fields[i+1][j];
					}
				}
			}
		}
	}
	
	void disableAllF() {       							//zamykanie pól
		if (!pickedPawn.isKing) {
			for (int i=0; i<4; i++) {
				if (this.pickedPawn.pos.neighbours[i]!=Board.nullField) {
					if (this.pickedPawn.pos.neighbours[i].neighbours[i]!=Board.nullField) {
						this.pickedPawn.pos.neighbours[i].neighbours[i].jumpedOver = Board.nullPawn;
						this.pickedPawn.pos.neighbours[i].neighbours[i].removeActionListener(this.pickedPawn.pos.neighbours[i].neighbours[i].lis);
						this.pickedPawn.pos.neighbours[i].neighbours[i].setEnabled(false);
					}
					this.pickedPawn.pos.neighbours[i].jumpedOver = Board.nullPawn;
					this.pickedPawn.pos.neighbours[i].removeActionListener(this.pickedPawn.pos.neighbours[i].lis);
					this.pickedPawn.pos.neighbours[i].setEnabled(false);
				}
			}
			this.pickedPawn.pos.setEnabled(false);
		}
		else {											//isKing
			for (int i=0; i<4; i++) {
				Field virtF=pickedPawn.pos;
				do{
					virtF.jumpedOver = Board.nullPawn;
					virtF.removeActionListener(virtF.lis);
					virtF.setEnabled(false);
					virtF=virtF.neighbours[i];
				}while(virtF!=Board.nullField);
			}
		}
	}
	
	void openAll(Boolean side) {						// odblokowanie pionków które mog¹ siê ruszyæ
		Boolean n=true;
		int m=0;
		Boolean win = true;

		if (!side) {									//otwarcie bia³ych
			for (int i=0; i<20; i++) {
				if(whites[i].pos!=nullField) {
					for (int j=0; j<4; j++) {
						int mm = ((PawnListener)whites[i].getActionListeners()[0]).captureFound(0, j, whites[i].pos);
						if (mm>m)
							m=mm;
					}
				}
			}
			if (m!=0) {
				win=false;
				for (int i=0; i<20; i++) {
					if(whites[i].pos!=nullField) {
						for (int j=0; j<4; j++) {
							if (((PawnListener)whites[i].getActionListeners()[0]).captureFound(0, j, whites[i].pos)==m)
								whites[i].setEnabled(true);
						}
					}
				}
			}
			if(m==0) {
				for (int i=0; i<20; i++) {
					if(whites[i].pos!=nullField) {
						if (!whites[i].isKing) {
							if (!whites[i].pos.neighbours[0].hasPawn&&whites[i].pos.neighbours[0]!=nullField) {
								whites[i].setEnabled(true);
								win=false;
							}
							else if(!whites[i].pos.neighbours[3].hasPawn&&whites[i].pos.neighbours[3]!=nullField) {
								whites[i].setEnabled(true);
								win=false;
							}
						}
						else {								//isKing
							for (int j=0; j<4; j++) {
								if (!whites[i].pos.neighbours[j].hasPawn&&whites[i].pos.neighbours[j]!=nullField) {
									whites[i].setEnabled(true);
									win=false;
								}
							}
						}
					}
				}
			}
			
			/*for (int i=0; i<20; i++) {
				if (whites[i].pos!=nullField&&whites[i].canCapture()) {
					whites[i].setEnabled(true);
					n=false;
				}
			}
			if (n)
				for (int i=0; i<20; i++) {
					whites[i].setEnabled(true);
				}*/
		}
		else {											//otwarcie czarnych
			for (int i=0; i<20; i++) {
				if(blacks[i].pos!=nullField) {
					for (int j=0; j<4; j++) {
						int mm = ((PawnListener)blacks[i].getActionListeners()[0]).captureFound(0, j, blacks[i].pos);
						if (mm>m)
							m=mm;
					}
				}
			}
			if (m!=0) {
				win=false;
				for (int i=0; i<20; i++) {
					if(blacks[i].pos!=nullField) {
						for (int j=0; j<4; j++) {
							if (((PawnListener)blacks[i].getActionListeners()[0]).captureFound(0, j, blacks[i].pos)==m)
								blacks[i].setEnabled(true);
						}
					}
				}
			}
			if(m==0) {
				for (int i=0; i<20; i++) {
					if(blacks[i].pos!=nullField) {
						if (!blacks[i].isKing) {
							if (!blacks[i].pos.neighbours[1].hasPawn&&blacks[i].pos.neighbours[1]!=nullField) {
								blacks[i].setEnabled(true);
								win=false;
							}
							else if(!blacks[i].pos.neighbours[2].hasPawn&&blacks[i].pos.neighbours[2]!=nullField) {
								blacks[i].setEnabled(true);
								win=false;
							}
						}
						else {								//isKing
							for (int j=0; j<4; j++) {
								if (!blacks[i].pos.neighbours[j].hasPawn&&blacks[i].pos.neighbours[j]!=nullField) {
									blacks[i].setEnabled(true);
									win=false;
								}
							}
						}
					}
				}
			}
			
			/*for (int i=0; i<20; i++) {
				if (blacks[i].pos!=nullField&&blacks[i].canCapture()) {
					blacks[i].setEnabled(true);
					n=false;
				}
			}
			if (n)
				for (int i=0; i<20; i++) {
					blacks[i].setEnabled(true);
				}*/
		}
	if (side==pickedPawn.side)
		pickedPawn.setEnabled(false);
	if (win)
		end(side);
	}
	
	/*void canEnd () {											// zakoñczenie gry
		Boolean n=true;
		Boolean m=true;
		for (int i=0; i<20; i++) {
			if (whites[i].pos!=nullField)
				n=false;
			if (blacks[i].pos!=nullField)
				m=false;
		}
		//if (n||m)
			//System.exit(0);
		if(n) {
			res = false;
			Result frame = new Result(this);
			frame.setVisible(true);
		}
		if(m) {
			res = true;
			Result frame = new Result(this);
			frame.setVisible(true);
		}	
	}*/
	
	void end(Boolean side) {
		if (side) {
			res = true;
			Result frame = new Result(this);
			frame.setVisible(true);
		}
		else {
			res = false;
			Result frame = new Result(this);
			frame.setVisible(true);
		}
	}
}
