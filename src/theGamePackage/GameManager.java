package theGamePackage;

import java.util.ArrayList;
import java.util.Random;


public class GameManager implements IGameManager {

    public int rowNumber;
    public int colNumber;
    public int brObstacles;
    public Element[] matrix; // used 1D array, scroll down for the private methods
    private int characterPosition;
    private int targetPosition; /////////////////
    
    //ArrayList<Element> matrix = new ArrayList<Element>();
    

    public GameManager(int rowNum, int colNum, int brObst) {
		this.rowNumber = rowNum;
		this.colNumber = colNum;
		this.brObstacles = brObst;
		
		matrix = new Element[rowNumber*colNumber];
		for (int i=0; i<rowNumber*colNumber; i++)
		{
			matrix[i] = Element.EMPTY;
		}
		setMatrix();
		 //i can switch the order of the loop and setMatrix
	}
	@Override
	public Element[] getMatrix() {
		
		return this.matrix;
    }
	@Override
	public void setMatrix() {
		
		// set character position
		int cursor = getRandomNumberInRange(0, rowNumber*colNumber-1);
		matrix[cursor] = Element.CHARACTER;
		characterPosition = cursor;
		
		// set target position
		do {
			cursor = getRandomNumberInRange(0, rowNumber*colNumber-1);
		} while(matrix[cursor] != Element.EMPTY);
		matrix[cursor] = Element.TARGET; 
		targetPosition = cursor;
		
		// set obstacles' positions
		for (int i=0; i<brObstacles; i++) {
			cursor = getRandomNumberInRange(0, rowNumber*colNumber-1); 
			if (matrix[cursor] != Element.EMPTY) i--;
			else matrix[cursor] = Element.OBSTACLE;
	    }
	}

	@Override
	public boolean move(Direction dir) {
		int newPosition = getPosition(characterPosition, dir); // if getPosition returns -1, the position doesn't exist
		if(newPosition == -1) return false;
		if(matrix[newPosition] != Element.EMPTY) newPosition = -1; // if not empty you cannot move that way
		if(newPosition == -1) return false;
		else 
		{
			matrix[characterPosition] = Element.EMPTY; // sets the old to empty //matrix[newPosition]
			matrix[newPosition] = Element.CHARACTER;
			characterPosition = newPosition;
			return true;
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
		reachableArea = getReachableArea(characterPosition, reachableArea);

		reachableArea.forEach((n) -> System.out.println(n)); 
	}

	
	/* PRIVATE METHODS */
	
	private static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
	}
	
	private  int getPosition(int pos, Direction dir) {
		if(positionExists(pos, dir)) {
			// gets the position I want to reach
			switch(dir) {
			  case UP:
				  return pos-colNumber;
			  case RIGHT:
				  return pos+1;
			  case DOWN:
				  return pos+colNumber;
			  case LEFT:
				  return pos-1;
			default: 
				  return -1;
			}
		}
		else return -1;
	}
	private boolean positionExists(int pos, Direction dir) {
//		// tests
//		System.out.println("up: pos < " + colNumber);
//		System.out.println("right: 0 == " + pos%(colNumber-1));
//		System.out.println("down: pos <" + ((rowNumber-1)*colNumber-1));
//		System.out.println("left: 0 == " + pos + colNumber);
		
		// Checks if the position I want to reach exists 
		if(pos < colNumber && dir == Direction.UP ||
			pos % (colNumber-1) == 0 && dir == Direction.RIGHT ||
			pos > ((rowNumber-1)*colNumber)-1 && dir == Direction.DOWN ||
			pos % colNumber == 0 && dir == Direction.LEFT) 
		{
			System.out.println("cannot move that way");
			return false;
		}
		else return true;
	}
	private ArrayList<Integer> getReachableArea(int currentPos, ArrayList<Integer> reachableArea){
		// should get the whole area that can be reached from the given position
		// currently throws stack overflow
		if(!reachableArea.contains(currentPos)) reachableArea.add(currentPos);
		
		for (Direction dir : Direction.values()) 
		{ 
			if(getPosition(currentPos, dir)!=-1 && matrix[getPosition(currentPos, dir)] == Element.EMPTY) // not first row && upper element is empty
				getReachableArea(getPosition(currentPos, dir), reachableArea);
			else continue;
		}
		return reachableArea;
	}
}
