import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Affix {
	private String affixName;
	private Map<String, Stats> affixMap;
	private File file;
	
	/** Constructs an Affix
	 * @param f a File of Affixes
	 */
	public Affix(File f) {
		this.affixName = "";
		this.affixMap = new HashMap<String, Stats>();
		this.file = f;
	}
	
	/** Gets the name of the given Affix
	 * @return name of Affix
	 */
	public String getAffixName() {
		return this.affixName;
	}
	
	/** Generates a random value (0 or 1) to determine if an Affix is produced
	 * @return true if 0 is generated; false otherwise
	 */
	public boolean hasAffix() {
		Random rand = new Random();
		int randValue = rand.nextInt(2);

		if(randValue == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/** Populates affixMap with the affixes stored in file
	 * @throws FileNotFoundException
	 */
	public void populate() throws FileNotFoundException {
		Scanner scan = new Scanner(this.file);
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			/* Determine name of Affix */
			int tabIndex = line.indexOf("\t");
			String affixName = line.substring(0, tabIndex);
			
			/* Determine effect of Affix */
			String affixStats = line.substring(tabIndex + 1, line.length());
			int tabIndex2 = affixStats.indexOf("\t");
			String affixEffect = affixStats.substring(0, tabIndex2);
			
			/* Determine minimum value of Affix */
			String affixValues = affixStats.substring(tabIndex2 + 1, affixStats.length());
			int tabIndex3 = affixValues.indexOf("\t");
			int affixMin = Integer.parseInt(affixValues.substring(0, tabIndex3));
			
			/* Determine maximum value of Affix */
			String affixVal2 = affixValues.substring(tabIndex3 + 1, affixValues.length());
			int affixMax = Integer.parseInt(affixVal2);
			
			/* Store Affix data in relevant fields */
			Stats affixStat = new Stats(affixEffect, affixMin, affixMax);
			this.affixMap.put(affixName, affixStat);
		}
		scan.close();
	}
	
	/** Randomly selects an Affix to be used
	 * @return the stats of the selected Affix
	 */
	public Stats generateRandomAffix() {
		ArrayList<String> affixList = new ArrayList<String>(this.affixMap.keySet());
		
		Random rand = new Random();
		int randElement = rand.nextInt(affixList.size());
		String choice = affixList.get(randElement);
		
		this.affixName = choice;
		
		return this.affixMap.get(choice);
	}
}
