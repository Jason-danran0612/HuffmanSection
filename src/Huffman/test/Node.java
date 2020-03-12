package Huffman.test;

public class Node implements Comparable<Node>{
//	 
	private int weight ;
	private Node leftNode ;
	private Node rightNode ;
	private Byte data ;
	
	public Node() {
	}
	public Node(int weight) {
		this.weight = weight ;
	}
	public Node(int weight, Node leftNode, Node rightNode) {
		this.weight = weight;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	
	
	
	
	public Node(int weight, Node leftNode, Node rightNode, Byte data) {
		this.weight = weight;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.data = data;
	}
	public Node(int weight, Byte data) {
		super();
		this.weight = weight;
		this.data = data;
	}
	@Override
	public String toString() {
		return "Node [weight=" + weight + ", data=" + data + "]";
	}
//	升序排序
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return -this.weight+o.weight;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Node getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
	public Node getRightNode() {
		return rightNode;
	}
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	public Byte getData() {
		return data;
	}
	public void setData(Byte data) {
		this.data = data;
	}
	
	
}
