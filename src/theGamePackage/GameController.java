package theGamePackage;

import java.util.Scanner;

import theGamePackage.IGameManager.Direction;

//import theGamePackage.IGameManager.Element;

public class GameController {
	//Sokoban

	public static void main(String[] args) {
		
		GameView gameView = new GameView();
	
		
		Scanner in = new Scanner(System.in); 
		int rowNum = in.nextInt(); 
		int brObstacles = in.nextInt(); 
		
		IGameManager iGameManager = new GameManager(rowNum, brObstacles);
		iGameManager.getMatrix();
		
		while(true) {
			System.out.print("");
			String action = in.next();

			if(action.contentEquals("play")) {
				String moveDirection = "";
				Direction dir = null;
				while (!moveDirection.contentEquals("exit")) {
					moveDirection = in.next();
					switch(moveDirection) {
					  case "up":
					    dir = Direction.UP;
					  case "right":
						dir = Direction.RIGHT;
					  case "down":
						dir = Direction.DOWN;
					  case "left":
						dir = Direction.LEFT;
					    break;
					  default:
						continue;
					}
					iGameManager.move(dir);
					iGameManager.getMatrix();
				}
			}
			else if(action.contentEquals("hint")) {
				iGameManager.findSolution();
			}
			else if(action.equals("exit")) {
				break;
			}
		}
		
		in.close();
	}

}
