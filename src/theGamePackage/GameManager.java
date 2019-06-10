package theGamePackage;

import java.util.ArrayList;
import java.util.Random;


public class GameManager implements IGameManager {

    MatrixModel matrixModel;
    //ArrayList<Element> matrix = new ArrayList<Element>();
    
    public GameManager(int rowNum, int colNum, int brObst) {
    	
    	matrixModel = new MatrixModel(rowNum, colNum, brObst);
		setMatrix();
	}
	@Override
	public Element[][] getMatrix() {
		
		return matrixModel.matrix;
    }
	
	@Override
	public Position getCharacterPosition() {
		
		return matrixModel.characterPosition;
	}

	@Override
	public boolean positionExists(Position myPos, Direction dir) {

		// Checks if the position I want to reach exists 
		if(myPos.row == 0 && dir == Direction.UP ||
			myPos.col == matrixModel.colNumber-1 && dir == Direction.RIGHT ||
			myPos.row == matrixModel.rowNumber-1 && dir == Direction.DOWN ||
			myPos.col == 0 && dir == Direction.LEFT) 
		{
			return false;
		}
		else return true;
	}
	public boolean canMoveThatWay(Position myPos, Direction dir)
	{
		Position newPos = getPosition(myPos, dir);
		if(matrixModel.matrix[newPos.row][newPos.col]!= Element.EMPTY) return false;
		else return true;
	}
	
	@Override
	public void setMatrix() {
		
		int rowRange = matrixModel.rowNumber-1; 
		int colRange = matrixModel.colNumber-1;
		
		// set character position
		int cursorR = getRandomNumberInRange(0, rowRange);
		int cursorC = getRandomNumberInRange(0, colRange);
		matrixModel.matrix[cursorR][cursorC] = Element.CHARACTER;
		matrixModel.characterPosition = new Position(cursorR, cursorC);
		
		// set target position
		do {
			cursorR = getRandomNumberInRange(0, rowRange);
			cursorC = getRandomNumberInRange(0, colRange);
		} while(matrixModel.matrix[cursorR][cursorC] != Element.EMPTY);
		matrixModel.matrix[cursorR][cursorC] = Element.TARGET; 
		matrixModel.targetPosition = new Position(cursorR, cursorC);
		
		// set obstacles' positions
		for (int i=0; i<matrixModel.brObstacles; i++) {
			cursorR = getRandomNumberInRange(0, rowRange);
			cursorC = getRandomNumberInRange(0, colRange);
			if (matrixModel.matrix[cursorR][cursorC] != Element.EMPTY) i--;
			else matrixModel.matrix[cursorR][cursorC] = Element.OBSTACLE;
	    }
	}
	
	@Override
	public void move(Direction dir) {
		Position oldPos = new Position(matrixModel.characterPosition.row, matrixModel.characterPosition.col);
		Position newPos = new Position(matrixModel.characterPosition.row, matrixModel.characterPosition.col);
		newPos = getPosition(newPos, dir);
		
		if(matrixModel.matrix[newPos.row][newPos.col] != Element.EMPTY) newPos = null; // if not empty you cannot move that way
		else 
		{
			matrixModel.matrix[oldPos.row][oldPos.col] = Element.EMPTY; // sets the old to empty //matrix[newPosition]
			matrixModel.matrix[newPos.row][newPos.col] = Element.CHARACTER;
			matrixModel.characterPosition = newPos;
		}
	}
	@Override
	public void findSolution() {
		// Get the reachable area -> characterReachablePath, boxReachablePath
		// Get the shortest path from the box to the target that can be implemented (can be pushed through it)
		// methods getBoxPath and get1PushCharacterPath will be needed
		/* I should check if there is an empty square on the 
		   opposite side of the turns/ the position opposite of the moving position of the box
		   so that the character can be there to push */
		// check if that empty square exists in the characterReachablePath
		
		// Get the shortest path from the character to the pushingPosition
		/* I'll need to get the next element from the boxPath and set the
		   pushingPosition to the opposite side of that nextElement  */
		
		
		
		// count character moves
		
		//if(matrix[getPosition(direction)] != Element.TARGET) 
		
		ArrayList<Integer> reachableArea  = new ArrayList<Integer>();
		//reachableArea = getReachableArea(characterPosition, reachableArea);

		reachableArea.forEach((n) -> System.out.println(n)); 
	}

	
	/* PRIVATE METHODS */
	
	private static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
	}
	
	private  Position getPosition(Position myPos, Direction dir) {
		Position newPos = new Position(myPos.row, myPos.col);

		if(positionExists(myPos, dir)) {
			// gets the position I want to reach
			switch(dir) {
			  case UP:
				  newPos.row--;
				  break;
			  case RIGHT:
				  newPos.col++;
				  break;
			  case DOWN:
				  newPos.row++;
				  break;
			  case LEFT:
				  newPos.col--;
				  break;
			}
			return newPos;
		}
		else return null;
	}
//	private ArrayList<Integer> getReachableArea(int currentPos, ArrayList<Integer> reachableArea){
//		// should get the whole area that can be reached from the given position
//		// currently throws stack overflow
//		if(!reachableArea.contains(currentPos)) reachableArea.add(currentPos);
//		
//		for (Direction dir : Direction.values()) 
//		{ 
////			if(getPosition(currentPos, dir)!=-1 && matrix[getPosition(currentPos, dir)] == Element.EMPTY) // not first row && upper element is empty
////				getReachableArea(getPosition(currentPos, dir), reachableArea);
//			//else continue;
//		}
//		return reachableArea;
//	}
	
}
