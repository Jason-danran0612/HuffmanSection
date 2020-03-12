package danran.test;

import java.io.*;

/**
 * java.lang.Runtime 每个 Java 应用程序都有一个 Runtime 类实例 ,使应用程序能够与其运行的环境相连接。
 * 
 * 可以通过getRuntime 方法获取当前运行时, 应用程序不能创建自己的 Runtime 类实例
 * 
 * 通过Process可以控制该子进程的执行或获取该子进程的信息。第二条语句的目的等待子进程完成再往下执行
 */
public class ExecDOS {

	/**
	 * 执行DOS的内部命令
	 * 
	 * @param command
	 *            一般应用程序或系统的cmd命令
	 * 
	 */
	public void execDOS(String command) {
		try {
			Runtime.getRuntime().exec("cmd /c " + command);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * 执行一个有标准输出的DOS可执行程序
	 * 
	 * @param command
	 *            具有输出的cmd命名
	 */
	public static String execDOStoOutPut(String command) {
		String result = "rrrr";
		InputStream is = null;
		InputStreamReader ir = null;
		BufferedReader bufferedReader = null;
		try {
			Process process = Runtime.getRuntime().exec("cmd /c " + command);
			// 创建一个使用默认大小输入缓冲区的缓冲字符输入流
			is = process.getInputStream();
			ir = new InputStreamReader(is);
			bufferedReader = new BufferedReader(ir);
			String temp;
			while ((temp = bufferedReader.readLine()) != null) {
				result += temp;
				System.out.println(temp);
			}
			process.waitFor();
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				bufferedReader.close();
				ir.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ExecDOS.execDOStoOutPut("ipconfig");
		System.out.println(ExecDOS.execDOStoOutPut("ipconfig"));
		System.out.println("ghgfd");
	}
}

