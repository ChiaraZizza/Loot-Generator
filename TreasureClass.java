import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TreasureClass {
	private Map<String, ArrayList<String>> TCMap;
	private File file;
	
	/** Constructs a TreasureClass
	 * @param f a File of TreasureClasses
	 */
	public TreasureClass(File f) {
		this.TCMap = new HashMap<String, ArrayList<String>>();
		this.file = f;
	}
	
	/** Populates TCMap with the name of each TreasureClass as keys and the
	 * following three names (in a list) as the values
	 * @throws FileNotFoundException
	 */
	public void populate() throws FileNotFoundException {
		Scanner scan = new Scanner(this.file);
		ArrayList<String> arr = new ArrayList<String>();
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			getTCList(line, arr);
			this.TCMap.put(getTCName(line), arr);
		}
		scan.close();
	}
	
	/** Gets the name of the TreasureClass from the given line from file
	 * @param line a line from file
	 * @return the name of the TreasureClass for that line
	 */
	public String getTCName(String line) {
		int tabIndex = line.indexOf("\t");
		return line.substring(0, tabIndex);
	}
	
	/** Stores each name in line from file into its appropriate position in arr
	 * @param line a line from file
	 * @param arr a list containing the names of the given TreasureClass
	 */
	public void getTCList(String line, ArrayList<String> arr) {
		int tabIndex = line.indexOf("\t");
		
		if(tabIndex == -1) {
			arr.add(line);
		} else {
			String name = line.substring(0, tabIndex);
			arr.add(name);
			getTCList(line.substring(tabIndex + 1, line.length()), arr);
		}
	}
	
	/** Helper function to generateBaseItem. Checks to see if given string is a Treasure Class
	 * and recursively searches until it finds an armor piece
	 * @param str the string being checked for Treasure Class/armor status
	 * @return an armor name
	 */
	public String generateBaseItemH(String str) {
		if(isTreasureClass(str)) {
			/* Choose a new random element from the TC, str */
			String result = chooseRandomTCElement(str);	
			return generateBaseItemH(result);
		}
		return str;
	}
	
	/** Chooses a random element from the three options of the given TreasureClass 
	 * @param TC the TreasureClass name
	 * @return a random element of the TreasureClass
	 */
	public String chooseRandomTCElement(String TC) {
		ArrayList<String> lst = this.TCMap.get(TC);
		Random rand = new Random();
		int randIndex = rand.nextInt(lst.size());
		
		return lst.get(randIndex);
	}
	
	/** Determines if the given string is a Treasure Class in TCMap
	 * @param str a potential TreasureClass name
	 * @return true if str is a key in TCMap; false otherwise
	 */
	public boolean isTreasureClass(String str) {
		return this.TCMap.containsKey(str);
	}
}
