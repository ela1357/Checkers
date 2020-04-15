package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameListener implements ActionListener {

	Menu menu;
	
	public NewGameListener(Menu m) {
		menu = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//BoardGraf frame2 = new BoardGraf();
		//frame2.setVisible(true);
		Checkers checkers = new Checkers();
		menu.setVisible(false);
		menu.setDefaultCloseOperation(menu.EXIT_ON_CLOSE);
		menu.dispose();

	}

}
