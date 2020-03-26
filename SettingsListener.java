package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsListener implements ActionListener {

	Menu menu;
	
	public SettingsListener(Menu m) {
		menu = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Settings frame2 = new Settings();
		frame2.setVisible(true);
		menu.setVisible(false);
		menu.setDefaultCloseOperation(menu.EXIT_ON_CLOSE);
		menu.dispose();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
