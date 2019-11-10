package osAss2;

import java.util.*;

public class Device extends Thread{
	
	String Type;
	String Name;
	int connection;
	
	Device(String name ,String type){
		Name = name;
		Type = type;
	}
	
	@Override
	public void run() {
		try {
			connect();
			performOnlineActivity();
			disConnect();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	 synchronized void connect() throws InterruptedException{
		//System.out.println(Name + " " + Type + " arrived");
		Thread.sleep(400);
	}
	
	 synchronized public  void performOnlineActivity() throws InterruptedException {
		System.out.println(Name + " " + Type + " Perform online activity");
		Thread.sleep(400);
	}
	
	 synchronized public  void disConnect() throws InterruptedException {
		System.out.println(Name + " " + Type + " logged out");
		Router.disconnect(this);
		Thread.sleep(400);
		
	}
	
	public void setConNum(int n) {
		connection = n;
	}
	
}
