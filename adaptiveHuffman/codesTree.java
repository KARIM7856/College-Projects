package adaptiveHuffman;
import java.util.*;




public class CodesTree {
	
	private int currentIndex;
	private Node NYT;
	public Node root;
	private HashMap<Character, Node> nodes;
	private HashMap<Integer, ArrayList<Node>> blocks;
	
	private boolean isFirst;
	
	
	public CodesTree() {
		this.currentIndex = 256;
		this.NYT = new Node();
		this.NYT.setOccurences(0);
		this.NYT.setOrder(currentIndex);
		currentIndex--;
		this.NYT.setSymbol(null);
		
		this.root = this.NYT;
		
		
		nodes = new HashMap<Character, Node>();
		blocks = new HashMap<Integer, ArrayList<Node>>();
		
		this.isFirst = true;
	}
	
	public HashMap<Character, Node> getNodes(){
		return nodes;
	}
	
	
	public Node splitNYT(Node newNode) {

		
		Node newInternal = NYT;
		newInternal.left = new Node();
		NYT = newInternal.left;
		
		
		
		newInternal.right = newNode;
		newInternal.right.order = currentIndex;
		newInternal.right.parent = newInternal;
		currentIndex--;
		
		newInternal.occurences++;
		NYT.parent = newInternal;
		NYT.order = currentIndex;
		currentIndex--;
		
		if(isFirst) {
			newInternal.isRoot = true;
			isFirst = false;
		}
		else {
			if(!blocks.containsKey(newInternal.occurences)) {
				blocks.put(newInternal.occurences, new ArrayList<Node>());
				
			}
				
			blocks.get(newInternal.getOccurences()).add(newInternal);
		}
			
		return newInternal.right;
	}
	
	private ArrayList<Node> getPossibleSwaps(Node nodeToCheck){
		ArrayList<Node> possibleSwaps = new ArrayList<Node>();
		
		if(nodeToCheck ==  null) {
			return null;
		}
			
			for(Node j : blocks.get(nodeToCheck.getOccurences())) {
				if(shouldSwap(nodeToCheck, j)){
					possibleSwaps.add(j);
				}
			}
			

		
		return possibleSwaps;
	}
	
	private Node getSwapNode(Node nodeToCheck) {
		
		ArrayList<Node> p = getPossibleSwaps(nodeToCheck);
		
		p.sort(new compare());
		
		
		if(!p.isEmpty())
			return p.get(0);
		return null;
		
	}
	
	public boolean shouldSwap(Node i, Node j) {
		return (j.getOccurences() <= i.getOccurences())
				&& (j.getOrder() > i.getOrder())
				&& (j != i.parent);
	}
	
	class compare implements Comparator<Node>{
	
		@Override
		public int compare(Node o2, Node o1) {
			
			return o1.getOrder() - o2.getOrder();
		}
		
	}

	public String getCode(Character c) {
		Node current;
		StringBuilder result = new StringBuilder();
		if(c == null) {
			current = NYT;
		}
		else {
			current = nodes.get(c);
			if(current == null)
				return null;
		}
		
		while(current != root) {
			if(current.parent.left == current)
				result.append("0");
			else if(current.parent.right == current)
				result.append("1");
			
			current = current.parent;
		}
		
		return result.reverse().toString();
	}

	public Node update(Character c) {
		
		Node current = new Node();
		boolean contains = false;
		
		if(nodes.containsKey(c)) {
			contains = true;
			current = nodes.get(c);
		}
		else {
			contains = false;
			
			Node newNode = new Node(c);
			
			nodes.put(c, newNode);
			if(blocks.containsKey(newNode.occurences))
				blocks.get(newNode.occurences).add(newNode);
			else {
				blocks.put(newNode.occurences, new ArrayList<Node>());
				blocks.get(newNode.occurences).add(newNode);
			}
			
			current = splitNYT(newNode);
			
		}
		
		while(!current.isRoot) {
			
			
			if(contains) {
		
					Node nodeToSwap = getSwapNode(current);
					
					if(nodeToSwap != null) {
						swapNodes(current, nodeToSwap);
						
						blocks.get(current.getOccurences()).remove(current);
						
						current.increaseOccurences();
						
						if(!blocks.containsKey(current.getOccurences()))
							blocks.put(current.getOccurences(), new ArrayList<Node>());
						
						blocks.get(current.getOccurences()).add(current);
						
						
					}
					else if(current.isInternal()) {
						current.setOccurences(current.left.getOccurences()
								+ current.right.getOccurences());
					}
					else if (!current.isInternal()) {
						
						
						current.increaseOccurences();
					}
					
			}
			else {
				if(current.isInternal() && current.left != NYT) {
					
					Node nodeToSwap = getSwapNode(current);
					if(nodeToSwap != null) {
						swapNodes(current, nodeToSwap);
						
						blocks.get(current.getOccurences()).remove(current);
						current.increaseOccurences();
						
						
						
						if(!blocks.containsKey(current.getOccurences()))
							blocks.put(current.getOccurences(), new ArrayList<Node>());
						
						blocks.get(current.getOccurences()).add(current);
						
						
					}
				}
			}

			current = current.parent;
		}
		
		System.out.println("A : " + this.getCode('A'));
		System.out.println("B : " + this.getCode('B'));
		System.out.println("C : " + this.getCode('C'));

		
		return current;
	}

	private void swapNodes(Node current, Node nodeToSwap) {
		Node tempParent;
		int tempOrder;
		
		blocks.get(current.getOccurences()).remove(current);
		blocks.get(nodeToSwap.getOccurences()).remove(nodeToSwap);
		
		
		
		boolean currentOnLeftOfParent = current.parent.left == current;
		boolean nodeToSwapOnLeftOfParent = nodeToSwap.parent.left == nodeToSwap;

		if(currentOnLeftOfParent && nodeToSwapOnLeftOfParent) {
			current.parent.left = nodeToSwap;
			nodeToSwap.parent.left = current;
		}
		else if(currentOnLeftOfParent && !nodeToSwapOnLeftOfParent) {
			current.parent.left = nodeToSwap;
			nodeToSwap.parent.right = current;
		}
		else if(!currentOnLeftOfParent && nodeToSwapOnLeftOfParent) {
			current.parent.right = nodeToSwap;
			nodeToSwap.parent.left = current;
		}
		else {
			current.parent.right = nodeToSwap;
			nodeToSwap.parent.right = current;
		}
		
		tempParent = current.parent;
		current.parent = nodeToSwap.parent;
		nodeToSwap.parent = tempParent;
		
		tempOrder = current.getOrder();
		current.setOrder(nodeToSwap.getOrder());
		nodeToSwap.setOrder(tempOrder);
		
		if(!current.isInternal()) {
			nodes.replace((char)current.getSymbol(), current);
		}
		if(!nodeToSwap.isInternal()) {
			
			nodes.replace((char)nodeToSwap.getSymbol(), nodeToSwap);
		}
		
		blocks.get(current.getOccurences()).add(current);
		blocks.get(nodeToSwap.getOccurences()).add(nodeToSwap);
		
		
		
	}

	private class Node{
		
		
		public Node left;
		public Node right;
		public Node parent;
		
		private int occurences;
		private int order;
		private Character symbol;
		private boolean isRoot;
		private boolean isNYT;
		
		public Node() {
			left = null;
			right = null;
			parent = null;
			occurences = 0;
			symbol = null;
			isRoot = false;
			isNYT = true;
		}
		
		public Node(Character value) {
			symbol = value;
			occurences = 1;
			left = null;
			right = null;
			parent = null;
		}
		
		public boolean isNYT() {return this == NYT;}
		public boolean isInternal() {return this.symbol == null && this.occurences != 0;}
		
		public int getOccurences(){return this.occurences;}
		public void setOccurences(int value) {this.occurences = value;}
		
		public int getOrder() {return this.order;}
		public void setOrder(int value) {this.order = value;}
		
		public int getSymbol() {return this.symbol;}
		public void setSymbol(Character value) {this.symbol = value;}
		
		public void increaseOccurences() {this.occurences++;}
		
		
	}
	
}
