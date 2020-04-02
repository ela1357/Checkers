package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PawnListener implements ActionListener {

	Pawn thisP;
	Board thisB;
	
	
	public PawnListener(Pawn thisP, Board thisB) {
		this.thisP=thisP;
		this.thisB=thisB;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!thisP.picked) {
			thisP.picked = true;
			thisB.pickedPawn=thisP;
			findMoves();
			closeAll();
		}
		else {
			thisB.disableAllF();
			openAll();
			thisP.picked = false;
		}
	}
	
	void closeAll() {
		for (int i=0; i<20; i++) {
			if (thisB.pickedPawn!=thisB.whites[i])
				thisB.whites[i].setEnabled(false);
			if (thisB.pickedPawn!=thisB.blacks[i])
				thisB.blacks[i].setEnabled(false);
		}
	}
	
	void openAll() {
		for (int i=0; i<20; i++) {
			if (thisB.pickedPawn.side)
				thisB.whites[i].setEnabled(true);
			else
				thisB.blacks[i].setEnabled(true);
		}
	}
	/*
	void findMoves(){
		for (int i=0; i<4; i++) {
		if(thisP.pos.neighbours[i]!=Board.nullField)
			if(thisP.pos.neighbours[i].hasPawn) {
				if(((Pawn)thisP.pos.neighbours[i].getComponent(0)).side!=thisP.side){
					if(thisP.pos.neighbours[i].neighbours[i]!=Board.nullField) {
						if(!thisP.pos.neighbours[i].neighbours[i].hasPawn) {
							thisP.pos.neighbours[i].neighbours[i].setEnabled(true);
							thisP.pos.neighbours[i].neighbours[i].jumpedOver = (Pawn)thisP.pos.neighbours[i].getComponent(0);
							thisP.pos.neighbours[i].neighbours[i].lis = new FieldListener(thisP.pos.neighbours[i].neighbours[i], thisB);
							thisP.pos.neighbours[i].neighbours[i].addActionListener(thisP.pos.neighbours[i].neighbours[i].lis);
						}
					}
				}
			}
			else {
				if (thisP.side) {
					if (i==0||i==3) {
						thisP.pos.neighbours[i].setEnabled(true);
						thisP.pos.neighbours[i].lis = new FieldListener(thisP.pos.neighbours[i], thisB);
						thisP.pos.neighbours[i].addActionListener(thisP.pos.neighbours[i].lis);
					}
				}
				else {
					if (i==1||i==2) {
						thisP.pos.neighbours[i].setEnabled(true);
						thisP.pos.neighbours[i].lis = new FieldListener(thisP.pos.neighbours[i], thisB);
						thisP.pos.neighbours[i].addActionListener(thisP.pos.neighbours[i].lis);
					}
				}
				
			}
		}
	}
	*/
	
	void findMoves() {
		int n[] = {0,0,0,0};
		int m = 0;
		for (int i=0; i<4; i++) {
			if(thisP.pos.neighbours[i]!=Board.nullField) {
				if(thisP.pos.neighbours[i].hasPawn) {
					if (thisP.pos.neighbours[i].neighbours[i]!=Board.nullField) {
						if (!thisP.pos.neighbours[i].neighbours[i].hasPawn) {
							if(((Pawn)(thisP.pos.neighbours[i].getComponent(0))).side!=thisP.side)
								n[i] = captureFound(n[i]+1, i, thisP.pos.neighbours[i].neighbours[i]);
						}
					}
				}
			}
		}
		for (int i=0; i<4; i++) {
			if (m<n[i])
				m=n[i];
			/*if(thisP.pos.neighbours[i].hasPawn) {
				if(((Pawn)(thisP.pos.neighbours[i].getComponent(0))).side!=thisP.side) {
					n[i]=0;
				}
			}*/
		}
		for (int i=0; i<4; i++) {
			if(thisP.pos.neighbours[i]!=Board.nullField) {
				if(thisP.pos.neighbours[i].hasPawn) {
					if (thisP.pos.neighbours[i].neighbours[i]!=Board.nullField) {
						if(((Pawn)(thisP.pos.neighbours[i].getComponent(0))).side!=thisP.side) {
							if (!thisP.pos.neighbours[i].neighbours[i].hasPawn) {
								if (n[i]==m) {
									thisP.pos.neighbours[i].neighbours[i].setEnabled(true);
									thisP.pos.neighbours[i].neighbours[i].jumpedOver = (Pawn)thisP.pos.neighbours[i].getComponent(0);
									thisP.pos.neighbours[i].neighbours[i].lis = new FieldListener(thisP.pos.neighbours[i].neighbours[i], thisB, m);
									thisP.pos.neighbours[i].neighbours[i].addActionListener(thisP.pos.neighbours[i].neighbours[i].lis);
								}
							}
						}
					}
				}
			}
		}
		if(m==0) {
			for (int i=0; i<4; i++) {
				if (!thisP.pos.neighbours[i].hasPawn) {
					if (thisP.side) {
						if (i==0||i==3) {
							thisP.pos.neighbours[i].setEnabled(true);
							thisP.pos.neighbours[i].lis = new FieldListener(thisP.pos.neighbours[i], thisB, 0);
							thisP.pos.neighbours[i].addActionListener(thisP.pos.neighbours[i].lis);
						}
					}
					else {
						if (i==1||i==2) {
							thisP.pos.neighbours[i].setEnabled(true);
							thisP.pos.neighbours[i].lis = new FieldListener(thisP.pos.neighbours[i], thisB, 0);
							thisP.pos.neighbours[i].addActionListener(thisP.pos.neighbours[i].lis);
						}
					}
				}
			}
		}
	}

	
	int captureFound(int n, int init, Field virtF) {
		int m=1;
		int j=0;
		if (init==0)
			j=2;
		if (init==1)
			j=3;
		if (init==2)
			j=0;
		if (init==3)
			j=1;
			
		for (int i=0; i<4; i++) {
			if(i!=j) {
				if(virtF.neighbours[i]!=Board.nullField) {
					if(virtF.neighbours[i].hasPawn) {
						if (virtF.neighbours[i].neighbours[i]!=Board.nullField) {
							if (!virtF.neighbours[i].neighbours[i].hasPawn) {
								if(((Pawn)(virtF.neighbours[i].getComponent(0))).side!=thisB.pickedPawn.side){
									if (n<6)
										m = captureFound(n+1, i, virtF.neighbours[i].neighbours[i]);
									if (m>n)
										n=m;
								}
							}
						}
					}
				}
			}
		}
		System.out.println(n);
		return n;
	}
}
