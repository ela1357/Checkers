package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import buttons.RoundButton2;

public class Result extends JFrame implements ActionListener{

	JPanel panel;
	JLabel label1, label2, label3;
	JButton back, exit;
	Boolean language = Menu.language;
	Boolean result = Board.res;
	Board board;
	
	public Result(Board b) {
		board = b;
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(400, 350, 600, 400);
		setMinimumSize(new Dimension(600, 400));
		
		File imageFile = new File("crown.png");
        BufferedImage image = null;
 		try {
 			image = ImageIO.read(imageFile);
 		} catch (IOException e) {
 			System.err.println("Blad odczytu obrazka");
 			e.printStackTrace();
 		}
 		BufferedImage resized = resize(image, 80, 80);
 		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(panel);
		panel.setBorder(new EmptyBorder(10, 150, 200, 150));
		panel.setBackground(new Color(229, 169, 126));
		
		JLabel label = new JLabel();
        label.setIcon(new ImageIcon(resized));
        
		Font font = new Font("HangingBaseline", Font.HANGING_BASELINE, 33);
		Font font2 = new Font("RomanBaseline", Font.CENTER_BASELINE, 33);
		Font font3 = new Font("LucidaSans", Font.PLAIN, 16);
		
		label1 = new JLabel("");
		label1.setFont(font2);
		label2 = new JLabel("");
		label2.setFont(font);
		
		back = new RoundButton2("");
		back.setFont(font3);
		back.setMinimumSize(new Dimension(150, 50));
		back.setMaximumSize(new Dimension(150, 50));
		exit = new RoundButton2("");
		exit.setFont(font3);
		exit.setMinimumSize(new Dimension(150, 50));
		exit.setMaximumSize(new Dimension(150, 50));
		
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		back.addActionListener(this);
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
		
		language();
		
		panel.add(label1);
		panel.add(label);
		panel.add(label2);
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		panel.add(back);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(exit);
	}
	
	public void language() {
		if(language == true) {
			label1.setText("A zwyciêzc¹ s¹:");
			if(result == true)
				label2.setText("JASNE PIONKI!");
			else if(result == false)
				label2.setText("CIEMNE PIONKI!");
			back.setText("Powrót do menu");
			exit.setText("Wyjœcie z gry");
		}
		else if(language == false) {
			label1.setText("And the winner is:");
			if(result == true)
				label2.setText("BRIGHT PAWNS!");
			else if(result == false)
				label2.setText("DARK PAWNS!");
			back.setText("Back to menu");
			exit.setText("Exit");
		}
	}
	
	public Result() throws HeadlessException {
		
	}
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Menu frame2 = new Menu();
		frame2.setVisible(true);
		//this.setVisible(false);
		//this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.dispose();
		board.dispose();
	}
	
	/*public static void main(String[] args) {
		Result frame = new Result();
		frame.setVisible(true);
	}*/

}
