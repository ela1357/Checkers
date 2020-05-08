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
			thisB.openAll(!thisP.side);
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
	
	void findMoves() {
		int n[] = {0,0,0,0};
		int m = 0;
		if (!thisP.isKing) {
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
		}
		else {											//isKing
			for (int i=0; i<4; i++) {
				n[i]=captureFurth(0, thisP.pos, i);
			}
		}
		for (int i=0; i<4; i++) {
			if (m<n[i])
				m=n[i];
		}
		//-----------------------------------------------------------------------------------
		if (!thisP.isKing) {
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
		}
		else {										//isKing
			for (int i=0; i<4; i++) {
				firstCapFurth(thisP.pos, i, m);
			}
		}
		//-------------------------------------------------------------------------------
		if(m==0) {
			if (!thisP.isKing) {
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
			else {									//isKing
				for (int i=0; i<4; i++) {
					Field virtF=thisP.pos;
					while(virtF.neighbours[i]!=Board.nullField&&!virtF.neighbours[i].hasPawn){
						virtF=virtF.neighbours[i];
						
						virtF.setEnabled(true);
						virtF.lis = new FieldListener(virtF, thisB, 0);
						virtF.addActionListener(virtF.lis);
					}
				}
			}
		}
	}

	
	int captureFound(int n, int init, Field virtF) {		// n - iloœæ biæ do tej pory, init - kierunek z którego przyby³ pionek, virtF - aktualnie rozpatrywane pole
		int m=0;
		int j=0;
		if (init==0)
			j=2;
		if (init==1)
			j=3;
		if (init==2)
			j=0;
		if (init==3)
			j=1;
			
		if (!thisP.isKing) {
			for (int i=0; i<4; i++) {
				if(i!=j) {
					if(virtF.neighbours[i]!=Board.nullField) {
						if(virtF.neighbours[i].hasPawn) {
							if (virtF.neighbours[i].neighbours[i]!=Board.nullField) {
								if (!virtF.neighbours[i].neighbours[i].hasPawn) {
									if(((Pawn)(virtF.neighbours[i].getComponent(0))).side!=thisP.side){
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
		}
		else {					//thisP.isKing
			for (int i=0; i<4; i++) {
				if(i!=j) {
					m = captureFurth(n, virtF, i);
					if(m>n)
						n=m;
				}
			}
		}
		//System.out.println(n);
		return n;
	}
	
	int captureFurth (int n, Field virtF, int i) {									// i - strona w któr¹ sprawdza
    	if (n>6)
    		return 6;
		if (virtF.neighbours[i]==Board.nullField||virtF.neighbours[i].neighbours[i]==Board.nullField) {
    		return n;				//brak biæ
    	}
    	else {
    		if (virtF.neighbours[i].hasPawn) {
    			if(((Pawn)virtF.neighbours[i].getComponent(0)).side!=thisP.side) {
	    			if (!virtF.neighbours[i].neighbours[i].hasPawn) {
	    				return nextCaptures (n+1, virtF.neighbours[i].neighbours[i], i);
	    			}
    			}
    		}
    		else {
    			return captureFurth(n, virtF.neighbours[i], i);
    		}
    	}
    	return -1;
    }
	
	int nextCaptures (int n, Field virtF, int i) {
		//int out=0;
		int out=n;												
		while (virtF!=Board.nullField&&!virtF.hasPawn) {
			int a = captureFound(n, i, virtF);
			if (out<a)
				out=a;
			virtF=virtF.neighbours[i];
		}
		return out;
	}
	
	void firstCapFurth(Field virtF, int i, int m) {
		while (virtF.neighbours[i]!=Board.nullField&&virtF.neighbours[i].neighbours[i]!=Board.nullField&&!virtF.neighbours[i].hasPawn) {
			virtF=virtF.neighbours[i];
		}
		if (virtF.neighbours[i].hasPawn&&virtF.neighbours[i]!=Board.nullField) {
			if (((Pawn)virtF.neighbours[i].getComponent(0)).side!=thisP.side) {
				Pawn jumpedOver = (Pawn)virtF.neighbours[i].getComponent(0);
				virtF=virtF.neighbours[i].neighbours[i];
				Field tmpF = virtF;
				int n=0;
				while (tmpF!=Board.nullField&&!tmpF.hasPawn) {
					int nn = captureFound(1, i, tmpF);
					if (nn>n)
						n=nn;
					
					tmpF=tmpF.neighbours[i];
				}
				if(n==m)
					while (virtF!=Board.nullField&&!virtF.hasPawn) {
						if (captureFound(1, i, virtF)==n) {
							virtF.setEnabled(true);
							virtF.jumpedOver=jumpedOver;
							virtF.lis = new FieldListener(virtF, thisB, m);
							virtF.addActionListener(virtF.lis);
						}
					virtF=virtF.neighbours[i];
					}
			}
		}
	}
}
