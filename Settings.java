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

public class Settings extends JFrame implements ActionListener{

	JPanel panel;
	JLabel label;
	JButton pl, eng, back;
	Boolean language = true;
	public void language() {
		if(language == true && Menu.language == true) {
			label.setText("Jêzyk:");
			pl.setText("Polski");
			eng.setText("Angielski");
			back.setText("Wróæ");
		}
		else if(language == false || Menu.language == false) {
			label.setText("Language:");
			pl.setText("Polish");
			eng.setText("English");
			back.setText("Back");
		}
	}
	
	public Settings() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1100, 900);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(panel);
		panel.setBorder(new EmptyBorder(100, 300, 200, 300));
		panel.setBackground(new Color(226, 196, 173));
		
		Font font = new Font("LucidaSans", Font.PLAIN, 20);
		Font font2 = new Font("Hanging Baseline", Font.HANGING_BASELINE, 70);
		Font font3 = new Font("LucidaSans", Font.PLAIN, 30);
		
		label = new JLabel("");
		label.setFont(font2);
		
		pl = new RoundButton2("");
		pl.setFont(font);
		eng = new RoundButton2("");
		eng.setFont(font);
		back = new RoundButton("");
		back.setFont(font3);
		
		pl.setMinimumSize(new Dimension(200, 50));
		pl.setMaximumSize(new Dimension(200, 50));
		eng.setMinimumSize(new Dimension(200, 50));
		eng.setMaximumSize(new Dimension(200, 50));
		back.setMinimumSize(new Dimension(300, 70));
		back.setMaximumSize(new Dimension(300, 70));
		
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pl.setAlignmentX(Component.CENTER_ALIGNMENT);
		eng.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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

		back.addActionListener(this);

		language();
		
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(pl);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(eng);
		panel.add(Box.createRigidArea(new Dimension(0, 100)));
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
	

	public static void main(String[] args) {
		Settings frame = new Settings();
		frame.setVisible(true);
	}

}
