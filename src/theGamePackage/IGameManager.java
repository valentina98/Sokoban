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
    public void setMatrix();
    public boolean move(Direction dir);
    public void findSolution();
    //public void getRandomElement(ArrayList<Element> mx);
}
