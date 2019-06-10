package theGamePackage;

import theGamePackage.IGameManager.Element;

public class MatrixModel {
	
	public int rowNumber;
    public int colNumber;
    public int brObstacles;
    public Element[][] matrix;  // private
    public Position characterPosition;
    public Position targetPosition; /////////////////
    
    
    //ArrayList<Element> matrix = new ArrayList<Element>();
    

    public MatrixModel(int rowNum, int colNum, int brObst) {
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
	}
}
