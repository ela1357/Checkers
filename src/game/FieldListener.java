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
			thisF.jumpedOver.pos = Board.nullField;
			thisF.jumpedOver = Board.nullPawn;
		}
		
		thisB.disableAllF();
		
		thisF.add(thisB.pickedPawn);
		thisB.pickedPawn.pos=thisF;
		thisB.pickedPawn.picked=false;
		thisF.hasPawn=true;
		
		if(n<2) {
			thisB.openAll(thisB.pickedPawn.side);
			
			
			for (int i=0; i<5; i++) {
				if (thisB.fields[0][i]==thisF&&thisB.pickedPawn.side) {
					thisB.pickedPawn.isKing=true;
				}
				if (thisB.fields[9][i]==thisF&&!thisB.pickedPawn.side) {
					thisB.pickedPawn.isKing=true;
				}
			}
			if (thisB.pickedPawn.side && thisB.bot!=null)
				thisB.bot.goForIt();
		}
		else {
			thisB.pickedPawn.setEnabled(false);
			thisB.pickedPawn.picked = true;
			((PawnListener)(thisB.pickedPawn.getActionListeners()[0])).findMoves();
			((PawnListener)(thisB.pickedPawn.getActionListeners()[0])).closeAll();
		}
		
		//thisB.canEnd();        
	}
	
	
	
}
