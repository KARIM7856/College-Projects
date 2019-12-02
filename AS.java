import java.util.*;

public class AS {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int numOfChar;
		char letter;
		Double prob;
		String word;
		ArrayList <Character> arrayletter = new ArrayList <Character> ();
		ArrayList <Double> probability = new ArrayList <Double>();
		System.out.println("enter number of characters");
		numOfChar = in.nextInt();
		System.out.println("enter letter and probability");
		for(int i = 0 ; i < numOfChar ; i++) {
			letter = in.next().charAt(0);
			arrayletter.add(letter);
			prob = in.nextDouble();
			probability.add(prob);
		}
		ArrayList <Double> newProbab = new ArrayList <Double>();
		newProbab.add(0.0);
		ArrayList <Character> checkChar = new ArrayList <Character>();
		System.out.println("enter a word to compress");
		Double prob2 = 0.0;
		word = in.next();
		for(int i = 0 ; i < probability.size() ; i++) {
			/*int pos = arrayletter.indexOf(word.charAt(i));
			letter = arrayletter.get(pos);
			prob = probability.get(pos);
			if(i == 0) {
				prob2 += prob;
				newProbab.add(prob);
				checkChar.add(letter);
			}
			else if(!checkChar.contains(letter)) {
				 pos = arrayletter.indexOf(word.charAt(i-1));
				 prob2 += prob;
				 newProbab.add(prob2);
				 checkChar.add(letter);
			}
			else if(checkChar.contains(letter)) {
			    pos = arrayletter.indexOf(word.charAt(i));
				prob = probability.get(pos);
				newProbab.add(prob);
				checkChar.add(letter);
			}
			*/
			prob = probability.get(i);
			if(i == 0) {
				prob2 += prob;
				newProbab.add(prob);
			}
			else {
				prob2 += prob;
				newProbab.add(prob2);
			}
			
			
		}
		
		Compress code = new Compress();
		
		code.compress(word, newProbab, arrayletter);
		
		

	}
	

}
