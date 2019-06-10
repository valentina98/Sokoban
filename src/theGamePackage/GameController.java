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
		    Boolean moved = null; // class boolean because it may be null
		    
		    switch(str)
		    {
			    case "Up":
				    moved = iGameManager.move(Direction.UP);
				    
				    break;
			    case "Right":
				    moved = iGameManager.move(Direction.RIGHT);
				    break;
			    case "Down":
				    moved = iGameManager.move(Direction.DOWN);
				    break;
			    case "Left":
				    moved = iGameManager.move(Direction.LEFT);
				    break;
			    case "Hint":
			    	//iGameManager.findSolution();
				    break;
		    }
		    if(moved == null) moved = false;
		    if(moved)
			{
				gameView.setField(iGameManager.getMatrix());
			}
			else
			{
				//gameView.lblMsg.setText("You cannot move that way.");
				System.out.println("cannot move that way");
			}
			
		}
 	}
}

