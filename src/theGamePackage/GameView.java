package theGamePackage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import theGamePackage.IGameManager.Direction;
//
//import GameView.StartAction;
//import GameView.StopAction;
//import GameView.ourThread;

public class GameView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // ???
	
	
	JButton btnUp = new JButton("Up");	
	JButton btnDown = new JButton("Down");	
	JButton btnLeft = new JButton("Left");	
	JButton btnRight = new JButton("Right");
	JButton btnHint = new JButton("Hint");
	
	JLabel lblMsg = new JLabel("", JLabel.LEFT);
	
//	DefaultListModel field = new DefaultListModel();
//	field.addElement("Online People");
//	JList list = new JList( field );

	Thread varThread = null;
	//JLabel label = new JLabel();
	
	
	public GameView() {
		
		JPanel panMatrix = new JPanel();
		JPanel panBtns = new JPanel();
		JPanel panMsg = new JPanel();
		
		this.setVisible(true);
		this.setSize(500, 500);
        this.setTitle("Sokoban");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		//this.setResizable(false);

//		this.add(label);
		
		panMatrix.setLayout(new FlowLayout()); 
		/////////////////
		add(panMatrix);
	    
		panBtns.setLayout(new FlowLayout());
		panBtns.add(btnUp);
		panBtns.add(btnDown);
		panBtns.add(btnLeft);
		panBtns.add(btnRight);
		panBtns.add(btnHint);
		add(panBtns);
		
		panMsg.setLayout(new FlowLayout()); 
		panMsg.add(lblMsg = new JLabel("msgLabel"));
		add(panMsg);
		
		
		
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
