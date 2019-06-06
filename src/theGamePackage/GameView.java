package theGamePackage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//import theGamePackage.IGameManager.Direction;
//
//import GameView.StartAction;
//import GameView.StopAction;
//import GameView.ourThread;

public class GameView extends JFrame{ 
	
	private static final long serialVersionUID = 1L; // ???
    private static final int GAP = 1;
    private static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 24);
    public JLabel[][] grid = new JLabel[5][5];
	
	
	JButton btnUp = new JButton("Up");	
	JButton btnDown = new JButton("Down");	
	JButton btnLeft = new JButton("Left");	
	JButton btnRight = new JButton("Right");
	JButton btnHint = new JButton("Hint");
	
	JLabel lblMsg = new JLabel("", JLabel.LEFT);

	Thread varThread = null;
	
	
	public GameView() {

		JPanel panMain = new JPanel(new GridLayout(3, 0));
		JPanel panMatrix = new JPanel(new GridLayout(5, 5, GAP, GAP));
		JPanel panBtns = new JPanel(new FlowLayout());
		JPanel panMsg = new JPanel(new FlowLayout());
		
		this.setVisible(true);
		this.setSize(500, 500);
        this.setTitle("Sokoban");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.add(panMain);
		panMain.setVisible(true);
		panMain.setSize(400, 400);

		panMain.add(panMatrix);
		panMatrix.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		panMatrix.setBackground(Color.BLACK);
		for(int row = 0; row < grid.length; row++) 
		{
			for(int col = 0; col < grid[row].length; col++)
			{
				grid[row][col] = new JLabel("     ", SwingConstants.CENTER);
                grid[row][col].setFont(LABEL_FONT); // make it big
                grid[row][col].setOpaque(true);
                grid[row][col].setBackground(Color.WHITE);
                panMatrix.add(grid[row][col]);
			}
				
		}

		panMain.add(panBtns);
		panBtns.add(btnUp);
		panBtns.add(btnDown);
		panBtns.add(btnLeft);
		panBtns.add(btnRight);
		panBtns.add(btnHint);


		panMain.add(panMsg);
		panMsg.add(lblMsg = new JLabel("msgLabel"));
		
		
		
	}
	
	
	
	
	
//	class StopAction implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			if (varThread!=null) {
//				varThread.stop();
//				varThread = null;
//				//label.setText(arg0);
//			}
//		}
//	}
	
	class ourThread extends Thread {
		
//		Random ran = new Random();
//		int temp;
		
		public void run() {
//			try {
//				while(true) {
//				field.setText("asdf");
//				temp = ran.nextInt(101);
//				field.setText("" + temp);
//				sleep(50);
//				}
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
