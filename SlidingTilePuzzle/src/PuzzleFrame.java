
public class PuzzleFrame {
	
	private int[] state;
	
	/* 
	 * Constructor for PuzzleFrame Class. Initialises the state of the puzzle to the solved state
	 */
	public PuzzleFrame(){
		state = new int[16];
		for(int i = 0; i <state.length; i++){
			state[i] = i+1;
		}
		state[15] = 0;
	}
	/*
	 * Returns a printable form of the current state of the board, with padded single digits
	 */
	public String toString(){
		String buf = "";
		for(int i = 0; i <state.length; i++){
			buf += " ";
			if(i%4 == 0){
				buf += '\n';
			}
			if(state[i] <10){
				buf += '0';
			}
			buf += state[i];
		}
		return buf;
	}
	
	/*
	 * Checks whether the board is in the solved state by checking that each element is less than the next
	 */
	public boolean isSolved(){
		for(int i = 1; i < state.length - 1; i++){
			if(state[i-1] > state[i])
				return false;			
		}
		return true;		
	}
	
	/*
	 * Returns whether a given tile can be moved this turn. 
	 */
	public boolean isMoveAllowed(int tile){
		if(tile < 0 || tile > 15){
			return false;
		}
		int pos;
		for(pos = 0; pos < state.length; pos++){
			if(state[pos] == tile){
				break;
			}			
		}
		/*
		 * Check whether the empty slot is beside the given tile
		 */
		if(pos + 4 < 16 && state[pos + 4] == 0){
			return true;
		}
		if(pos - 4 >= 0 && state[pos - 4] == 0){
			return true;
		}
		if(pos + 1 < 16 && state[pos + 1] == 0){
			return true;
		}
		if(pos - 1 >= 0 && state[pos - 1] == 0){
			return true;
		}
		return false;
	}
	
	/*
	 * Move given tile to the empty slot, if the empty slot is adjacent 
	 */
	public void slideTile(int tile){
		if(!isMoveAllowed(tile)){
			return;
		}
		int pos;
		for(pos = 0; pos < state.length; pos++){
			if(state[pos] == tile){
				break;
			}			
		}
		/*
		 * Check whether the empty slot is beside the given tile and if so, swap them
		 */
		if(pos + 4 < 16 && state[pos + 4] == 0){
			state[pos + 4] = state[pos];
			state[pos] = 0;
		}
		else if(pos - 4 >= 0 && state[pos - 4] == 0){
			state[pos - 4] = state[pos];
			state[pos] = 0;
		}
		else if(pos + 1 < 16 && state[pos + 1] == 0){
			state[pos + 1] = state[pos];
			state[pos] = 0;
		}
		else if(pos - 1 >= 0 && state[pos - 1] == 0){
			state[pos - 1] = state[pos];
			state[pos] = 0;
		}
		return;
	}
	
	/*
	 * Random shuffle the tiles around 10000 times.
	 * The direction to take is determined by a random number between 0.0 and 1.0
	 */
	public void shuffleTiles(){
		int pos;
		for(pos = 0; pos < state.length; pos++){
			if(state[pos] == 0){
				break;
			}			
		}
		double choice;
		for(int i = 0; i< 10000; i++){
			choice  = Math.random();
			if(choice < 0.25){
				slideTile(pos + 4);
				pos += 4;
			}
			else if(choice < 0.5){
				slideTile(pos - 4);
				pos -= 4;
			}
			else if(choice < 0.75){
				slideTile(pos + 1);
				pos++;
			}
			else{
				slideTile(pos - 1);
				pos--;
			}
		}
	}
}
