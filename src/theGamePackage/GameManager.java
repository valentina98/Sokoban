package theGamePackage;

import java.util.LinkedList;
//import java.util.Queue;
import java.util.Random;


public class GameManager implements IGameManager {

    public MatrixModel matrixModel;
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
		if(matrixModel.matrix[newPos.row][newPos.col] == Element.EMPTY ||
				matrixModel.matrix[newPos.row][newPos.col] == Element.TARGET) return true;
		else return false;
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
	
	
	
	
	
	
	// FIND SOLUTION METHODS


	// A Data Structure for queue used in findSolution() 
    class QueueNode // public
    {
    	Position position; // The cordinates of a cell 
    	int distance; // cell's distance of from the source 
    	QueueNode(Position p, int d)
    	{
    		this.position = p;
    		this.distance = d;
    	}
    }
    
    // function to find the shortest path between a given source cell to a destination cell.
	public String findShortestPath(Position myPos, Position dest) {
		
		String strPath = new String();
		
	    boolean visited[][] = new boolean[matrixModel.rowNumber][matrixModel.colNumber]; 
	    // Mark the source cell as visited 
	    visited[myPos.row][myPos.col] = true; 
	    
	    // Create a queue 
	    LinkedList<QueueNode> queue = new LinkedList<QueueNode>(); // Used as queue for the positions and their distance
	    LinkedList<Direction> dirQueue = new LinkedList<Direction>(); // Used as stack for the directions
	    
	    
	    QueueNode source = new QueueNode(myPos, 0); // Distance of source cell is 0 
	    
	    queue.add(source);  // Enqueue source cell 
	    //dirQueue.push(Direction.UP); // add some direction for code purpose, will fix it later on
	    
	    boolean pathFound = false;
	    
	    //outerloop: // helps optimize the loops
	    while (!queue.isEmpty()) 
	    { 
	        QueueNode theHead = queue.peek(); 
	        Position headPos = theHead.position; 
	  
	        // If we have reached the destination cell, we are done 
	        if (headPos.compareTo(dest) == 0) 
	        	//headPos.row == dest.row && headPos.col == dest.col
	        {
	        	strPath = "You can find it with " + theHead.distance + " moves: "; 
	        	pathFound = true;
	        	break;
	        }
	            
	        // Otherwise dequeue the front cell in the 2 queues 
	        queue.poll();
	        //dirQueue.pop();
	        
	        for (Direction dir : Direction.values()) //int i = 0; i < 4; i++
	        { 
	            // If adjacent cell is valid, has path and not visited yet, enqueue it. 
	            if (positionExists(headPos, dir))
	            {
            		if(canMoveThatWay(headPos, dir))
            		{
            			Position newPos = getPosition(headPos, dir);
            			if(visited[newPos.row][newPos.col] == false)
            			{
			                int newDist = theHead.distance + 1;
//            				if(newPos.compareTo(dest) == 0)  // helps optimize the loops
//            				{
//            					System.out.println( "you found it with " + newDist + " moves"); 
//            		        	pathFound = true;
//            		        	break outerloop;
//            				}
            				if(matrixModel.matrix[newPos.row][newPos.col] == Element.EMPTY ||
            					matrixModel.matrix[newPos.row][newPos.col] == Element.TARGET)
	            			{
				                // Mark cell as visited and enqueue it 
				                visited[newPos.row][newPos.col] = true; 
				                QueueNode Adjcell = new QueueNode(newPos, newDist);
				                queue.add(Adjcell); 
				                dirQueue.push(dir);
	            			}
            				
            			}
            			
            		}
	            }
	        } 
	    }    
        // Show message if destination cannot be reached 
	    if(!pathFound)
	    {
	    	return "Your problem cannot be solved."; 
	    }
	    else 
	    {
	    	for(Direction dir : dirQueue)
	    	{
	    		switch(dir) 
	    		{
				  case UP:
					  strPath += "up, ";
					  break;
				  case RIGHT:
					  strPath += "right, ";
					  break;
				  case DOWN:
					  strPath += "down, ";
					  break;
				  case LEFT:
					  strPath += "left, ";
					  break;
	    		}
	    	}
	    	return strPath;
	    }
	}
	
	@Override
	public String findSolution() {
		
		return findShortestPath(matrixModel.characterPosition,matrixModel.targetPosition);
	
	// 
	    // check source and destination cell 
	    // of the matrix have value 1 
		//Element currentEl = matrixModel.matrix[matrixModel.characterPosition.row][matrixModel.characterPosition.col];
		
	        
//	        Position pos = new Position(matrixModel.characterPosition.row, matrixModel.characterPosition.col);
//		Direction dir = null;
//		
//	    if (positionExists(pos, dir) &&
//	    		canMoveThatWay(pos, dir))
//	    {
//	    	boolean[][] visited = new boolean[matrixModel.rowNumber][matrixModel.colNumber];
//	    	
//	    	
//	        //memset(visited, false, sizeof visited); 
//	        //return -1; 
//	    }
//	    else // if some of the methods returns false or null
//	    {
//	    	
//	    }
		
		
		
		
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
		
//		ArrayList<Integer> reachableArea  = new ArrayList<Integer>();
//		//reachableArea = getReachableArea(characterPosition, reachableArea);
//
//		reachableArea.forEach((n) -> System.out.println(n)); 
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
