package battleship;
/**
 * 
 * @author Carl Whitehouse-Tedd
 *
 */

public class AircraftCarrier extends Ship {
	
	
	public AircraftCarrier(boolean vertical) {
		super(vertical);
		this.length = 5;
        this.vertical = vertical;
        health = length;
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getHealth(){
		return this.health;
	}

}
