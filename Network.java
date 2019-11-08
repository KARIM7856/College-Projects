package osAss2;

import java.util.*;
import java.io.*;

public class Network {
	
	private ArrayList <Connections> conlist;
	int N;
	int TC;
	String TClines;
	
	Network(int size){
		N = size;
		TC = 0;
		TClines = new String();
		conlist = new ArrayList<Connections>();
	}		
	
	
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int nCons = 0;
		int nDev = 0;
		String line;
		Network net = new Network(nCons);
		
		System.out.println("What is number of WI-FI Connections?");
		nCons = in.nextInt();
		System.out.println("What is number of devices Clients want to connect?");
		nDev = in.nextInt();
		
		for(int i = 0 ; i < nDev ; i ++) {
			line = in.nextLine();
			Connections temp = parse(line);
			net.addConnection(temp);
		}
		
		
		
	}
	
	
	
	
	
	public void addConnection(Connections newC) {
		
		conlist.add(newC);
		
	}
	
	public static Connections parse (String line) {
		
		return new Connections(line.split(" ")[0] , line.split(" ")[1]);
		
	}
	
}
