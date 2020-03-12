package Huffman.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {
	private static StringBuilder sb = new StringBuilder();
//	用于存储应用huffman编码算法形成的编码表
	private static Map<Byte, String> huffmanCodeMap = new HashMap<Byte, String>();
	
	public static void main(String[] args) {
		HUFFMAN();
	}
	
	/**
	 * 主要测试方法 
	 */
	private static void HUFFMAN() {
		String msg = "can you can a can as a can canner can a can?";
		byte[] bytes = msg.getBytes();
		System.out.println(new String(bytes));
		byte[] ziped_byte = HuffmanZip(bytes);
		byte[] unzip_byte = UnHuffmanzip(huffmanCodeMap,ziped_byte);
		String str = new String(unzip_byte);
		System.out.println(str);
		
//		String src1="1.mp4";
//		String dst="video.zip";
//		String src3="video.mp4";
		
//		String src1="1.bmp";
//		String dst="2.zip";
//		String src3="55.bmp";
		String src1="1.mp3";
		String dst="music.zip";
		String src3="music.mp3";
		try {
			zipFile(src1, dst);
			unzipFile(dst,src3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 用于对指定的文件进行压缩
	 * @param src
	 * @param dst
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private static void unzipFile(String src, String dst) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		InputStream is = new FileInputStream(src);
		ObjectInputStream oS = new ObjectInputStream(is);
//		从文件输入流中读取压缩文件的byte信息数组
		byte[] b = (byte[]) oS.readObject();
//		读取huffman编码表，用于进行解码
		Map<Byte, String> codesMap = (Map<Byte, String>) oS.readObject();
		oS.close();
		is.close();
//		解压缩文件，就是解码
		byte[] newbyte = UnHuffmanzip(codesMap, b);
		OutputStream oStream = new FileOutputStream(dst);
//		向外部输出文件信息
		oStream.write(newbyte);
		oStream.close();
		System.out.println("解压缩成功");
	}
	/**
	 * 用于进行文件的读取压缩
	 * @param src
	 * @param dst
	 * @return
	 * @throws IOException
	 */
	private static void zipFile(String src,String dst) throws IOException {
		InputStream is = new FileInputStream(src);
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		is.close();
		byte[] ziped_byte = HuffmanZip(bytes);
		System.out.println("压缩比  =   "+(1-ziped_byte.length*1.0/bytes.length*1.0)*100);
		OutputStream os = new FileOutputStream(dst);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(ziped_byte);
		oos.writeObject(huffmanCodeMap);
		oos.close();
		os.close();
		System.out.println("压缩成功");
	}
	/**
	 * 
	 * @param huffmanCodeMap2
	 * @param ziped_byte
	 * @return
	 */
	private static byte[] UnHuffmanzip(Map<Byte, String> CodeMap, byte[] ziped_byte) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		List<Byte> list = new ArrayList<Byte>();
//		for (byte b : ziped_byte) {
////			sb.append(byteToString(b));
////			System.out.println(Integer.toBinaryString(b));
//		}
		for (int i = 0; i < ziped_byte.length; i++) {
//			if (i!=ziped_byte.length-1) {
//				sb.append(byteToString(true,ziped_byte[i]));
//			}
//			sb.append(byteToString(i!=ziped_byte.length-1, ziped_byte[i]));
			byte b = ziped_byte[i];
			boolean flag = (i==ziped_byte.length-1);
			sb.append(ByteTobitStr(!flag,b));
//			sb.append(byteToString(!flag, b));
		}
//		System.out.println(sb.toString());
		Map<String, Byte> DecodeMap = new HashMap<String, Byte>();
		for(Map.Entry<Byte, String> mapEntry : CodeMap.entrySet()) {
			DecodeMap.put(mapEntry.getValue(), mapEntry.getKey());
		}
		int start = 0;
		int end = 0;
		for (int i = 0; i < sb.length();i++) {
			StringBuilder sb2 = new StringBuilder();
			
			sb2.append(sb.substring(start,end+1));
//			System.out.println(sb2.toString());
			if (DecodeMap.get(sb2.toString())==null) {
				end++;
			}else {
				list.add(DecodeMap.get(sb2.toString()));end++;start=end;
			}
		}
		byte[] b = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}
	/**
	 * 将给定的byte 转换成对应的8位二进制字符串
	 * @param flag
	 * @param b
	 * @return 2进制解码字符串
	 */
	private static String ByteTobitStr(boolean flag, byte b) {
		int temp = b;
		if (flag) {
			temp|=256;
		}
		String str1 = Integer.toBinaryString(temp);
		if (flag) {
			return str1.substring(str1.length()-8);
		}else {
			return str1;
		}
	}
	/**
	 *将给定的byte 转换成对应的8位二进制字符串
	 * @param b
	 * @return
	 */
	private static String byteToString(boolean flag,byte b) {
		// TODO Auto-generated method stub
		int temp = b;
//		String s = null;
		if (flag) {
			temp|=256;
		}
		String string = Integer.toBinaryString(temp);
		if (flag) {
			string = string.substring(string.length()-8);
		}else {
			string = Integer.toBinaryString(temp);
		}
		return string;
	}
	/**
	 * 用于返回用huffman编码好的byte[]数组
	 * @param bytes
	 * @return
	 */
	private static byte[] HuffmanZip(byte[] bytes) {
//		统计bytes 数组的字符数量，统计字符 的权重
		List<Node> nodes = getNodes(bytes);
//		构造一个huffmantree
		Node node = createHuffmanTree(nodes);
//		下面进行huffman编码
//		Map<Byte, String> CodeMap = huffmanCode(node);
		huffmanCode(node);
//		System.out.println(huffmanCodeMap);
		byte[] zip_bytes = zipBytes(bytes);
//		System.out.println("压缩率  =   "+(1-zip_bytes.length*1.0/bytes.length*1.0)*100);
		return zip_bytes;
	}
	/**
	 * 根据已经创建好的huffmanCodeMap进行bytes数组的压缩
	 * @param huffmanCodeMap2
	 * @param bytes
	 * @return ziped bytes
	 */
	private static byte[] zipBytes(byte[] bytes) {
		// TODO Auto-generated method stub
		StringBuilder sb1 = new StringBuilder();
//		循环遍历bytes 数组，从Map中得到对应的编码，然后替换，形成新的二进制字符串
		for (byte b : bytes) {
			if (huffmanCodeMap.get(b) != null) {
				sb1.append(huffmanCodeMap.get(b));
			}
		}
//		System.out.println(sb1);
//		System.out.println(huffmanCodeMap);
		int len = 0;
//		判断二进制字符串的长度，用于确定新的byte数组的长度
		if (sb1.length() % 8 == 0) {
			len = sb1.length() / 8 ;
		}else {
			len = sb1.length() / 8 + 1;
		}
		int index = 0;//z标识符
		byte[] b = new byte[len];
		for (int i = 0; i < sb1.length(); i += 8) {
			String str = null;
//			字符串按照8位进行截取，用于生产新的byte
			if (sb1.length() % 8 == 0) {
				str = sb1.substring(i, i + 8);
			} else {
//				记录不能整除的开始位置
				int flag = sb1.length() - sb1.length() % 8;
				if (i + 8 <= flag) {
					str = sb1.substring(i, i + 8);
				} else {
					str = sb1.substring(i);
				}
			}
//			System.out.println(str);
			byte b1 = (byte) Integer.parseInt(str, 2);
//			System.out.println(str + "::" + b1);
			b[index++] = b1;
		}
		return b;
	}
	/**
	 * 根据传入的根节点进行huffman树的编码
	 * @param node
	 * @return
	 */
	private static Map<Byte, String> huffmanCode(Node node) {
		if (node == null) {
			return null;
		}
//		System.out.println("访问根左边");
		getHuffmanCode(node.getLeftNode(),"0",sb);
//		System.out.println("访问根右边");
		getHuffmanCode(node.getRightNode(),"1",sb);
//		Map<Byte, String> huffmanCodeMap = new HashMap<Byte, String>();
////		说明了该结点不是叶子节点
//		if (node.getData()==null) {
////			查看他的左右节点
//			sb.append("0");
//			huffmanCode(node.getLeftNode());
//			sb.append("1");
//			huffmanCode(node.getRightNode());
//		}else {
//			huffmanCodeMap.put(node.getData(), sb.toString());
//			sb = new StringBuilder();
//		}
		return huffmanCodeMap;
	}
	/**
	 * 方法用于从huffmantree中得到huffmantree中得到huffmanCodeMap
	 * @param node
	 * @param string
	 * @param sb
	 */
	private static void getHuffmanCode(Node node, String string, StringBuilder sb) {
		// TODO Auto-generated method stub
		if (node==null) {
			return ;
		}
//		sb.append(string);
		StringBuilder sb2 = new StringBuilder(sb);
		sb2.append(string);
//		System.out.println("sb= "+sb.toString()+",\n"+"sb2="+sb2.toString());
		if (node.getData() != null) {
			huffmanCodeMap.put(node.getData(), sb2.toString());
//			System.out.println("返回,存入"+node.getData()+"---"+sb2.toString());
		}else {
//			System.out.println("访问左边");
			getHuffmanCode(node.getLeftNode(), "0",sb2);
//			System.out.println("访问右边");
			getHuffmanCode(node.getRightNode(), "1", sb2);
		}
	}
	/**
	 * 用于来根据传入的nodes进行对应huffmantree的创建，并且返回
	 * huffmantree的根节点
	 * @param nodes
	 * @return
	 */
	private static Node createHuffmanTree(List<Node> nodes) {
//		当nodes的size>1的时候，说明了集合当中的结点不止1个，
//		就可以拿出来继续构建huffman树的节点，==1的时候就说明集合当中就是根节点
		while(nodes.size()>1) {
//			对nodes集合进行排序，升序
			Collections.sort(nodes);
//			从排序后的list集合中抽取两个权重最小的结点
//			构造出 新的结点放入到集合中
			Node leftNode = nodes.get(nodes.size()-1);
			Node rightNode = nodes.get(nodes.size()-2);
//			Node newNode = new Node(leftNode.getWeight()+rightNode.getWeight(),null);
			Node newNode = new Node(leftNode.getWeight()+rightNode.getWeight(), leftNode, rightNode, null);
			
			nodes.remove(rightNode);
			nodes.remove(leftNode);
			nodes.add(newNode);
		}
		return nodes.get(0);
	}
	/**
	 *  将传入的bytes 数组包装成Node节点，放入到集合中返回
	 *  包装节点中讲各个字符出现的权重统计出来
	 * @param bytes
	 * @return
	 */
	private static List<Node> getNodes(byte[] bytes) {
//		用于进行临时的结点统计映射
		Map<Byte, Integer> nodeMap = new HashMap<Byte, Integer>();
		int index =0;
//		构造出一个临时的List集合，用于存储包装节点
		List<Node> nodes = new ArrayList<Node>();
		for(byte b : bytes) {
//			flag 用于存储字节出现的次数
			Integer flag = nodeMap.get(b);
//			记录读取到的字节数
			index++;
//			如果flag == null 则是说明了在映射关系图中不存在该字符
//			于是就将该字符添加到映射图中去
			if (flag==null) {
				nodeMap.put(b, 1);
			}else {
//				说明了映射图中已经存在，就将他的value 值加1
				nodeMap.put(b, flag+1);
			}
		}//End of for
		for (Map.Entry<Byte, Integer> entry	 :nodeMap.entrySet() ) {
			nodes.add(new Node(entry.getValue(), entry.getKey()));
		}//End of for
		System.out.println("重复字节数== "+nodes.size()+"\t---字节数=="+index);
		return nodes;
	}
}
