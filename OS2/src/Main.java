import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Disc.initialize("C", 1000);
		String cmd;
		
		OperatingSystem os = new OperatingSystem("C");
		
		Scanner sc = new Scanner(System.in);
		
		cmd =  sc.nextLine();
		
		while(cmd != "quit") {
			os.execute(cmd);
			cmd = sc.nextLine();
		}

	}

}
