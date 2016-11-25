import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Armor {
	private Map<String, Stats> armorMap;
	private File file;
	
	/** Constructs an Armor
	 * @param f a File of Armors
	 */
	public Armor(File f) {
		this.armorMap = new HashMap<String, Stats>();
		this.file = f;
	}
	
	/** Populates armorMap with the armor stored in file
	 * @throws FileNotFoundException
	 */
	public void populate() throws FileNotFoundException {
		Scanner scan = new Scanner(this.file);
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			/* Determine name of Armor */
			int tabIndex = line.indexOf("\t");
			String armorName = line.substring(0, tabIndex);
			
			/* Determine minimum value of Armor */
			String armorRange = line.substring(tabIndex + 1, line.length());
			int tabIndex2 = armorRange.indexOf("\t");
			int armorMin = Integer.parseInt(armorRange.substring(0, tabIndex2));
			
			/* Determine maximum value of Armor */
			String armorVal2 = armorRange.substring(tabIndex2 + 1, armorRange.length());
			int armorMax = Integer.parseInt(armorVal2);
			
			/* Store Armor data in relevant fields */
			Stats armorStats = new Stats(armorMin, armorMax);
			this.armorMap.put(armorName, armorStats);
		}
		scan.close();
	}
	
	/** Randomly selects stats of Armor selected
	 * @param armorName the chosen Armor
	 * @return the stats of the selected Armor
	 */
	public int getRandomArmorStat(String armorName) {
		Stats armor = armorMap.get(armorName);
		return armor.generateRandomStat(armor.min, armor.max);
	}

}
