package bot;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import utility.Node;

public class Tree implements Runnable{
	Node<VBoard> root = new Node<VBoard>(new VBoard());
	Node<VBoard> temp = root;
	Board game;
	
	public static final int iterations = 5;
	public static final double kingValue = 2.1;
	
	public Tree(){
		game = new Board(this);
		game.setVisible(true);
	}
	
	ArrayList<Node<VBoard>> genChildren (){
		ArrayList<Node<VBoard>> out = new ArrayList<Node<VBoard>>();
		ArrayList<VBoard> tmp = temp.getData().createMoves();
		
		for (VBoard bo:tmp) {
			out.add(new Node<VBoard>(bo));
		}
		
		return out;
	}

	void generator(int ctr) {
		if (ctr>=iterations)
			return;
		
		temp.addChildren(genChildren());
		List<Node<VBoard>> actual = temp.getChildren();
		for(Node<VBoard> node : actual) {
			temp=node;
			generator (ctr+1);
		}
		temp=temp.getParent();
	}
	
	void generate() {
		temp = root;
		generator(0);
		temp = root;
	}
	
	VBoard findGoodMove() {
		VBoard out = null;
		double value = 0;
		
		temp = root;
		List<Node<VBoard>> inp = findLasts(0);
		for (Node<VBoard> i : inp) {
			if (i.getData().whiteCtr==0 && i.getData().whiteKingCtr==0) {
				return i.getData();
			}
			else {
				double tmp = ((double)i.getData().blackCtr + kingValue * (double)i.getData().blackKingCtr)/((double)i.getData().whiteCtr + kingValue * (double)i.getData().whiteKingCtr);
				if (value<=tmp) {
					value = tmp;
					out = i.getData();
				}
			}
		}
		return out;
	}
	
	List<Node<VBoard>> findLasts (int ctr){
		ArrayList<Node<VBoard>> out = new ArrayList<Node<VBoard>>();
		if (ctr>=iterations) {
			out.add(temp);
			return out;
		}
		
		List<Node<VBoard>> actual = temp.getChildren();
		for(Node<VBoard> node : actual) {
			temp=node;
			out.addAll(findLasts (ctr+1));
		}
		temp=temp.getParent();
		return out;
	}
	
	public void goForIt() {
		root= new Node<VBoard>(game.giveSitToBot());
		this.run();
	}

	@Override
	public void run() {
		generate();
		
		VBoard move = findGoodMove();
		Node<VBoard> nextMove = root.getChildren().get(0);
		temp = root;
		
		for (Node<VBoard> child : root.getChildren()) {
			if (child.hasMove(move))
				nextMove = child;
		}
		
		//nextMove.getData().showBoard();
		game.setSitFromBot(nextMove.getData());
	}
	
	

	/*public static void main(String[] args) {
		Tree t1 = new Tree();
		
	}*/
}
