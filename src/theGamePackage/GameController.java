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
		
		//iGameManager.move(Direction.UP); // move if possible
		
	}

	static class myActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String str = e.getActionCommand();
		    System.out.println("You clicked " + str);
		    boolean moved;
		    
		    if(str.equals("Up")) {
			    moved = iGameManager.move(Direction.UP);
				if(!moved)
				{
					gameView.lblMsg.setText("You cannot move that way.");
				}
				else 
				{
					gameView.setField(iGameManager.getMatrix());
				}
		    }
		    else if(str == "Right")
		    {
		    	moved = iGameManager.move(Direction.RIGHT);
				if(!moved)
				{
					gameView.lblMsg.setText("You cannot move that way.");
				}
				else 
				{
					gameView.setField(iGameManager.getMatrix());
				}
		    }
		    else if(str.equals("Down"))
		    {
		    	moved = iGameManager.move(Direction.DOWN);
				if(!moved)
				{
					gameView.lblMsg.setText("You cannot move that way.");
				}
				else 
				{
					gameView.setField(iGameManager.getMatrix());
				}
		    }
		    else if(str.equals("Left"))
		    {
		    	moved = iGameManager.move(Direction.LEFT);
				if(!moved)
				{
					gameView.lblMsg.setText("You cannot move that way.");
				}
				else 
				{
					gameView.setField(iGameManager.getMatrix());
				}
		    }
		    else if(str.equals("Hint"))
		    {
		    	//iGameManager.findSolution();
		    
		    }
			
		}
 	}
}

