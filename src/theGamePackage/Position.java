package theGamePackage;

public class Position implements Comparable<Position>{
    public int row;
	public int col;
    public Position(int r, int c){
        this.row = r;
        this.col = c;
    }
	@Override
	public int compareTo(Position p) {
		int difference = 0;
		if(row!=p.row)  
			difference += Math.abs(row-p.row); 
		if(col!=p.col)  
			difference += Math.abs(col-p.col);
		return difference;
	}
}
