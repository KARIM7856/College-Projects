package osAss2;

import java.util.*;

public class Router extends Thread {
	
	Semaphore sem = null;

	Router(){
		sem = new Semaphore();
	}
	@Override
	public void run() {
		
	}
}
