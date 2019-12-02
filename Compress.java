import java.util.*;
public class Compress {
	
	public void compress(String word, ArrayList<Double> newProbab, ArrayList<Character> arrayletter) {
		ArrayList <Sequence> ARcode = new ArrayList();
		Double prob;
		Double prob2;
		for(int i = 0 ; i < word.length() ; i++) {
			if(i == 0) {
				Sequence s = new Sequence(word.charAt(i) , newProbab.get(i) , newProbab.get(i+1));
				s.setLower(newProbab.get(i));
				s.setUpper(newProbab.get(i+1));
				s.setRange(newProbab.get(i+1) , newProbab.get(i));
				System.out.println("range = "+ s.getRange());
				System.out.println("lower(" + word.charAt(i) + ")" + newProbab.get(i));
				System.out.println("upper(" + word.charAt(i) + ")" + newProbab.get(i+1));
				System.out.println("***************************************************");
				ARcode.add(s);
			}
			else {
				
				int pos = arrayletter.indexOf(word.charAt(i));
				prob = newProbab.get(pos);
				prob2 = newProbab.get(pos+1);
				Sequence s = new Sequence(word.charAt(i) , prob , prob2);
				Sequence s2 = ARcode.get(i-1);
				s.setRange(s2.getUpper() , s2.getLower());
				System.out.println("range = " + s.getRange());
				System.out.println("lower(" + word.charAt(i) + ")" + s.calculateLower(s2.getLower(), s.getLowerprobability(), s.getRange()));
				System.out.println("upper(" + word.charAt(i) + ")" + s.calculateUpper(s2.getLower(), s.getUpperProbability(), s.getRange()));
				System.out.println("***************************************************");
				s.setLower(s.calculateLower(s2.getLower(), s.getLowerprobability(), s.getRange()));
				s.setUpper( s.calculateUpper(s2.getLower(), s.getUpperProbability(), s.getRange()));
				ARcode.add(s);
			}
			
		}
		
	}
}
