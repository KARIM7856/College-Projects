import java.sql.*;

public class DataBaseManager {
	
	//private String SQLconnection;
	
	public Connection con;
	public Statement st;
	public ResultSet rs;
	
	public User u = new User();
	public Group g = new Group();
	public Post p = new Post();
	public Page pa = new Page();
	public Profile prof = new Profile();
	public Notifications n = new Notifications();
	
	public DataBaseManager() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Software","root","");
			st = con.createStatement();
			
		}catch(Exception ex) {
			
			System.out.println("Error: " + ex);
		}
	}
	
	public void getData() {
		
		try {
			
			String query = "select * from Users";
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				
				int userID = rs.getInt("userID");
				String fname = rs.getString("firstName");
				String lname = rs.getString("lastName");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String pass = rs.getString("password");
				String location = rs.getString("location");
				String country = rs.getString("country");
				Date dob = rs.getDate("dateOfBirth");
				String gender = rs.getString("gender");
				
				System.out.println("user ID = " + userID);
				System.out.println("first name = " + fname);
				System.out.println("last name = " + lname);
				System.out.println("email = " + email);
				System.out.println("phone = " + phone);
				System.out.println("password = " + pass);
				System.out.println("location = " + location);
				System.out.println("country = " + country);
				System.out.println("date of birth = " + dob);
				System.out.println("gender = " + gender);
				System.out.println();
				System.out.println("*********************************************");
				System.out.println();
				
			}
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
		
	}
	
}
