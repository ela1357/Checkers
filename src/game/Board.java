package game;

import java.awt.GridLayout;
import javax.swing.JFrame;

public class Board extends JFrame{
	Field [][] fields = new Field [10][5];
	Pawn [] whites = new Pawn [20];
	Pawn [] blacks = new Pawn [20];
	public static final Field nullField = new Field();
	public static final Pawn nullPawn = new Pawn(nullField, true);
	Pawn pickedPawn;
	
	
	Board(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1000, 1000);
		setLayout(new GridLayout(10, 10));
		setFields();
		setNeis();	
		setPawns();
	}
	
	void setFields() {
		for (int i=0; i<10; i++) {
			for (int j=0; j<5; j++) {
				fields[i][j]=new Field();
				fields[i][j].setText("i = " + i + "\nj = " + j);
				fields[i][j].setPos(j, i);
				fields[i][j].setEnabled(false);
				fields[i][j].setLayout(new GridLayout(1, 1));
				if(i%2==1) {
					add(fields[i][j]);
					add(new BlankField());
				}
				else {
					add(new BlankField());
					add(fields[i][j]);
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

		if (!side) {
			for (int i=0; i<20; i++) {
				if(whites[i].pos!=nullField) {
					for (int j=0; j<4; j++) {
						int mm = ((PawnListener)whites[i].getActionListeners()[0]).captureFound(0, j, whites[i].pos);
						if (mm>m)
							m=mm;
					}
				}
			}
			for (int i=0; i<20; i++) {
				if(whites[i].pos!=nullField) {
					for (int j=0; j<4; j++) {
						if (((PawnListener)whites[i].getActionListeners()[0]).captureFound(0, j, whites[i].pos)==m)
							whites[i].setEnabled(true);
					}
				}
			}
			if(m==0) {
				for (int i=0; i<20; i++) {
					if(whites[i].pos!=nullField) {
						whites[i].setEnabled(true);
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
		else {
			for (int i=0; i<20; i++) {
				if(blacks[i].pos!=nullField) {
					for (int j=0; j<4; j++) {
						int mm = ((PawnListener)blacks[i].getActionListeners()[0]).captureFound(0, j, blacks[i].pos);
						if (mm>m)
							m=mm;
					}
				}
			}
			for (int i=0; i<20; i++) {
				if(blacks[i].pos!=nullField) {
					for (int j=0; j<4; j++) {
						if (((PawnListener)blacks[i].getActionListeners()[0]).captureFound(0, j, blacks[i].pos)==m)
							blacks[i].setEnabled(true);
					}
				}
			}
			if(m==0) {
				for (int i=0; i<20; i++) {
					if(blacks[i].pos!=nullField) {
						blacks[i].setEnabled(true);
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
	}
	
	void canEnd () {											// zakoñczenie gry
		Boolean n=true;
		for (int i=0; i<20; i++) {
			if (whites[i].pos!=nullField)
				n=false;
			if (blacks[i].pos!=nullField)
				n=false;
		}
		if (n)
			System.exit(0);
	}
}
