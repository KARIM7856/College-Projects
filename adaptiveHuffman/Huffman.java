package adaptiveHuffman;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class Huffman {
	private ArrayList<Character> characters;
	private Map<Character, String> shortCodes;
	
	CodesTree tree;
	
	
	public String compress(String message) {
		
		String result = "";
		
		for(int i = 0; i < message.length(); i++) {
			if(i == 0) 
				result += shortCodes.get(message.charAt(i));
			
			else if(!characters.contains(message.charAt(i))) {
				result += "0";
				result += shortCodes.get(message.charAt(i));
				
			}
			else {
				result += tree.getCode(message.charAt(i));
			}
			
			tree.update(message.charAt(i));
		}
		return result;
	}
}
