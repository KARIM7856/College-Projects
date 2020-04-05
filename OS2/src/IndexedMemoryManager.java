import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;



public class IndexedMemoryManager implements MemoryManager {
	
	int nBlocks;
	HashMap<Integer, String> allocationMap; //Map between name of file and Starting point of allocation
	HashMap<Integer, String> sizeMap; // Map between name of file and it's size
	LinkedList<FreeBlock> freeBlocks; // To keep track of free memory
	HashMap<Integer, String> blockAllocations;
	
	IndexedMemoryManager(){
		allocationMap = new HashMap<Integer, String>();
		sizeMap = new HashMap<Integer, String>();
		freeBlocks = new LinkedList<FreeBlock>();
		nBlocks = Disc.getInstance().getnBlocks();
		blockAllocations = new HashMap<Integer, String>();
		
		freeBlocks.add(new FreeBlock(0,nBlocks));
		
		
		
	}
	
	@Override
	public void alloc(String fileName, int size) throws OutOfMemoryError {
		if(Disc.getInstance().getRemainingDiscSpace() < size) {
			throw new OutOfMemoryError();
		}
		int counter = 0;
		int freeBlockIndex = getFreeBlock(size);
		int startOfAllocation = freeBlocks.get(freeBlockIndex).getStartOfAllocation();
		int freeBlockSize = freeBlocks.get(freeBlockIndex).getSize();
		allocationMap.put(startOfAllocation, fileName);
		sizeMap.put(size, fileName);
		
		counter = size - 1;
		freeBlocks.remove(freeBlockIndex);
		if(freeBlockSize > size) {
			int startNew = startOfAllocation + size;
			int endNew = startOfAllocation + freeBlockSize;
			
			freeBlocks.add(new FreeBlock(startNew, endNew));
		}
		while(counter > 0) {
			freeBlockIndex = getFreeBlock(counter);
			freeBlocks.get(startOfAllocation).addBlock(freeBlockIndex);
			counter--;
		}
	}

	@Override
	public void deAlloc(String fileName) {
		// TODO Auto-generated method stub
		
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
			listOfAllocation = new LinkedList<Integer>();
			this.size = size;
		}
		private int startOfAllocation;
		private LinkedList<Integer> listOfAllocation;
		private int size;
		public int getStartOfAllocation() {
			return startOfAllocation;
		}
		public void setStartOfAllocation(int startOfAllocation) {
			this.startOfAllocation = startOfAllocation;
		}
		
		public void addBlock(int block) {
			listOfAllocation.add(block);
		}
		
		public LinkedList<Integer> getListOfAllocation(){
			return listOfAllocation;
		}
		
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
	}
	
	

}
