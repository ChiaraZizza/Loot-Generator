import java.util.Random;

public class Stats {
	public String name;
	public int min;
	public int max;
	
	/** Constructs a Stats
	 * @param low the minimum value of the Stat
	 * @param high the maximum value of the Stat
	 */
	public Stats(int low, int high) {
		this.min = low;
		this.max = high;
	}
	
	/** Constructs a Stats
	 * @param n the name of the Stat
	 * @param low the minimum value of the Stat
	 * @param high the maximum value of the Stat
	 */
	public Stats(String n, int low, int high) {
		this.name = n;
		this.min = low;
		this.max = high;
	}
	
	/** Generates a random value between min and max, inclusive
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return a random value between min and max, inclusive
	 */
	public int generateRandomStat(int min, int max) {
		int range = max - min;	
		Random rand = new Random();
		int randValue = rand.nextInt(range + 1);
		
		return randValue + min;
	}
}
