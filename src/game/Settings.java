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

import buttons.RoundButton;
import buttons.RoundButton2;

public class Settings extends JFrame implements ActionListener{

	JPanel panel;
	JLabel label, label1;
	JButton pl, eng, back, classic, random;
	Boolean language = true;
	static Boolean color = true;
	public void language() {
		if(language == true && Menu.language == true) {
			label.setText("Jêzyk:");
			pl.setText("Polski");
			eng.setText("Angielski");
			back.setText("Wróæ");
			label1.setText("Kolor:");
			classic.setText("Klasyczny");
			random.setText("Losowy");
		}
		else if(language == false || Menu.language == false) {
			label.setText("Language:");
			pl.setText("Polish");
			eng.setText("English");
			back.setText("Back");
			label1.setText("Color:");
			classic.setText("Classic");
			random.setText("Random");
		}
	}
	
	public Settings() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1100, 900);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(panel);
		panel.setBorder(new EmptyBorder(100, 300, 300, 300));
		panel.setBackground(new Color(229, 169, 126));
		
		Font font = new Font("LucidaSans", Font.PLAIN, 20);
		Font font2 = new Font("Hanging Baseline", Font.HANGING_BASELINE, 70);
		Font font3 = new Font("LucidaSans", Font.PLAIN, 30);
		
		label = new JLabel("");
		label.setFont(font2);
		label1 = new JLabel("");
		label1.setFont(font2);
		
		pl = new RoundButton2("");
		pl.setFont(font);
		eng = new RoundButton2("");
		eng.setFont(font);
		classic = new RoundButton2("");
		classic.setFont(font);
		random = new RoundButton2("");
		random.setFont(font);
		back = new RoundButton("");
		back.setFont(font3);
		
		pl.setMinimumSize(new Dimension(150, 50));
		pl.setMaximumSize(new Dimension(150, 50));
		eng.setMinimumSize(new Dimension(150, 50));
		eng.setMaximumSize(new Dimension(150, 50));
		classic.setMinimumSize(new Dimension(150, 50));
		classic.setMaximumSize(new Dimension(150, 50));
		random.setMinimumSize(new Dimension(150, 50));
		random.setMaximumSize(new Dimension(150, 50));
		back.setMinimumSize(new Dimension(300, 70));
		back.setMaximumSize(new Dimension(300, 70));
		
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pl.setAlignmentX(Component.CENTER_ALIGNMENT);
		eng.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		label1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		classic.setAlignmentX(Component.CENTER_ALIGNMENT);
		random.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		eng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	language = false;
            	Menu.language = false;
            	language();
            }
        });
		
		pl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	language = true;
            	Menu.language = true;
            	language();
            }
        });
		
		classic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	color = true;
            }
        });
		
		random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	color = false;
            }
        });

		back.addActionListener(this);

		language();
		
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(pl);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(eng);
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(label1);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(classic);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(random);
		panel.add(Box.createRigidArea(new Dimension(0, 90)));
		panel.add(back);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Menu frame2 = new Menu();
		frame2.setVisible(true);
		this.setVisible(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.dispose();
	}
	
}
