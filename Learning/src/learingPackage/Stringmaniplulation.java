package learingPackage;

public class Stringmaniplulation {
	
//	find the length
	
	public void length() {
		String name ="Programming";
		System.out.println(name.length());
	}
	
//	Print Each Character of a String
	public void eachcharacter() {
		String name ="Java";
		for(int i=0; i<4;i++) {
			System.out.println(name.charAt(i));

			
		}
	}
	
//	Uppercase loware case
	public void casechange() {
		String name ="Java";
		String text ="Java";
	    System.out.println(name.toUpperCase());
		System.out.println(text.toLowerCase());			
	}
	
//	4. Remove Spaces from Beginning and End
	public void trimm() {
		String name ="  hello there  ";
		System.out.println(name.trim());			
	}
	
	
//	Check if a String Contains a Word
	
	public void wordcheck() {
		String name ="java programming";
		System.out.println(name.contains("java"));			
	}
	
//	Extract Part of a String
	
	public void extract() {
		String name ="programming";
		System.out.println(name.substring(3,7));			
	}
	
//	Replace a Word in a String
	
	public void replace() {
		String name ="I like Java";
		System.out.println(name.replace("Java", "Python"));			
	}
	
//	Split a Sentence into Words
	
	public void split() {
		String name ="I like Java";
		String [] words = name.split(" ");
		for(String check:words) {
			for(int i=0; i<check.length()-1;i++);
			System.out.println(check);
		}
	}
	
//	Find First and Last Position of a Character
	public void position() {
		String name ="Programming";
		System.out.println(name.indexOf("g"));
		System.out.println(name.lastIndexOf("g"));
	}
	
//	Check Prefix and Suffix
	public void prefixsuffix() {
		String name ="Programming";
		System.out.println(name.startsWith("Pro"));
		System.out.println(name.endsWith("ing"));
	}
	
//	Compare Two Strings
	
	public void comparestring() {
		String name ="Java";
		String word = "java";
		System.out.println(name.equals(word));
		System.out.println(name.equalsIgnoreCase(word));
	}
	
//	Concatenate Two Strings
	
	public void concatenate() {
		String name ="Hello";
		String word = "world";
		System.out.println(name+" "+word);
		System.out.println(name.concat(" ").concat(word));
	}
	
//	Check if String is Empty
	
	public void checkstring() {
		String name ="";
		System.out.println(name.isEmpty());
		System.out.println(name.isBlank());
	}
	
//	Reverse a String
	public void Reversestring() {
		String name ="vanita";
		for(int i=name.length()-1; i>=0;i--) {
		System.out.println(name.charAt(i));
		}
	}
	
	
//	Count Vowels and Consonants
	
	public void contable() {
		String name ="Programming";
		int vowel= 0;
		int constant =0;
		String[]vowels= {"a","e","i","o","u"};
		for(int i=0; i<=name.length()-1;i++) {
			if(name.charAt(i) == 'a' || 
					   name.charAt(i) == 'e' || 
					   name.charAt(i) == 'i' || 
					   name.charAt(i) == 'o' || 
					   name.charAt(i) == 'u') {
				vowel++;
			}
			else {
				constant++;
			}
		}
		System.out.println("vowels "+vowel);
		System.out.println("Constant "+ constant);
	}
	
	
//	Check Palindrome String
	
	public void Palindrome() {
		String str ="madam";
		String Reveresed ="";
		for(int i =str.length()-1;i>=0;i--) {
			Reveresed =Reveresed+str.charAt(i);
		}
		System.out.println(Reveresed);
		if(str.equals(Reveresed)) {
			System.out.println("Palindrome String");
		}
		else {
			System.out.println("not Palindrome String ");
		}	

			
		}
	
//	String buffer

	public void appending() {
		StringBuffer sb = new StringBuffer("Java");

		sb.append(" Programming");

		System.out.println(sb);
	}
	
//	compareTo() checks the lexicographic (dictionary) order.
	public void compareStrings() {

        String str1 = "Apple";
        String str2 = "Banana";

        int result = str1.compareTo(str2);

        System.out.println(result);
    }
	
	
	public static void main(String[]args) {
	Stringmaniplulation s1=new Stringmaniplulation();
	s1.length();
	s1.eachcharacter();
	s1.casechange();
	s1.trimm();
	s1.wordcheck();
	s1.extract();
	s1.replace();
	s1.split();
	s1.position();
	s1.prefixsuffix();
	s1.comparestring();
	s1.concatenate();
	s1.checkstring();
	s1.Reversestring();
	s1.contable();
	s1.Palindrome();
	s1.appending();
	s1.compareStrings();
	
	}

}
