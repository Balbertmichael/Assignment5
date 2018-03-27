package assignment5;

import java.util.List;


public class Critter2 extends Critter {

	private static int fought = 0;
	
	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return null;
	}

	public Critter2() {
	}

	/**
	 * doTimeStep: Critter2 will always run randomly in whichever direction it wants
	 */
	@Override
	public void doTimeStep() {
		fought = 0;
		run(getRandomInt(8));
		if (getEnergy() >= Params.start_energy * 3) {
			Critter2 child = new Critter2();
			reproduce(child, getRandomInt(8));
		}
	}

	/**
	 * fight: Critter2 will always fight
	 */
	@Override
	public boolean fight(String oponent) {
		fought++;
		return true;
	}

	/**
	 * String representation of Critter2
	 */
	@Override
	public String toString() {
		return "2";
	}

	/**
	 * Shows the number of fights Critter2s had last turn
	 * 
	 * @param critter4
	 */
	public static String runStats(List<Critter> critter4) {
		String ret = "";
		ret += (critter4.size() + " total Critter2s who fought " + fought + " times") + '\n';
		return ret;
	}

}
