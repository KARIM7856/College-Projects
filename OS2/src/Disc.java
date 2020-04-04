
public class Disc {
	private static Disc instance;
	
	
	int nBlocks;
	int remainingDiscSpace;
	boolean[] blocks;
	
	
	public int getnBlocks() {
		return nBlocks;
	}
	public void setnBlocks(int nBlocks) {
		this.nBlocks = nBlocks;
	}
	public int getRemainingDiscSpace() {
		return remainingDiscSpace;
	}
	public void setRemainingDiscSpace(int remainingDiscSpace) {
		this.remainingDiscSpace = remainingDiscSpace;
	}
	
	private Disc(String mode, int nBlocks) {
		this.nBlocks = nBlocks;
		blocks = new boolean[nBlocks];
		
	}
	public static Disc getInstance() {
		
		if(instance == null) {
			instance = new Disc(null,0);
		}
		return instance;
	}
	
	public static void initialize(String mode, int nBlocks) {
		if(instance == null)
			instance = new Disc(mode,nBlocks);
	}
	
	

}
