
public class PremiumUser extends User{
	
	public void addPremiumUser(String email) {
		
		try {
		DataBaseManager connect = new DataBaseManager();
		String query = "SELECT * FROM Users WHERE email = '" + email + "'";
		connect.rs = connect.st.executeQuery(query);
		connect.rs.first();
		
		int credit = connect.rs.getInt("creditCard");
		
		if(credit >= 99) {
			
			credit = credit - 99;
			String query2 = "UPDATE Users SET creditCard = '" + credit + "' WHERE email = '" + email + "'";
			int result = connect.st.executeUpdate(query2);
			String query3 = "UPDATE Users SET premiumUsers = 'premium user' WHERE email = '" + email + "'";
			int result2 = connect.st.executeUpdate(query3);
			System.out.println("transaction is done you are now a premium user");
			
		}
		
		else {
			System.out.println("not enough money");
		}
		
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
