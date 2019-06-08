package theGamePackage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import theGamePackage.IGameManager.Element;

//import theGamePackage.IGameManager.Direction;

public class GameView extends JFrame implements ActionListener{ 
	
	private int rowNumber;
	private int colNumber;
	private static final long serialVersionUID = 1L; // ???
    private static final int GAP = 1;
    private static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 24);
    public JLabel[][] grid;
	
	
	JButton btnUp = new JButton("Up");	
	JButton btnDown = new JButton("Down");	
	JButton btnLeft = new JButton("Left");	
	JButton btnRight = new JButton("Right");
	JButton btnHint = new JButton("Hint");
	
	JLabel lblMsg = new JLabel("", JLabel.LEFT);

	Thread varThread = null;
	
	
	public GameView(int rowNum, int colNum) {

		this.rowNumber = rowNum;
		this.colNumber = colNum;
		this.grid = new JLabel[rowNumber][colNumber];

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
				grid[row][col] = new JLabel("", SwingConstants.CENTER);
                grid[row][col].setFont(LABEL_FONT); // make it big
                grid[row][col].setOpaque(true);
                grid[row][col].setBackground(Color.WHITE);
                panMatrix.add(grid[row][col]);
			}
		}

		panMain.add(panBtns);
		panBtns.add(btnUp); btnUp.addActionListener(this);	
		panBtns.add(btnRight); btnRight.addActionListener(this);
		panBtns.add(btnDown); btnDown.addActionListener(this);	
		panBtns.add(btnLeft); btnLeft.addActionListener(this);		
		panBtns.add(btnHint); btnHint.addActionListener(this);	

		panMain.add(panMsg);
		panMsg.add(lblMsg = new JLabel("msgLabel"));
		
	}
	public void setField(Element[] dataElements) // better if I get the arr like strings and don't import the elements
	{
		for(int row = 0; row < grid.length; row++) 
		{
			for(int col = 0; col < grid[row].length; col++)
			{
				// System.out.println(grid.length*row+col); 
				switch (dataElements[grid.length*row+col]) { // it will get the elements one by one
					case CHARACTER: 
						grid[row][col].setText("CH");
						break;
					case OBSTACLE: 
						grid[row][col].setText("O");
						break;
					case BOX: 
						grid[row][col].setText("B");
						break;
					case TARGET: 
						grid[row][col].setText("T");
						break;
				default:
					break;
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e)     
	{
	    String str = e.getActionCommand();
	    lblMsg.setText("You clicked " + str);
	}
	
}
