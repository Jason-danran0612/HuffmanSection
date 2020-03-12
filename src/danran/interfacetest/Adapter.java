package danran.interfacetest;

public class Adapter implements PowerA{
	private PowerB bpower ;
	
	public Adapter(PowerB bpower) {
		super();
		this.bpower = bpower;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		bpower.work();
	}
	
}
