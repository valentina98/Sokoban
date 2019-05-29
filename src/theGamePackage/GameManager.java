package theGamePackage;

import java.util.ArrayList;
import java.util.Random;


public class GameManager implements IGameManager {

    public int rowNum;
    public int brObstacles;
    private int characterPosition;
    //private int targetPosition; /////////////////
    
    //ArrayList<Element> matrix = new ArrayList<Element>();
    Element[] matrix;

	public GameManager(int rn, int brObst) {
		rowNum = rn;
		brObstacles = brObst;
		matrix = new Element[rowNum*rowNum];
		for (int i=0; i<rowNum*rowNum; i++)
		{
			matrix[i] = Element.EMPTY;
		}
		setMatrix(brObst);
	}
	@Override
	public void getMatrix() {
		for (int i=0; i<rowNum*rowNum; i++) {
			System.out.print(matrix[i].toString() +  " ");
			if((i+1)%rowNum == 0) System.out.println();
	    }
    }
	@Override
	public void setMatrix(int brObstacles) {
		int cursor = getRandomNumberInRange(0, rowNum*rowNum-1); //rowNum*rowNum
		matrix[cursor] = Element.CHARACTER;
		characterPosition = cursor;
		
		cursor = getRandomNumberInRange(0, rowNum*rowNum-1); //rowNum*rowNum
		matrix[cursor] = Element.TARGET;
		//targetPosition = cursor;
		
		for (int i=0; i<brObstacles; i++) {
			cursor = getRandomNumberInRange(0, rowNum*rowNum-1); //rowNum*rowNum
			if (matrix[cursor] != Element.EMPTY) i--;
			else matrix[cursor] = Element.OBSTACLE;
	    }
	}

	@Override
	public void move(Direction dir) {
		int newPosition;
		if(positionExists(characterPosition, dir)) {
			newPosition = getPosition(characterPosition, dir);
			//case "exit":
//			if(characterPosition < rowNum &&
//				characterPosition > rowNum*(rowNum-1) &&
//				characterPosition % rowNum == 0 &&
//				characterPosition-1 % rowNum ==0) { // obstaclePosition
//				
//				System.out.print("game over");
//			}


			matrix[characterPosition] = matrix[newPosition]; // sets the old to empty
			matrix[newPosition] = Element.CHARACTER;
			characterPosition = newPosition;
		}
		else {
				System.out.println("cannot move that way");
		}
	}
	@Override
	public void findSolution() {
		
		// there is a road from between the obstacle and the target? // find all options
		// there is an empty square on the other side of the turns
		// there is a road from the character position to this empty square?
		
		// count moves, find shortest
		
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
		switch(dir) {
		  case UP:
			  return pos-rowNum;
		  case RIGHT:
			  return pos+1;
		  case DOWN:
			  return pos+rowNum;
		  case LEFT:
			  return pos-1;
		default: 
			  return -1;
		}
	}
	private boolean positionExists(int pos, Direction dir) {
		if(pos < rowNum && dir == Direction.UP ||
				pos > rowNum*(rowNum-1) && dir == Direction.RIGHT ||
				pos % rowNum == 0 && dir == Direction.DOWN ||
				pos-1 % rowNum ==0 && dir == Direction.LEFT) return false;
		else return true;
	}
	private ArrayList<Integer> getReachableArea(int currentPos, ArrayList<Integer> reachableArea){
		
		if(!reachableArea.contains(currentPos)) reachableArea.add(currentPos);
		
		for (Direction dir : Direction.values()) 
		{ 
			if(positionExists(currentPos, dir) && matrix[getPosition(currentPos, dir)] == Element.EMPTY) // not first row && upper element is empty
				getReachableArea(getPosition(currentPos, dir), reachableArea);
			else continue;
		}
		return reachableArea;
	}
//	private static void swapSquares(Element a, Element b) { // not sure
//		Element c = a;
//		a = b;
//		b = c;
//	}
	
}
