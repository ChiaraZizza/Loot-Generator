
public class GenerateItem {
	private String name;
	private int baseStat;
	
	private String prefix;
	private String prefixStat;
	private int prefixValue;
	
	private String suffix;
	private String suffixStat;
	private int suffixValue;
	
	/** Constructs a GenerateItem
	 */
	public GenerateItem() {
		this.name = "";
		this.baseStat = 0;
		this.prefix = "";
		this.prefixStat = "";
		this.prefixValue = 0;
		this.suffix = "";
		this.suffixStat = "";
		this.suffixValue = 0;
	}
	
	/** Stores the given name as the item name 
	 * @param str the item name
	 */
	public void addName(String str) {
		this.name = str;
	}
	
	/** Stores the given value as the item stats 
	 * @param value the item stats
	 */
	public void addBaseStat(int value) {
		this.baseStat = value;
	}
	
	/** Stores the given name as the item prefix name 
	 * @param p the prefix name
	 */
	public void addPrefix(String p) {
		this.prefix = p;
	}
	
	/** Stores the given name as the item prefix effect name 
	 * @param stat the item prefix effect name
	 */
	public void addPrefixStat(String stat) {
		this.prefixStat = stat;
	}
	
	/** Stores the given value as the item prefix stats
	 * @param value the item prefix stats
	 */
	public void addPrefixValue(int value) {
		this.prefixValue = value;
	}
	
	/** Stores the given name as the item suffix name
	 * @param s the suffix name
	 */
	public void addSuffix(String s) {
		this.suffix = s;
	}
	
	/** Stores the given name as the item suffix effect name
	 * @param stat the item suffix effect name
	 */
	public void addSuffixStat(String stat) {
		this.suffixStat = stat;
	}
	
	/** Stores the given value as the item suffix stats
	 * @param value the item suffix stats
	 */
	public void addSuffixValue(int value) {
		this.suffixValue = value;
	}
	
	/** Prints the item information in the desired format
	 */
	public void printItem() {
		if(!prefix.equals("")) {
			System.out.print(prefix + " ");
		}
		System.out.print(name + " ");
		System.out.println(suffix);
		System.out.println("Defense: " + baseStat);
		if(prefixValue != 0) {
			System.out.println(prefixStat + " " + prefixValue);
		}
		if(suffixValue != 0) {
			System.out.println(suffixStat + " " + suffixValue);
		}
	}
}
