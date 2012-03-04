import java.util.Scanner;


public class SlidingPuzzle {
	public static void main(String[] args){
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		while(choice != 2){
			System.out.println("(1) New puzzle\n(2) Exit");
			choice = sc.nextInt();
			if(choice == 1){
				playGame();
			}
		}
	}
	
	/*
	 * Create a new puzzle object and allow the player to attempt to solve it
	 */
	public static void playGame(){
		PuzzleFrame puzzle = new PuzzleFrame();
		puzzle.shuffleTiles();
		//puzzle.slideTile(15);
		Scanner sc = new Scanner(System.in);
		while(!puzzle.isSolved()){
			System.out.println(puzzle.toString());
			System.out.println("Enter a tile to move: ");		
			int tile = sc.nextInt();
			if(!puzzle.isMoveAllowed(tile)){
				System.out.println("Invalid move: " + tile);
				continue;
			}
			puzzle.slideTile(tile);
		}
		System.out.println(puzzle.toString());
		System.out.println("Puzzle Solved!");
	}
}
