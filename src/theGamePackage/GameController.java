package theGamePackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import theGamePackage.IGameManager.Direction;
//import theGamePackage.IGameManager.Element;


public class GameController {
	
	static IGameManager iGameManager = new GameManager();//rowNum, brObstacles
	

	public static void main(String[] args) {
		
		GameView gameView = new GameView();

		gameView.btnUp.addActionListener(new MoveUpAction());
		gameView.btnDown.addActionListener(new MoveDownAction());
		gameView.btnLeft.addActionListener(new MoveLeftAction());
		gameView.btnRight.addActionListener(new MoveRightAction());
		gameView.btnHint.addActionListener(new GetHintAction());
		
		iGameManager.setMatrix(5);
		boolean moved = iGameManager.move(Direction.UP); // move if possible
		if(!moved)
		{
			gameView.lblMsg.setText("You cannot move that way.");
		}
		
	}
	
	static class SetMessageAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			
		}
	}
	
	static class MoveUpAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			iGameManager.move(Direction.UP);
			
		}
	}
	static class MoveDownAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			iGameManager.move(Direction.DOWN);
			
		}
	}
	static class MoveLeftAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			iGameManager.move(Direction.LEFT);
			
		}
	}
	static class MoveRightAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			iGameManager.move(Direction.RIGHT);
			
		}
	}
	static class GetHintAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {

			iGameManager.findSolution();
		}
	}
	
	

}
