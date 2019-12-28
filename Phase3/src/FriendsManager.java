import java.util.ArrayList;

public class FriendsManager {
	private String name;
	private int userID;
	private String message;
	private static final String NULL = null;
	
	public static Notifications n = new Notifications();
	
	
	public boolean sendFriendRequest (String userEmail , String personEmail) {
		boolean choice = true;
		
		try {
			ArrayList <String> fi = new ArrayList();
			String friends = checkFriends(userEmail);
			String friendd = "";
			for(int i = 0 ; i < friends.length() ; i++) {

				if(friends.charAt(i) != ',' && i != friends.length() && friends.charAt(i) != ' ') {
					friendd += friends.charAt(i);
				}
				
				if(friends.charAt(i) == ',' || i == friends.length() - 1) {
					if(friendd.equals(personEmail)) {
						n.popUp("is already your friend");
						return false;
					}
					
					fi.add(friendd);
					friendd = "";
					
				}
			}
			
			String request = getFriendRequest(personEmail);
			String newPerson = userEmail;
			
			if(request != null) {
				newPerson += ", " + request;
			}
			
			DataBaseManager connect = new DataBaseManager();
			String query = "UPDATE Users SET friendRequests = '" + newPerson + "' WHERE email = '" + personEmail + "'";
			int result = connect.st.executeUpdate(query);
			
			System.out.println("*********************************");
			System.out.println();
			
			if(result == 1) {
				n.popUp("friend request sent");
			}
			
			else {
				n.popUp("user not found");
			}
			
			System.out.println("*********************************");
			System.out.println();
			
		}catch(Exception e) {
			System.out.println("user not found " + e);
		}
		
		return choice;
	}
	
	
	

	public String getFriendRequest(String email) {
		
		String result = NULL;
		
		try {
			
			DataBaseManager connect = new DataBaseManager();
			String query = "SELECT * FROM Users WHERE email = '" + email + "'";
			connect.rs = connect.st.executeQuery(query);
			connect.rs.first();
			result = connect.rs.getString("friendRequests");
			
			return result;
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	
	
	public ArrayList<String> checkFriendRequests(String email) {
		
		try {
			
			DataBaseManager connect = new DataBaseManager();
			String query = "SELECT * FROM Users WHERE email = '" + email +"'";
			connect.rs = connect.st.executeQuery(query);
			connect.rs.first();
			
			
				
			String request = connect.rs.getString("friendRequests");
			String request2 = "";
			ArrayList <String> arrayRequests = new ArrayList();

			if(request == NULL) {
				System.out.println();
				n.popUp(" ther is no friend requests");
				System.out.println("*************************************");
				
				return null;
			}
			else if(request != NULL) {
				
				for(int i = 0 ; i < request.length() ; i++) {
					
					if(request.charAt(i) != ',' && i != request.length() && request.charAt(i) != ' ') {
						request2 += request.charAt(i);
					}
					
					if(request.charAt(i) == ',' || i == request.length() - 1) {
						
						arrayRequests.add(request2);
						n.popUp(request2 + " sent friend request");
						request2 = "";
						
					}

				}
				
			}
			return arrayRequests;
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
		
	}
	
	


	public void acceptFriendRequest(String userEmail, String personEmail) {
		
		try {
			
			String request = checkFriends(userEmail);
			
			String newPerson = personEmail;
			String friend = checkFriends(personEmail);
			
			if(request != null) {
				newPerson += ", " + request;
			}
			
			if(friend != null) {
				personEmail += ", " + friend;
			}
			
			DataBaseManager connect = new DataBaseManager();
			String query = "UPDATE Users SET friends = '" + newPerson + "' WHERE email = '" + userEmail + "'";
			String query2 = "UPDATE Users SET friendRequests = " + NULL + " WHERE email = '" + userEmail + "'";
			String query3 = "UPDATE Users SET friends = '" + userEmail + "' WHERE email = '" + personEmail + "'";
			int result = connect.st.executeUpdate(query);
			connect.st.executeUpdate(query2);
			connect.st.executeUpdate(query3);
			
			if(result == 1) {
				System.out.println();
				n.popUp("you and " + personEmail + " are now friends");
				System.out.println("*******************************************");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	

	public void rejectFriendRequest(String userEmail, String personEmail) {
		
		try {
			
			DataBaseManager connect = new DataBaseManager();
			String query = "UPDATE Users SET friendRequests = '" + null + "' WHERE email = '" + userEmail + "'";
			int result = connect.st.executeUpdate(query);
			
			if(result == 1) {
				System.out.println();
				n.popUp(personEmail + " friend request rejected");
				System.out.println("****************************************");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	
	public String checkFriends(String email) {
		
		String result = NULL;
		
		try {
			
			DataBaseManager connect = new DataBaseManager();
			String query = "SELECT * FROM Users WHERE email = '" + email + "'";
			connect.rs = connect.st.executeQuery(query);
			connect.rs.first();
			result = connect.rs.getString("friends");
			
			return result;
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	
	public void removeFriend(String name) {
		this.name = name;
	}
	

	public void messageFriend(String text) {
		this.message = text;
	}
	
	public void goToProfile() {}
}
