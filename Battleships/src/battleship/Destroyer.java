package battleship;
/**
 * 
 * @author Carl Whitehouse-Tedd
 *
 */

public class Destroyer extends Ship {

	public Destroyer(boolean vertical) {
		super(vertical);
		this.length = 2;
        this.vertical = vertical;
        health = length;
	}

	@Override
	public void hit() {
		health--;
	}

	@Override
	public boolean isAlive() {
		if(health > 0){
			return true; //if the health which is also the length is greater than zero then it is still alive
		}
		return false; //if it is not greater than zero then it must be zero and therefore has no length hence dead.
	}

	@Override
	public int getHealth() {
		return this.health;
	}

}