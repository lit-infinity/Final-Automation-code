package learingPackage;

public class interview {
	
	public void check(int[] array){
		for(int i=0;i<array.length; i++) {
			if(array[i]%2==0) {
				System.out.println(array[i]+ " this is even number");
			}
			else {
				System.out.println(array[i]+ " this is odd number");
			}
		}
	}
	
//	occurence of the characters in the string
	
	public void occurence() {
		String name ="programmer";
		int count [] =new int[256];
		
		for(int i=0; i< name.length(); i++) {
			count[name.charAt(i)]++;
		}
		
		for(int i=0; i<name.length(); i++) {
			if (count[name.charAt(i)]!=0) {
				System.out.println(name.charAt(i)+ " is "+ count[name.charAt(i)]);
				count[name.charAt(i)]=0;
			}
		}

		
	}
	
//	Reverse the string using String Builder
	
	public void reverse(){
		
		String name ="vanita";
		StringBuilder sb =new StringBuilder(name);
		sb.reverse();
		System.out.println(sb);
		
//		for(int i=name.length()-1; i>=0; i--) {
//			System.out.print(name.charAt(i));
//		}
//		
	}
	
//	find the number is Palindrome or not
	
	public void Palindrome() {
		int num=121;
		int orignal =num;
		int reverse = 0;
		
		while(num>0) {
			int digit = num%10;
			reverse = reverse*10+digit;
			num= num/10;
		}
		
		if (reverse ==orignal) {
			System.out.println("Palindrome number");
		}
		else {
			System.out.println("not Palindrome number");
		}
		
	}
	
//	remove duplicate letters from the string
	
	public void Duplicateletter() {
		String name ="vanita";
		int array[] =new int[256];
		for(int i=0; i<name.length();i++){
			array[name.charAt(i)]++;
			
		}
		for(int i=0;i<name.length();i++) {
			if(array[name.charAt(i)]>=1) {
				System.out.print(name.charAt(i));
				array[name.charAt(i)]=0;
			}
		}
	}
	
//	Fibonacci Series(next number is sumof previous two number
	
	public void Fibonacci() {
		int first =0;
		int second =1;
		System.out.print(first+ " ");
		System.out.print(second+" ");
		for(int i=0;i<=4; i++) {
			int next =first + second;
			
			first=second;
			second=next;
			System.out.print(next+ " ");
		}
	}
	
	
//	Transform 'Mv name is so and so" to (My-name-is-so-and-so)"
	
	public void Transform() {
		String name ="'Mv name is so and so";
		name =name.replace(" ", "-");
		System.out.println("("+name+ ")");
	}
	
	
	
	public static void main(String[]args) {
		int[]array = {2,4,6};
		interview s1 =new interview();
//		s1.check(array);
		
//		s1.occurence();
//		s1.reverse();
//		s1.Palindrome();
//		s1.Duplicateletter();
//		s1.Fibonacci();
		s1.Transform();
		
	}
		
	

}
