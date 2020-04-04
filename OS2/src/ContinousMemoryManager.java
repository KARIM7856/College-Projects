import java.util.HashMap;
import java.util.LinkedList;

public class ContinousMemoryManager implements MemoryManager {

	int nBlocks;
	HashMap<String, Integer> allocationMap; //Map between name of file and Starting point of allocation
	HashMap<String, Integer> sizeMap; // Map between name of file and it's size
	LinkedList<FreeBlock> freeBlocks; // To keep track of free memory
	
	ContinousMemoryManager(){
		allocationMap = new HashMap<String,Integer>();
		sizeMap = new HashMap<String,Integer>();
		freeBlocks = new LinkedList<FreeBlock>();
		nBlocks = Disc.getInstance().getnBlocks();
		
		freeBlocks.add(new FreeBlock(0,nBlocks));
		
		
		
	}
	
	@Override
	public void alloc(String fileName, int size) throws OutOfMemoryError {
		
		if(Disc.getInstance().getRemainingDiscSpace() < size) {
			throw new OutOfMemoryError();
		}
		
		int freeBlockIndex = getFreeBlock(size);
		int startOfAllocation = freeBlocks.get(freeBlockIndex).getStartOfAllocation();
		int freeBlockSize = freeBlocks.get(freeBlockIndex).getSize();
		
		allocationMap.put(fileName, startOfAllocation);
		sizeMap.put(fileName, size);
		
		freeBlocks.remove(freeBlockIndex);
		
		if(freeBlockSize > size) {
			int startNew = startOfAllocation + size;
			int endNew = startOfAllocation + freeBlockSize;
			
			freeBlocks.add(new FreeBlock(startNew, endNew));
		}
		
		
	}
	@Override
	public void deAlloc(String fileName) {
		
		
	}
	
	public int getFreeBlock(int size) {
		int index = 0;
		int min = freeBlocks.get(index).getSize();
		
		for(int i = 0; i < freeBlocks.size(); i++) {
			FreeBlock temp = freeBlocks.get(i);
			
			if(temp.getSize() >= size && temp.getSize() < min) {
				index = i;
			}
		}
		return index;
	}
	
	
	

	private class FreeBlock{
		public FreeBlock(int startOfAllocation, int size) {
			super();
			this.startOfAllocation = startOfAllocation;
			this.size = size;
		}
		private int startOfAllocation;
		private int size;
		public int getStartOfAllocation() {
			return startOfAllocation;
		}
		public void setStartOfAllocation(int startOfAllocation) {
			this.startOfAllocation = startOfAllocation;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
	}
}


