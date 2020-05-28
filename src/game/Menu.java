package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import buttons.RoundButton;
import listeners.AuthorsListener;
import listeners.NewGameListener;
import listeners.NewGameListener2;
import listeners.SettingsListener;

public class Menu extends JFrame implements ActionListener{

	Checkers checkers;
	JPanel panel;
	JLabel label;
	JButton newGameS, newGameM, settings, exit, authors;
	static Boolean language = true;
	public void language() {
		if(language == true) {
			label.setText("Warcaby");
			newGameM.setText("Tryb Wieloosobowy");
			newGameS.setText("Tryb Jednoosobowy");
			settings.setText("Ustawienia");
			authors.setText("Twórcy");
			exit.setText("Wyjœcie z gry");
		}
		else if(language == false) {
			label.setText("Checkers");
			newGameM.setText("Multiplayer");
			newGameS.setText("Singleplayer");
			settings.setText("Settings");
			authors.setText("Authors");
			exit.setText("Exit");
		}
	}
	
	public Menu() throws HeadlessException {
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1100, 900);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(panel);
		panel.setBorder(new EmptyBorder(100, 300, 100, 300));
		panel.setBackground(new Color(229, 169, 126));
		
		Font font = new Font("Center baseline", Font.CENTER_BASELINE, 80);
		Font font2 = new Font("LucidaSans", Font.PLAIN, 30);
		label = new JLabel("");
		label.setFont(font);
		
		newGameM = new RoundButton("");
		newGameM.setFont(font2);
		newGameS = new RoundButton("");
		newGameS.setFont(font2);
		settings = new RoundButton("");
		settings.setFont(font2);
		authors = new RoundButton("");
		authors.setFont(font2);
		exit = new RoundButton("");
		exit.setFont(font2);
		
		newGameM.setMinimumSize(new Dimension(300, 70));
		newGameM.setMaximumSize(new Dimension(300, 70));
		newGameS.setMinimumSize(new Dimension(300, 70));
		newGameS.setMaximumSize(new Dimension(300, 70));
		settings.setMinimumSize(new Dimension(300, 70));
		settings.setMaximumSize(new Dimension(300, 70));
		authors.setMinimumSize(new Dimension(300, 70));
		authors.setMaximumSize(new Dimension(300, 70));
		exit.setMinimumSize(new Dimension(300, 70));
		exit.setMaximumSize(new Dimension(300, 70));
		
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		newGameM.setAlignmentX(Component.CENTER_ALIGNMENT);
		newGameS.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings.setAlignmentX(Component.CENTER_ALIGNMENT);
		authors.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		newGameM.addActionListener(new NewGameListener(this));
		newGameS.addActionListener(new NewGameListener2(this));	
		settings.addActionListener(new SettingsListener(this));
		authors.addActionListener(new AuthorsListener(this));
		
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
		
		language();
		
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(0, 90)));
		panel.add(newGameM);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(newGameS);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(settings);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(authors);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(exit);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				//Menu.setDefaultLookAndFeelDecorated(true);
				Menu frame = new Menu();
				frame.setVisible(true);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//BoardGraf frame2 = new BoardGraf();
		//frame2.setVisible(true);
		checkers = new Checkers();
		this.setVisible(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.dispose();

	}

}