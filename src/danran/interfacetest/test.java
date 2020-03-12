package danran.interfacetest;

public class test {
	
	public static void main(String[] args) {
//		适配器模式
		getPower(new PowerImpl());
		getPower(new Adapter(new PowerBImpl()));
	}
	private static void getPower(PowerA aPowerA) {
		aPowerA.start();
	}
}
