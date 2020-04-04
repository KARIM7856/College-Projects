
public interface MemoryManager {
	
	
	public void alloc(String fileName, int nBlocks) throws OutOfMemoryError;
	public void deAlloc(String fileName);
}
