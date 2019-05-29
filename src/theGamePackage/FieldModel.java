package theGamePackage;

import theGamePackage.IGameManager.Element;

public class FieldModel {
	public int rowNumber;
	public int colNumber;
    public int brObstacles;
    private int characterPosition;
    private int targetPosition;
    Element[][] field;
    
	public FieldModel(int rowNum, int colNum, int brObst) {
		this.rowNumber = rowNum;
		this.colNumber = colNum;
		this.brObstacles = brObst;
		this.field = new Element[rowNum][colNum];
		
		for (int i=0; i<rowNum; i++)
		{
			for (int j=0; j<colNum; j++) 
			{
				field[i][j] = Element.EMPTY;
			}
		}
		
	}
}
