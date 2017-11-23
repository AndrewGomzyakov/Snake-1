package direction;

public enum Dir {
	Up,
	Down,
	Left, 
	Right
	
	public static Dir ofInt(int i) {
		if (i == 2) {
			return Down;
		}
	}
}
