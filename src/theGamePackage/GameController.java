package theGamePackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import theGamePackage.IGameManager.Direction;
//import theGamePackage.IGameManager.Element;


public class GameController {
	
	static IGameManager iGameManager = new GameManager(5,5,5);//rowNum, 
	static GameView gameView = new GameView(5,5);

	public static void main(String[] args) {
		
		gameView.btnUp.addActionListener(new myActionListener());
		gameView.btnDown.addActionListener(new myActionListener());
		gameView.btnLeft.addActionListener(new myActionListener());
		gameView.btnRight.addActionListener(new myActionListener());
		gameView.btnHint.addActionListener(new myActionListener());

		gameView.setField(iGameManager.getMatrix());
		
		//gameView.grid[1][1].setText("asd");
		
		boolean moved = iGameManager.move(Direction.UP); // move if possible
		if(!moved)
		{
			gameView.lblMsg.setText("You cannot move that way.");
		}
		
	}

	static class myActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String str = e.getActionCommand();
		    System.out.println("You clicked " + str);
		    
		    if(str.equals("Up")) {
		    	iGameManager.move(Direction.UP);
				gameView.setField(iGameManager.getMatrix());
		    }
		    if(str.equals("Right"))
		    {
		    	iGameManager.move(Direction.RIGHT);
				gameView.setField(iGameManager.getMatrix());
		    }
		    if(str.equals("Down"))
		    {
		    	iGameManager.move(Direction.DOWN);
				gameView.setField(iGameManager.getMatrix());
		    }
		    if(str.equals("Left"))
		    {
		    	iGameManager.move(Direction.LEFT);
				gameView.setField(iGameManager.getMatrix());
		    }
		    if(str.equals("Hint"))
		    {
		    	//iGameManager.findSolution();
		    
		    }
			
		}
 	}
}

