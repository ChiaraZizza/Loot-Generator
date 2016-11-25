
public class Monster {
	private String name;
	private String TC;
	
	/** Constructs a Monster
	 * @param n the Monster name
	 * @param tc the Monster TreasureClass
	 */
	public Monster(String n, String tc) {
		this.name = n;
		this.TC = tc;
	}
	
	/** Gets the Monster name
	 * @return the Monster name
	 */
	public String getMonsterName() {
		return this.name;
	}
	
	/** Gets the Monster TreasureClass
	 * @return the Monster TreasureClass
	 */
	public String getTreasureClass() {
		return this.TC;
	}
}
