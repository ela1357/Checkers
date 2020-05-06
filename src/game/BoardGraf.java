package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BoardGraf extends JFrame implements ActionListener{

	JPanel panel, center;
	JButton back;
	
	public BoardGraf() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1100, 900);
		
		panel = new JPanel(new GridBagLayout());
		center = new JPanel(new GridLayout(10, 10));
		center.setPreferredSize(new Dimension(800, 800));
		center.setBorder(new LineBorder(Color.GRAY));
		
		panel.setBackground(new Color(226, 196, 173));
		center.setBackground(Color.white);
		
		JButton[] button = new JButton[100];
		for(int i = 0; i < 100; i++) {
			button[i] = new JButton(String.valueOf(i));
			center.add(button[i]);
		}
		
		back = new RoundButton3("Wróæ");
		GridBagConstraints b = new GridBagConstraints();
		GridBagConstraints c = new GridBagConstraints();
		b.anchor = GridBagConstraints.LAST_LINE_START;
		c.anchor = GridBagConstraints.CENTER;
		//b.ipady = 0;
		//b.weightx = 0.1;
		b.fill = GridBagConstraints.HORIZONTAL;
		b.insets = new Insets(500,0,0,75);
		panel.add(back, b);
		panel.add(center, c);
		
		
		back.addActionListener(this);
		
		this.add(panel);
		
		
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
		BoardGraf frame = new BoardGraf();
		frame.setVisible(true);
	}

	
	
	
	
}



