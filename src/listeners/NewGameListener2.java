package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bot.Tree;
import game.Checkers;
import game.Menu;

public class NewGameListener2 implements ActionListener {

	Menu menu;
	
	public NewGameListener2(Menu m) {
		menu = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//BoardGraf frame2 = new BoardGraf();
		//frame2.setVisible(true);
		Tree checkers = new Tree();
		menu.setVisible(false);
		menu.setDefaultCloseOperation(menu.EXIT_ON_CLOSE);
		menu.dispose();

	}

}
