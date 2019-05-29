package theGamePackage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	
//	DefaultListModel field = new DefaultListModel();
//	field.addElement("Online People");
//	JList list = new JList( field );

	Thread varThread = null;
	JLabel label = new JLabel();
	public GameView() {
		this.setVisible(true);
		this.setSize(500, 500);
        this.setTitle("Sokoban");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		//this.setResizable(false);

		this.add(label);
		this.add(btnUp);
		this.add(btnDown);
		this.add(btnLeft);
		this.add(btnRight);
		this.add(btnHint);
		
		btnUp.addActionListener(new MoveUpAction());
		btnDown.addActionListener(new MoveDownAction());
		btnLeft.addActionListener(new MoveLeftAction());
		btnRight.addActionListener(new MoveRightAction());
		btnHint.addActionListener(new GetHintAction());
	}
	
	class MoveUpAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (varThread==null) {
				varThread = new ourThread();
				varThread.start();
			}
		}
	}
	class MoveDownAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (varThread==null) {
				varThread = new ourThread();
				varThread.start();
			}
		}
	}
	class MoveLeftAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (varThread==null) {
				varThread = new ourThread();
				varThread.start();
			}
		}
	}
	class MoveRightAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (varThread==null) {
				varThread = new ourThread();
				varThread.start();
			}
		}
	}
	class GetHintAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (varThread==null) {
				varThread = new ourThread();
				varThread.start();
			}
		}
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
