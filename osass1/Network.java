package osAss2;

import java.util.*;
import java.io.*;

public class Network {
	

	int N;
	int TC;
	String TClines;
	
	Network(int size){
		N = size;
		TC = 0;
		TClines = new String();

	}		
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int nCons = 0;
		int nDev = 0;
		String line;
		
		
		System.out.println("What is number of WI-FI Connections?");
		nCons = in.nextInt();
		System.out.println("What is number of devices Clients want to connect?");
		nDev = in.nextInt();
		
		Network net = new Network(nCons);
		Router r = new Router(nCons);
		ArrayList<Device> deList = new ArrayList<Device>();
		
		if(in.hasNext())
			in.nextLine();
		
		for(int i = 0 ; i < nDev ; i ++) {
			line = in.nextLine();
			
				deList.add(new Device(line.split(" ")[0], line.split(" ")[1]));

		}
		
		try {
			for(int i = 0; i < nDev; i++) {
				r.Handle(deList.get(i));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
