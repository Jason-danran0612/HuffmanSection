package danran.iostream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
	public static void main(String[] args) {

		FileReadtest();
		FileWritetest();
	}
	/**
	 * 从桌面的文件test.txt中读取文本数据，并将其文本显示在控制台中。
	 */
	private static void FileReadtest() {
		String string = null;
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\ASUS\\Desktop\\test.txt"))) {
			while ((string = bufferedReader.readLine()) != null) {
				System.out.println(string);
			}
		} catch (IOException ex) {
			// TODO: handle exception
			System.out.println(ex);
		}
	}
	private static void FileWritetest() {
		String string = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("输入‘<EOFI>’结束输入！！");
		try (FileWriter fileWriter = new FileWriter("C:\\Users\\ASUS\\Desktop\\test.txt",true)){
			do {
				System.out.print(":");
				string  = bufferedReader.readLine();
				if (string.equals("<EOFI>")) {
					break;
				}
				string  = string + "\r\n";
				fileWriter.write(string);
			} while (!string.equals("<EOFI>"));
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
