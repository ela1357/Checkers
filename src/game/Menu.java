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
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame implements ActionListener{

	Checkers checkers;
	JPanel panel;
	JLabel label;
	JButton newGame, settings, exit, authors;
	static Boolean language = true;
	public void language() {
		if(language == true) {
			label.setText("Warcaby");
			newGame.setText("Nowa Gra");
			settings.setText("Ustawienia");
			authors.setText("Twórcy");
			exit.setText("Wyjœcie z gry");
		}
		else if(language == false) {
			label.setText("Checkers");
			newGame.setText("New Game");
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
		panel.setBorder(new EmptyBorder(100, 300, 200, 300));
		panel.setBackground(new Color(226, 196, 173));
		
		Font font = new Font("Center baseline", Font.CENTER_BASELINE, 80);
		Font font2 = new Font("LucidaSans", Font.PLAIN, 30);
		label = new JLabel("");
		label.setFont(font);
		
		newGame = new RoundButton("");
		newGame.setFont(font2);
		settings = new RoundButton("");
		settings.setFont(font2);
		authors = new RoundButton("");
		authors.setFont(font2);
		exit = new RoundButton("");
		exit.setFont(font2);
		
		newGame.setMinimumSize(new Dimension(300, 70));
		newGame.setMaximumSize(new Dimension(300, 70));
		settings.setMinimumSize(new Dimension(300, 70));
		settings.setMaximumSize(new Dimension(300, 70));
		authors.setMinimumSize(new Dimension(300, 70));
		authors.setMaximumSize(new Dimension(300, 70));
		exit.setMinimumSize(new Dimension(300, 70));
		exit.setMaximumSize(new Dimension(300, 70));
		
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings.setAlignmentX(Component.CENTER_ALIGNMENT);
		authors.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		newGame.addActionListener(new NewGameListener(this));
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
		panel.add(newGame);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(settings);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(authors);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(exit);
	}

	public static void main(String[] args) {
		//Menu.setDefaultLookAndFeelDecorated(true);
		Menu frame = new Menu();
		frame.setVisible(true);
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
