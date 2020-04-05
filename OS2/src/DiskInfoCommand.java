
public class DiskInfoCommand implements Command {

	@Override
	public void execute() {
		System.out.println( Disc.getInstance().getDiskStatus() );

	}

}
