package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Authors extends JFrame implements ActionListener{

	JPanel panel;
	JLabel label1, label2, label3, label4;
	JButton back;
	Boolean language = Menu.language;
	public void language() {
		if(language == true) {
			label1.setText("Twórcami gry s¹:");
			label3.setText("oraz");
			back.setText("Wróæ");
		}
		else if(language == false) {
			label1.setText("The authors of the game are:");
			label3.setText("and");
			back.setText("Back");
		}
	}
	
	public Authors() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1100, 900);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(panel);
		panel.setBorder(new EmptyBorder(200, 150, 200, 150));
		panel.setBackground(new Color(226, 196, 173));
		
		Font font = new Font("HangingBaseline", Font.HANGING_BASELINE, 45);
		Font font2 = new Font("RomanBaseline", Font.CENTER_BASELINE, 50);
		Font font3 = new Font("LucidaSans", Font.PLAIN, 25);
		Font font4 = new Font("HangingBaseline", Font.HANGING_BASELINE, 30);
		
		label1 = new JLabel("");
		label1.setFont(font2);
		label2 = new JLabel("El¿bieta Lubañska");
		label2.setFont(font);
		label3 = new JLabel("");
		label3.setFont(font4);
		label4 = new JLabel("Kordian Makulski");
		label4.setFont(font);
		
		back = new RoundButton2("");
		back.setFont(font3);
		back.setMinimumSize(new Dimension(150, 50));
		back.setMaximumSize(new Dimension(150, 50));
		
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		label3.setAlignmentX(Component.CENTER_ALIGNMENT);
		label4.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		back.addActionListener(this);
		
		language();
		
		panel.add(label1);
		panel.add(Box.createRigidArea(new Dimension(0, 50)));
		panel.add(label2);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(label3);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(label4);
		panel.add(Box.createRigidArea(new Dimension(0, 120)));
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
