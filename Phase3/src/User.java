import java.sql.Date;
import java.util.ArrayList;

public class User {
	
	public static final String NULL = null;
	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private String location;
	private String country;
	private Date date_of_birth;
	private String gender;
	
	public static Notifications n = new Notifications();
	public FriendsManager f = new FriendsManager();
	
	
	public boolean login (String email, String pass) {
		
		boolean status = true;
		
		try {
			
			DataBaseManager connect = new DataBaseManager();
			String query = "SELECT * FROM Users WHERE email = '" + email + "'AND password = '" + pass + "'";
			connect.rs = connect.st.executeQuery(query);
			connect.rs.first();
			String fname = connect.rs.getString("firstName");
			String lname = connect.rs.getString("lastName");
			System.out.println();
			n.popUp("Welcome " + fname + " " + lname);
			System.out.println("**********************************");
			
			
		}catch(Exception e) {
			System.out.println("email or password is wrong : " + e);
			status = false;
		}
		
		return status;
	}
	
	public void logout () {
	}
	
	public String signUp (String fname,String lname, String email, String phone, String password, String location, String country, Date dob, String gender) {
		
		
		try {
			
			DataBaseManager connect = new DataBaseManager();
			String query = "INSERT INTO Users (userID, firstName, lastName, email, phone, password, location, country, dateOfBirth, gender) VALUES ("+ NULL +",'" + fname + "','" + lname + "','" + email + "','" + phone + "','" + password + "','" + location + "','" + country + "','"+ dob +"','" + gender + "')";
			int result = connect.st.executeUpdate(query);
			System.out.println("*********************************");
			System.out.println();
		
			if(result == 1) {
				n.popUp("sign up is successfull");
			}
		
			System.out.println("*********************************");
			System.out.println();
			return email;
		
			}	catch(Exception e){
				System.out.println(e);
			}
			return email;
		}
	
	
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	
}


