import java.util.InputMismatchException;

public class Game {
	public String[][] map = new String[12][12]; 
//	user display
	public String[][] userMap = new String[12][12]; 
	public Boolean Finished = false;
	public Boolean Win = false;

	public String block = " x ";
	public String bomb = " * ";
	public String opened = "   ";

//	first start, getting ready to gameTable
	
	public Game() {
		int row = 0;
		int column = 0;

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				
				if ((x == 0 || x == map.length - 1) || (y == 0 || y == map[0].length - 1)) {
					map[x][y] = opened;
					userMap[x][y] = opened;
				}
				
				else {
					map[x][y] = block;
					userMap[x][y] = block;
				}
			}
		}
	}

	
	
//----------------------------------------------------MAP-CHANGING---------------------------------------------------------------------
	public static void printTable(String[][] str) {
		for (int x = 1; x < str.length - 1; x++) {
//			displaying coords x
			if(x==1) {
				System.out.print("   ");
				for(int j = 0; j<str[0].length-2; j++) {
					int n = j;
					n++;
					System.out.print("   " + n);
					
				}
				System.out.println("");
			}
			System.out.print("   ");
			for(int j = 0; j<str[0].length-2; j++) {
			
				if(j==0)System.out.print("  ___");
				if(j>0)System.out.print(" ___");
				
			}
			
			for (int y = 0; y < str[0].length; y++) {
//				coords y
				if(y==1) {
					System.out.print(x);
				}
//				displaying content
				if (y > 0 && y < str[0].length)
					System.out.print("|");
				else
					System.out.println("");

			 System.out.print(str[x][y]);
			}
			System.out.println();
		}
	}

	public void clear(int x, int y) {
		for (int i = (x - 1); i <= (x + 1); i++) {
			for (int j = (y - 1); j <= (y + 1); j++) {
				if (map[i][j].equals(block)) {
					userMap[i][j] = opened;
					map[i][j] = opened;
				}
			}
		}
	}
	public void mapChanged() {
		printTable(userMap);
		System.out.println("");
	}

	
	public void generateBombs(int n) {
		for (int m = 0; m < n; m++) {
			
			while (true) {
				int x, y = 0; 
				x = (int) (10 * Math.random());
				y = (int) (10 * Math.random());

				
				if (x >= 1 && x <= 10) {
					if (y >= 1 && y <= 10) {
//						avoid duplicate bombs
						if (!map[x][y].equals(bomb)) {
							map[x][y] = bomb;
							break;
						}
					}
				}
			}
		}
	}

	
	

//-------------------------------------------------ANALYZE-MOVE--------------------------------------------------------------------------
	public String checkCoord(int x, int y) {
		return map[x][y];
	}

	
	public void detect() {
		for (int x = 1; x < userMap.length - 2; x++) { 
			for (int y = 1; y < userMap.length - 2; y++) {
				if (map[x][y].equals(opened)) {
					
//					bombs around opened coord
					int nums = 0; 
					for (int i = (x - 1); i <= (x + 1); i++) {
						for (int j = (y - 1); j <= (y + 1); j++) {
							if (map[i][j].equals(bomb))
								nums++; 
						}
					}
					userMap[x][y] = " " + nums + " ";
				}
			}
		}
	}

	
	public void checkMove(int x, int y) {
		if (map[x][y].equals(block)) { 
			Finished = false;
			userMap[x][y] = opened;
			map[x][y] = opened;
		} else if (map[x][y].equals(bomb)) { 
			Finished = true; 
			Win = false; 
			System.out.println("GAME OVER");
		} else if (userMap[x][y].equals(opened) && map[x][y].equals(opened)) {
			Finished = false;
			System.out.println("This tile's been cleared!");
		}
	}

	
	public void checkTableForWin() {
		int blocks = 0; 
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].equals(block))
					blocks++; 
			}
		}
		if (blocks != 0)
			Win = false; 
		else {
			Win = true;
			Finished = true;
		}
	}

	
	public Boolean isFinished() {
		return Finished;
	}

	public Boolean getWin() {
		return Win;
	}
	
	public void gameOver() {
		printTable(map);
	}

}
