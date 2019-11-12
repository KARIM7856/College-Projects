package adaptiveHuffman;
import java.util.ArrayList;
import java.util.Set;


import java.util.HashMap;
public class Huffman {
	
	public Huffman() {
		shortCodes = new HashMap<Character, String>();
		tree = new CodesTree();
	}
	
	
	public HashMap<Character, String> shortCodes;
	
	
	public CodesTree tree;
	
	
	public void getShortCodes(String message) {
		for(int i = 0; i < 256; i++) {
			shortCodes.put(Character.valueOf((char)i), String.format("%" + 8 + "s", 
					Integer.toBinaryString((char)i
					& 0xff)).replace(' ', '0'));
		}
			
		
	}
	
	
	public String compress(String message) {
		
		String result = "";
		
		getShortCodes(message);
		
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
	
	public String deCompress(String message) {
		getDeCompressShortCodes();
		
		Set<Character> characters = shortCodes.keySet();
		
		String parse = new String();
		String result = new String();
		int shortCodeIndex = 0;
		int resultIndex = 0;
		for(int i = 0; i < message.length(); i++) {
			if(i == 0) {
				parse = message.substring(0,8);
				result += characters.toArray()[Integer.parseInt(parse, 2)];
				i += shortCodes.get(result.charAt(0)).length()-1;
				tree.update(result.charAt(0));
				shortCodeIndex++;
				resultIndex++;
				parse = "";
				continue;
				
			}
			else {
				parse += message.charAt(i);
				
				if(parse.equals(tree.getCode(null))) {
					parse = message.substring(i+1,i+9);
					result += characters.toArray()[Integer.parseInt(parse, 2)];
					i += shortCodes.get(result.charAt(0)).length();
					tree.update(result.charAt(resultIndex));
					shortCodeIndex++;
					resultIndex++;
					parse = "";
				}
				else {
					for(Character j : characters) {
						if(parse.equals(tree.getCode(j))) {
							result += j;
							tree.update(j);
							resultIndex++;
							parse = "";
						}
					}
				}
			}
			
		}
		
		return result;
	}


	private void getDeCompressShortCodes() {
		for(int i = 0; i < 256; i++) {
			shortCodes.put(Character.valueOf((char)i), String.format("%" + 8 + "s", 
					Integer.toBinaryString((char)i
					& 0xff)).replace(' ', '0'));
		}
			
		
	}
}
