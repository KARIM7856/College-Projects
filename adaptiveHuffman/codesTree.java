package adaptiveHuffman;
import java.util.*;


public class CodesTree {
	
	private int currentIndex;
	private Node NYT;
	public Node root;
	private HashMap<Integer, ArrayList<Node>> blocks;
	
	public CodesTree() {
		this.NYT = new Node();
		this.NYT.setOccurences(0);
		this.NYT.setOrder(currentIndex);
		currentIndex--;
		this.NYT.setSymbol(null);
		
		this.root = this.NYT;
		
		this.currentIndex = 256;
	}
	
	
	public Node splitNYT(Node newNode) {
		Node currentNode = root;
		while(!currentNode.isNYT()) {
			currentNode = currentNode.left;
		}
		
		currentNode.right = newNode;
		currentNode.right.order = currentIndex;
		currentNode.right.parent = currentNode;
		currentIndex--;
		
		currentNode.occurences++;
		
		currentNode.left = new Node();
		currentNode.left = NYT;
		currentNode.left.parent = currentNode;
		currentNode.order = currentIndex;
		currentIndex--;
		return currentNode.right;
	}
	
	private class Node{
		
		
		public Node left;
		public Node right;
		public Node parent;
		
		private int occurences;
		private int order;
		private Character symbol;
		
		public Node() {
			left = null;
			right = null;
			parent = null;
			occurences = 0;
			symbol = null;
		}
		
		public Node(Character value) {
			symbol = value;
			occurences = 1;
		}
		
		public boolean isNYT() {return this.occurences == 0;}
		public boolean isInternal() {return this.symbol == null && this.occurences != 0;}
		
		public int getOccurences(){return this.occurences;}
		public void setOccurences(int value) {this.occurences = value;}
		
		public int getOrder() {return this.order;}
		public void setOrder(int value) {this.order = value;}
		
		public int getSymbol() {return this.symbol;}
		public void setSymbol(Character value) {this.symbol = value;}
		
		public void increaseOccurences() {this.occurences++;}
		
		
	}
	
	public String getCode(Character c) {
		return null;
	}
	
	public void update(Character c) {
		
	}
	
}
