package adaptiveHuffman;
import java.util.*;


public class codesTree {
	
	private class Node{
		
		
		public Node left;
		public Node right;
		public Node parent;
		
		private int occurences;
		private int order;
		private Character symbol;
		
		
		public boolean isNYT() {return this.occurences == 0;}
		public boolean isInternal() {return this.symbol == null && this.occurences != 0;}
		
		public int getOccurences(){return this.occurences;}
		public void setOccurences(int value) {this.occurences = value;}
		
		public int getOrder() {return this.order;}
		public void setOrder(int value) {this.order = value;}
		
		public int getSymbol() {return this.symbol;}
		public void setSymbol(char value) {this.symbol = value;}
		
		public void increaseOccurences() {this.occurences++;}
		
		
	}
	public Node root;
}
