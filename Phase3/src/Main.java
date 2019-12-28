import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

public class Main {
		
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DataBaseManager connect = new DataBaseManager();
		//connect.getData();
		int ans;
		
		System.out.println("1- login.");
		System.out.println("2- sign up.");
		System.out.println();
		
		ans = in.nextInt();
		String email;
		
		if(ans == 1) {
			email = login();
			System.out.println("********************************");
			System.out.println("what would you like to do?");
			System.out.println();
			
			int choice;
			
			System.out.println("1- send friend request.");
			System.out.println("2- check friend requests.");
			
			choice = in.nextInt();
			
			if(choice == 1) {
				friendRequest(email);
			}
			else if(choice == 2) {
				checkAcceptRejectFriend(email);
			}
			
		}
		
		else if(ans == 2) {
			email = signup();
			
			System.out.println("********************************");
			System.out.println("what would you like to do?");
			System.out.println();
			
			int choice;
			
			System.out.println("1- send friend request.");
			System.out.println("2- check friend requests.");
			
			choice = in.nextInt();
			
			if(choice == 1) {
				friendRequest(email);
			}
			else if(choice == 2) {
				checkAcceptRejectFriend(email);
			}
		}
		//friendRequest(email);
		//checkAcceptRejectFriend(email);
		
		
	}
	
	public static String signup() {
		int n; 
		String fname;
		String lname;
		String email;
		String phone;
		String pass;
		String location;
		String country;
		int year;
		int month;
		int day;
		String gender;
		
		
		
		System.out.println("first name = ");
		fname = in.next();
		System.out.println("last name = ");
		lname = in.next();
		System.out.println("email = ");
		email = in.next();
		
		String result ="";
		
		
			try {
			
				DataBaseManager connect2 = new DataBaseManager();
				String query1 = "SELECT * FROM Users WHERE email = '" + email + "'";
				connect2.rs = connect2.st.executeQuery(query1);
				connect2.rs.first();
				String result1 = connect2.rs.getString("email");
				if(result1 != null) {
					System.out.println("email already exists");
					return null;
				}
				
			}catch(Exception e) {
			}
		
		System.out.println("phone = ");
		phone = in.next();
		System.out.println("password = ");
		pass = in.next();
		while(pass.length() <= 3) {
			System.out.println("minimum password letters allowed 4");
			pass = in.next();
		}
		
		System.out.println("location = ");
		location = in.next();
		System.out.println("country = ");
		country = in.next();
		System.out.println("year = ");
		year = in.nextInt();
		System.out.println("month = ");
		month = in.nextInt();
		System.out.println("day = ");
		day = in.nextInt();
		java.sql.Date dob = new java.sql.Date(year, month, day);
		
		System.out.println("gender = ");
		gender = in.next();
		
		User user = new User();
		
		String m = user.signUp(fname, lname, email, phone, pass, location, country, dob, gender);
		
		return m;
		
	}

	public static String login() {
		
		String email;
		String password;
		
		System.out.println("enter your email: ");
		email = in.next();
		System.out.println("enter your password");
		password = in.next();
		
		User user = new User();
		user.login(email, password);
		
		if(user.login(email, password)) {
			return email;
		}
		
		return null;
	}
	
	public static void friendRequest(String userEmail) {
		
		String personEmail;
		
		System.out.println("enter the person email : ");
		personEmail = in.next();
		
		FriendsManager f = new FriendsManager();
		f.sendFriendRequest(userEmail, personEmail);
		
	}
	
	public static void checkAcceptRejectFriend(String userEmail) {
		
		FriendsManager f = new FriendsManager();
		ArrayList <String> result = new ArrayList();
		
		result = f.checkFriendRequests(userEmail);
		
		if(result != null) {
			
			String choice;
			
			System.out.println("do you accept friend request?  yes/no");
			for(int i = 0 ; i < result.size() ; i++) {
				
				choice = in.next();
				
				if(choice.equals("yes")) {
					f.acceptFriendRequest(userEmail, result.get(i));
				}
				
				else if(choice.equals("no")) {
					f.rejectFriendRequest(userEmail, result.get(i));
				}
			}
		}		
	}	
}
