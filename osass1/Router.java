package osAss2;

import java.util.*;

public class Router{
	
	static Semaphore sem = null;
	static int N;
	public static ArrayList<Device> conList;

	Router(int nConnections){
		sem = new Semaphore(nConnections);
		conList = new ArrayList<Device>();
		N = nConnections;
	}
	
	void occupy(Device c) {
		if(conList.size() == N) {
			conList.add(c);
			System.out.println(c.Name + " " + c.Type + " " + "Arrived and waiting");
		}
		else {
			System.out.println(c.Name + " " + c.Type + " " + "Arrived");
		}
		
		sem.P();
		synchronized (this) {
			conList.add(c);
			
			System.out.println("Connection " + (conList.indexOf(c)+1) + " " + c.Name + " Occupied" );
		}

	}
	
	void Handle(Device c) {
		occupy(c);
		c.start();
	}
	
	 static void disconnect(Device c) {
		
		conList.remove(c);
		
		sem.V();
	}
	 
}
