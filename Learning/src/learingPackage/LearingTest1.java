package learingPackage;

import java.util.Iterator;

public class LearingTest1 {
	
//	Checking the static method
	 public static void checking(int a) {
		 if (a%2==0) {
			 System.out.println(a+" is even number");
		 }
		 else {
			 System.out.println(a+" is odd number");
		 }
	 }
	 
	 
//	 checking the non static method
	 public void testing(int num1, int num2,int num3) {
		 if(num1>=num2 && num1>=num3) {
			 System.out.println("num1 is max number");
		 }
		 else if (num2>=num1 && num2>=num3) {
			 System.out.println("num2 is max number");
		 }
		 else {
			 System.out.println("num3 is max number");
		 }
	 }
	 
	 
// checking the method with loop
	 public void loop() {
		 for(int i=0; i<=5;i++) {
			 System.out.println(i);
		 }
	 }
	 
//	 checking the pattern
	 public void pattern() {
//		 it will decide the number of rows
		 for(int i=1;i<=5;i++) {
//			 this will decide the how many starts you want to print in that raw
			 for(int j=1; j<=i;j++) {
				 System.out.print("*");
			 }
			 
			 System.out.println();
		 }
	 }
	 
//	 with parameters and return value
	 public int hello(int a, int b){
		 return a+b;
		  
		 
	 }
	 
//	 with parameters no return value
	 
	 public void vtest(int num) {
		 System.out.println(num);
	 }
	 
//	 No Parameters, With Return Value
	 
	public int check() {
		return 10;
	}
	
//	no parameters no return value
	public void rest() {
		System.out.println("hello there");
	}
	 
//	 checking the method with array
	
	public static void checkevenodd(int[]array) {
		for(int i=0; i<array.length;i++) {
			if (array[i]%2==0) {
				System.out.println(array[i]+" this is even number");
			}
			else {
				System.out.println(array[i]+" this is odd number");
			}
		}
	}
	
	
	public static void main(String[]args) {
//		Calling the static method
		checking(10);          
		
//		calling the non static method
		LearingTest1 obj = new LearingTest1();
		obj.testing(10,20,30);
		
//		calling the method with loop
		LearingTest1 obj1 =new LearingTest1();
		obj1.loop();
		
//		calling the patten
		LearingTest1 obj2 =new LearingTest1();
		obj2.pattern();
		
		LearingTest1 obj3 =new LearingTest1();
		int test=obj3.hello(10,20);
		System.out.println(test);
		
		LearingTest1 obj4 =new LearingTest1();
		obj4.vtest(20);
		
		LearingTest1 obj5 =new LearingTest1();
		int num =obj5.check();
		System.out.println(num);
		
		LearingTest1 obj6=new LearingTest1();
		obj6.rest();
		
		int[] numbers = {1,2,3,4,5};
		checkevenodd(numbers);
		
		
		
				
	}
	
}
