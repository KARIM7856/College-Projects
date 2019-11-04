package adaptiveHuffman;
import java.util.HashMap;
public class Main {
	public static void main(String[] args) {
		Huffman test = new Huffman();
		
		test.shortCodes = new HashMap<Character, String>();
		test.tree = new CodesTree();
		
		test.shortCodes.put('A', "00");
		test.shortCodes.put('B', "01");
		test.shortCodes.put('C', "10");
		
		System.out.println(test.compress("ABCCCAAAA"));
	}

}
