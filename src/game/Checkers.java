package game;

import java.util.concurrent.Semaphore;

public class Checkers {
	Board board;
	boolean turn;   // 1 - bia³e,    0 - czarne
	
	public Checkers() {
		turn = true;
		startGame();
		move();
	}
	/*
	public static void main(String[] args) {
		Checkers game = new Checkers();
	}
	*/

	void startGame() {
		board = new Board();
		board.setVisible(true);
	}
	
	void move(){
		if(turn) {
			for (int i = 0; i<20; i++) {
				board.whites[i].setEnabled(true);
				board.blacks[i].setEnabled(false);
			}
		}
		else {
			for (int i = 0; i<20; i++) {
				board.whites[i].setEnabled(false);
				board.blacks[i].setEnabled(true);
			}
		}
		
	}
}
