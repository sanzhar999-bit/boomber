import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
	
	public static void start() {
		Game game = new Game();
		game.generateBombs(16);
		game.mapChanged();
		
		Scanner scanner = new Scanner(System.in); 
		int x, y;
//	FIRST INPUT cant be losed
		System.out.print("Enter x-y coordinate, sample (5-7)");
		String input;
		input = scanner.nextLine();
		
		if(!getIdFromUserInput(input)) {
			while(true) {
				System.out.println("Wrong input");
				System.out.print("Enter x-y coordinate, sample (5-7)");
				input = scanner.nextLine();
				
				if(getIdFromUserInput(input)) {
					break;
				}
			}
		}
		y = Integer.valueOf(input.split("-")[0]);
		x = Integer.valueOf(input.split("-")[1]);

		if (game.checkCoord(x, y).equals(" * ")) {
			game.generateBombs(1);
			game.map[x][y] = game.block;
		}

		game.clear(x, y);
		game.detect();
		game.mapChanged();

//		LOOP for next inp
		while (true) {
			if (game.isFinished() && game.getWin()) {
				System.out.println("You win!");
				game.gameOver();
				restartMenu();
				break;
			} else if (game.isFinished()) {
				game.gameOver();
				restartMenu();
				break;
			} else if (game.isFinished()==false) { 
				x = -1;
				y = -1;
				System.out.print("Enter x-y coordinate, sample (5-7)");
				input = scanner.next();
				
				
				
				if(getIdFromUserInput(input)) {
					y = Integer.valueOf(input.split("-")[0]);
					x = Integer.valueOf(input.split("-")[1]);
					game.checkMove(x, y);
					game.checkTableForWin();
					game.detect();
					if(game.isFinished()==false) {
						game.mapChanged();
					}
				}
				
				
			}

		}
		
	}
	public static boolean getIdFromUserInput(String inp) {
		
		int x, y;
		try {
			 y = Integer.valueOf(inp.split("-")[0]);
			 x = Integer.valueOf(inp.split("-")[1]);
		} catch (NumberFormatException e) {
			System.out.println("Input must be integer");
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			return false;
		}
		if(x>10 || y>10) {
			System.out.println("Coord NOT FOUND!");
			return false;
		}
		return true;
	}
	
	public static void restartMenu() {
		System.out.println("Restart? type Y/N");
		Scanner scanner = new Scanner(System.in); 
		String response = scanner.nextLine();
		if(response.equals("Y") || response.equals("y")) {
			start();
		}else {
			System.out.println("Bye bye!");
		}
	}
	
}
