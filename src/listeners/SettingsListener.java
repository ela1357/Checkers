package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Menu;
import game.Settings;

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

}
