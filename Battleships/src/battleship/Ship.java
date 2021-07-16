package battleship;
import javafx.scene.Parent;

/**
 * List properties that all ships will have so different variations of "Ship" can be made
 * 
 * @author Rajan Gopalji
 *
 */
public abstract class Ship extends Parent {
	
    public int length;
    
    public boolean vertical = true;

    protected int health;

    public Ship(boolean vertical) { 
    }

    public abstract void hit();

    public abstract boolean isAlive();
    
    public abstract int getHealth();
}

