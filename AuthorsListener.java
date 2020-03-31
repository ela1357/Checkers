package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorsListener implements ActionListener {

	Menu menu;
	
	public AuthorsListener(Menu m) {
		menu = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Authors frame2 = new Authors();
		frame2.setVisible(true);
		menu.setVisible(false);
		menu.setDefaultCloseOperation(menu.EXIT_ON_CLOSE);
		menu.dispose();

	}

	public static void main(String[] args) {
		
	}

}
