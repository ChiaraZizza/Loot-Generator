import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
	
	/** Randomly selects a Monster from a Monster file
	 * @param monsterFile a file of Monsters
	 * @return a Monster
	 * @throws FileNotFoundException
	 */
	public static Monster pickMonster(File monsterFile) throws FileNotFoundException {		
		MonsterList monList = new MonsterList(monsterFile);
		
		monList.generateMonsterList(monList.arr);
		Random rand = new Random();
		int randIndex = rand.nextInt(monList.arr.size());
		
		return monList.arr.get(randIndex);
	}
	
	/** Determines the Treasure Class of the selected Monster
	 * @param mon a Monster
	 * @return the name of the Monster
	 */
	public static String fetchTreasureClass(Monster mon) {
		return mon.getTreasureClass();
	}
	
	/** Determines the random item dropped by a Monster and stores the result
	 * @param treasureFile a file of Monster drops
	 * @param monsterTC the TreasureClass of the selected Monster
	 * @param result a GenerateItem to hold the item drop information
	 * @return the name of the item dropped
	 * @throws FileNotFoundException
	 */
	public static String generateBaseItem(File treasureFile, String monsterTC, GenerateItem result)
			throws FileNotFoundException {
		TreasureClass TC = new TreasureClass(treasureFile);
		TC.populate();
		String TCElement = TC.chooseRandomTCElement(monsterTC);	
		String armorName = TC.generateBaseItemH(TCElement);
		
		/* Store relevant information */
		result.addName(armorName);
		
		return armorName;
	}
	
	/** Determines the base stats of the given item and stores the result
	 * @param file a file of Armor stats
	 * @param armorName the name of the Armor
	 * @param result a GenerateItem to hold the item stats information
	 * @throws FileNotFoundException
	 */
	public static void generateBaseStats(File file, String armorName, GenerateItem result)
			throws FileNotFoundException {
		Armor armor = new Armor(file);
		armor.populate();
		int armorStat = armor.getRandomArmorStat(armorName);
		
		/* Store relevant information */
		result.addBaseStat(armorStat);
	}
	
	/** Determines if an Affix (prefix and/or suffix) is generated and stores the result
	 * @param prefixFile a file of Prefixes
	 * @param suffixFile a file of Suffixes
	 * @param result a GenerateItem to hold the prefix/suffix information
	 * @throws FileNotFoundException
	 */
	public static void generateAffix(File prefixFile, File suffixFile, GenerateItem result)
			throws FileNotFoundException {
		Affix prefix = new Affix(prefixFile);
		Affix suffix = new Affix(suffixFile);
		
		prefix.populate();
		suffix.populate();
		
		/* Determines if a Prefix is generated and gets appropriate information, if needed */
		if(prefix.hasAffix()) {
			Stats prefixStats = prefix.generateRandomAffix();
			
			/* Store relevant information */
			result.addPrefix(prefix.getAffixName());
			result.addPrefixStat(prefixStats.name);
			result.addPrefixValue(prefixStats.generateRandomStat(
					prefixStats.min, prefixStats.max));
		}
		
		/* Determines if a Suffix is generated and gets appropriate information, if needed */
		if(suffix.hasAffix()) {
			Stats suffixStats = suffix.generateRandomAffix();
			
			/* Store relevant information */
			result.addSuffix(suffix.getAffixName());
			result.addSuffixStat(suffixStats.name);
			result.addSuffixValue(suffixStats.generateRandomStat(
					suffixStats.min, suffixStats.max));
		}
	}
	
	/** Prompts the user if he or she wants to continue playing
	 * @param scan a scanner
	 * @return true if user enters 'Y'; false if user enters 'N'; re-prompts otherwise
	 */
	public static boolean wantContinue(Scanner scan) {
		while(true) {
			System.out.print("Fight again [y/n]? ");
			String response = scan.next();
			
			if(response.equalsIgnoreCase("n")) {
				scan.close();
				return false;
			} else if(response.equalsIgnoreCase("y")) {
				System.out.println("\n");
				return true;
			} else {
				System.out.println("Not a valid response.");
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		File monsterFile = new File("loot-generator-data/large/monstats.txt");
		File treasureFile = new File("loot-generator-data/large/TreasureClassEx.txt");
		File armorFile = new File("loot-generator-data/large/armor.txt");
		File prefixFile = new File("loot-generator-data/large/MagicPrefix.txt");
		File suffixFile = new File("loot-generator-data/large/MagicSuffix.txt");
		
		Scanner scan = new Scanner(System.in);
		boolean proceed = true;
		
		while(proceed) {
			/* GenerateItem stores the info about the item dropped to be printed later */
			GenerateItem result = new GenerateItem();
			
			Monster monster = pickMonster(monsterFile);
			
			/* Battle sequence */
			System.out.println("Fighting " + monster.getMonsterName());
			System.out.println("You have slain " + monster.getMonsterName() + "!");
			System.out.println(monster.getMonsterName() + " dropped:");
			
			/* Generate random loot drop */
			String armor = generateBaseItem(treasureFile, fetchTreasureClass(monster), result);
			generateBaseStats(armorFile, armor, result);		
			generateAffix(prefixFile, suffixFile, result);

			/* Print out item results to screen */
			System.out.println();
			result.printItem();
			System.out.println();
			
			proceed = wantContinue(scan);
		}
	}
}