package theGamePackage;

import java.util.ArrayList;
import java.util.Random;


public class GameManager implements IGameManager {

    public int rowNumber;
    public int colNumber;
    public int brObstacles;
    public Element[][] matrix;  // private
    private Position characterPosition;
    private Position targetPosition; /////////////////
    
    
    //ArrayList<Element> matrix = new ArrayList<Element>();
    

    public GameManager(int rowNum, int colNum, int brObst) {
		this.rowNumber = rowNum;
		this.colNumber = colNum;
		this.brObstacles = brObst;
		
		matrix = new Element[rowNumber][colNumber];
		for (int i=0; i<rowNumber; i++)
		{
			for(int j=0; j<colNumber; j++)
			{
				matrix[i][j] = Element.EMPTY;
			}
		}
		setMatrix();
		 //i can switch the order of the loop and setMatrix
	}
	@Override
	public Element[][] getMatrix() {
		
		return this.matrix;
    }
	
	@Override
	public Position getCharacterPosition() {
		
		return this.characterPosition;
	}

	@Override
	public boolean positionExists(Position myPos, Direction dir) {

		// Checks if the position I want to reach exists 
		if(myPos.row == 0 && dir == Direction.UP ||
			myPos.col == colNumber-1 && dir == Direction.RIGHT ||
			myPos.row == rowNumber-1 && dir == Direction.DOWN ||
			myPos.col == 0 && dir == Direction.LEFT) 
		{
			System.out.println("cannot move that way");
			return false;
		}
		else return true;
	}
	public boolean canMoveThatWay(Position myPos, Direction dir)
	{
		Position newPos = getPosition(myPos, dir);
		if( matrix[newPos.row][newPos.col]!= Element.EMPTY) return false;
		else return true;
	}
	
	@Override
	public void setMatrix() {
		
		// set character position
		int cursorR = getRandomNumberInRange(0, rowNumber-1);
		int cursorC = getRandomNumberInRange(0, colNumber-1);
		matrix[cursorR][cursorC] = Element.CHARACTER;
		characterPosition = new Position(cursorR, cursorC);
		
		// set target position
		do {
			cursorR = getRandomNumberInRange(0, rowNumber-1);
			cursorC = getRandomNumberInRange(0, colNumber-1);
		} while(matrix[cursorR][cursorC] != Element.EMPTY);
		matrix[cursorR][cursorC] = Element.TARGET; 
		//targetPosition = cursor;
		
		// set obstacles' positions
		for (int i=0; i<brObstacles; i++) {
			cursorR = getRandomNumberInRange(0, rowNumber-1);
			cursorC = getRandomNumberInRange(0, colNumber-1);
			if (matrix[cursorR][cursorC] != Element.EMPTY) i--;
			else matrix[cursorR][cursorC] = Element.OBSTACLE;
	    }
	}

	
	
	@Override
	public void move(Direction dir) {
		Position oldPos = new Position(characterPosition.row, characterPosition.col);
		Position newPos = new Position(characterPosition.row, characterPosition.col);
		newPos = getPosition(newPos, dir);
		
		if(matrix[newPos.row][newPos.col] != Element.EMPTY) newPos = null; // if not empty you cannot move that way
		else 
		{
			matrix[oldPos.row][oldPos.col] = Element.EMPTY; // sets the old to empty //matrix[newPosition]
			matrix[newPos.row][newPos.col] = Element.CHARACTER;
			characterPosition = newPos;
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
