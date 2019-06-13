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
		    gameView.lblMsg.setText("You clicked " + str);
		    Direction dir = null;
		    boolean moveDirectionClicked = true;
		    
		    switch(str)
		    {
			    case "Up":
				    dir = Direction.UP;
				    break;
			    case "Right":
				    dir = Direction.RIGHT;
				    break;
			    case "Down":
				    dir = Direction.DOWN;
				    break;
			    case "Left":
				    dir = Direction.LEFT;
				    break;
			    case "Hint":
			    	moveDirectionClicked = false;
			    	gameView.lblSol.setText(iGameManager.findSolution());
				    break;
		    }
		    if(moveDirectionClicked)
		    {
		    	Position characterPosition = iGameManager.getCharacterPosition();
			    if(iGameManager.positionExists(characterPosition, dir) && 
			    		iGameManager.canMoveThatWay(characterPosition, dir))
			    {
			    	iGameManager.move(dir);
			    	gameView.setField(iGameManager.getMatrix());
			    }
			    else gameView.lblMsg.setText("You cannot move that way.");
		    }
		    

		}
 	}
}

