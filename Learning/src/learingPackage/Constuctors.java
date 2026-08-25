package learingPackage;

public class Constuctors {

//	Simple Constructor Example
//	Constuctors(){
//		System.out.println("hello we are learning");
//	}
	
//	Constructor with Variables
//	
//	String color;
//	Constuctors(){
//		color ="red";
//	}
//	
//	void newmethod() {
//        System.out.println("Car color is " + color);
//	}
	
//	3. Parameterized Constructor
	
//	String name;
//	int age;
//	public Constuctors(String n, int a) {
//		name =n;
//		age =a;
//	}
//	
//	void display() {
//        System.out.println(name + " " + age);
//    }
//	
	
//	 Constructor with Odd Even Example
	
//	public Constuctors(int num) {
//		if(num%2==0) {
//			System.out.println(num+" even");
//		}
//		else {
//			System.out.println(num + " odd");
//		}
//	}
	
//	  constructor with array example
	public Constuctors(int[]array) {
		for(int i=0; i<array.length;i++) {
			if(array[i]%2==0) {
				System.out.println(array[i]+ "is even number");
			}
			else {
				System.out.println(array[i]+ "odd number");
			}
		}
	}
	
	public static void main(String[]args) {
//		Constuctors s1 =new Constuctors();
//		Constuctors s2 =new Constuctors();
//		s2.newmethod();
		
//		Constuctors s3 =new Constuctors("Vanita",27);
//		s3.display();
		
//		Constuctors s4 =new Constuctors(11); 
		 int[]numbers = {1,2,3,4,5};
		 Constuctors s5 =new Constuctors(numbers);
	}
}
