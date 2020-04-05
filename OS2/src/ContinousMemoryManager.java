import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

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
			int newSize = freeBlockSize - size;
			
			freeBlocks.add(new FreeBlock(startNew, newSize));
		}
		
		Disc.getInstance().alloc(startOfAllocation, size);
		
		
	}
	
	
	@Override
	public void deAlloc(String fileName) {
		Integer startOfAllocation = allocationMap.get(fileName);
		Integer size = sizeMap.get(fileName);
		
		freeBlocks.add(new FreeBlock(startOfAllocation, size));
		
		allocationMap.remove(fileName);
		sizeMap.remove(fileName);
		mergeFreeBlocks();
		Disc.getInstance().deAlloc(startOfAllocation, size);
		
	}
	
	private void mergeFreeBlocks() {
		for(int k = 0; k < 5; k++) {
			for(int i = 0; i < freeBlocks.size(); i++) {
				Stack<FreeBlock> candidates = new Stack<FreeBlock>();
				candidates.push(freeBlocks.get(i));
				int end = freeBlocks.get(i).getEnd();
				for(int j = 0; j < freeBlocks.size(); j++) {
					if(end == freeBlocks.get(j).getStartOfAllocation()) {
						candidates.push(freeBlocks.get(j));
						end = freeBlocks.get(j).getEnd();
						j = 0;
						break;
					}
				}
				
				
					if(candidates.size() > 1) {
					while(candidates.size() > 1) {
						FreeBlock b1 = candidates.pop();
						FreeBlock b2 = candidates.pop();
						int newStart = Math.min(b1.getStartOfAllocation(), b2.getStartOfAllocation());
						int newSize = b1.getSize() + b2.getSize();
						
						freeBlocks.remove(b1);
						freeBlocks.remove(b2);
						candidates.push(new FreeBlock(newStart, newSize));
					}
					freeBlocks.add(candidates.pop());
					}
				
			}
		}
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
		public int getEnd() {
			return startOfAllocation + size;
		}
		public void setSize(int size) {
			this.size = size;
		}
	}
}


