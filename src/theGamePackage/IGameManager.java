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
    public void getMatrix();
    public void setMatrix(int brObst);
    public void move(Direction dir);
    public void findSolution();
    //public void getRandomElement(ArrayList<Element> mx);
}
