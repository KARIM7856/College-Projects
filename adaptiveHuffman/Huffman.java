package adaptiveHuffman;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;
public class Huffman {
	
	public HashMap<Character, String> shortCodes;
	
	public CodesTree tree;
	
	
	public String compress(String message) {
		
		String result = "";
		
		for(int i = 0; i < message.length(); i++) {
			if(i == 0) {
				result += shortCodes.get(message.charAt(i));
				tree.root = tree.update(message.charAt(i)); 
				continue;
				
				
				
				
			}
			
			else if(!tree.getNodes().containsKey(message.charAt(i))) {
				result += tree.getCode(null);
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
