package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldListener implements ActionListener {
	
	Field thisF;
	Board thisB;
	int n;
	

	public FieldListener(Field thisF, Board thisB, int n) {
		this.thisF=thisF;
		this.thisB=thisB;
		this.n=n;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		thisB.pickedPawn.pos.remove(thisB.pickedPawn);
		thisB.pickedPawn.pos.repaint();
		thisB.pickedPawn.pos.hasPawn=false;
		if (thisF.jumpedOver!=Board.nullPawn){
			thisF.jumpedOver.pos.hasPawn=false;
			thisF.jumpedOver.pos.remove(0);
			thisF.jumpedOver.pos.repaint();
			thisF.jumpedOver = Board.nullPawn;
		}
		
		thisB.disableAllF();
		
		thisF.add(thisB.pickedPawn);
		thisB.pickedPawn.pos=thisF;
		thisB.pickedPawn.picked=false;
		thisF.hasPawn=true;
		
		if(n<2) {
			openAll();
		}
		else {
			thisB.pickedPawn.setEnabled(false);
			thisB.pickedPawn.picked = true;
			((PawnListener)(thisB.pickedPawn.getActionListeners()[0])).findMoves();
			((PawnListener)(thisB.pickedPawn.getActionListeners()[0])).closeAll();
		}
	}
	
	void openAll() {
		for (int i=0; i<20; i++) {
			if (!thisB.pickedPawn.side)
				thisB.whites[i].setEnabled(true);
			else
				thisB.blacks[i].setEnabled(true);
		}
		thisB.pickedPawn.setEnabled(false);
	}
	
}
