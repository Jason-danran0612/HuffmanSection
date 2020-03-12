package danran.test;


public class cianc implements Runnable{
	private String Name; 
	private Thread thread;
	public cianc(String name) {
		super();
		Name = name;
		this.thread = new Thread(this, name);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	/**
	 *功能描述：
	 * @param name
	 *@return cianc
	 *@version 1.0
	 *@Author Jason
	 *@Date 2020-03-12-16:42
	 */
	public static cianc CreateAndStart(String name) {
		cianc testCianc = new cianc(name);
		testCianc.thread.start();
		return testCianc;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			System.out.print(i +"\t");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
