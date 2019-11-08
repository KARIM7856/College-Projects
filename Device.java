package osAss2;

import java.util.*;

public abstract class Device {
	
	
	String Name;
	
	public abstract boolean connect();
	
	public abstract void preformOnlineActivity() ;
	
	public abstract void disConnect() ;
	
}
