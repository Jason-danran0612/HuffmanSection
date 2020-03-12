package danran.test;


public class test {
	/**
	 *功能描述：
	 * @param null
	 *@return 
	 *@version 1.0
	 *@Author Jason
	 *@Date 2020-03-12-16:50
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Raunnable接口实现线程");
//		cianc testCianc = new cianc("线程");
//		Thread thread = new Thread(testCianc);
//		thread.start();
		
		cianc testCianc = cianc.CreateAndStart("csdgsg");
		Thread.sleep(10000);
		System.out.println(testCianc.getName());
		lsjgl llLsjgl = new lsjgl();
		System.out.println(lsjgl.list.toString());
	}
}
