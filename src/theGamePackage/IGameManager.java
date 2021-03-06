package theGamePackage;


public interface IGameManager {

    enum Element {
    	CHARACTER,
    	OBSTACLE,
    	BOX,
    	TARGET,
    	EMPTY
    }
    enum Direction {
    	UP,
    	DOWN,
    	RIGHT,
    	LEFT
    }
    public Element[][] getMatrix();
    public Position getCharacterPosition();
    public void setMatrix();
    public boolean positionExists(Position myPos, Direction dir);
	public boolean canMoveThatWay(Position characterPosition, Direction dir);
    public void move(Direction dir);
    public String findSolution();
    public String findShortestPath(Position myPos, Position dest);
    //public void getRandomElement(ArrayList<Element> mx);
}
