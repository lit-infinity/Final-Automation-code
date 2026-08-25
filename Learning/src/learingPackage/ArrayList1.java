package learingPackage;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;


//Using Arrays.asList()

//You can add many values in one line.


//ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(10,20,10,30,20));

//. Adding Using Loop
//
//If numbers come from another source, use a loop.
//
//Example:
//
//ArrayList<Integer> numbers = new ArrayList<>();
//
//for(int i = 1; i <= 5; i++) {
//    numbers.add(i * 10);
//}

public class ArrayList1 {
	
	
	
//	print the arralist
	public void display() {
		
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("Bananna");
        fruits.add("mango");
        System.out.println(fruits);
        
        
		
	}
	
//	2. Access Elements Using Index
	
	public void getelement() {
		ArrayList<String>names =new ArrayList<>();
		names.add("vanita");
		names.add("chintan");
		names.add("paresh");
        System.out.println(names.get(1));
		
	}
	
//	Remove element
	
	public void RemoveElement() {
		ArrayList<Integer>numbers =new ArrayList<>();
		numbers.add(20);
		numbers.add(10);
		numbers.add(5);
		numbers.add(50);
		numbers.add(45);
		numbers.remove(1);
		System.out.println(numbers);
		
//sorting the array
		Collections.sort(numbers);
		System.out.println("sorting");

		System.out.println(numbers);

	}
	
	
//	Loop
	
	public void loop() {
		ArrayList<Integer>numbers =new ArrayList<>();
		 numbers.add(10);
	     numbers.add(15);
	     numbers.add(20);
	     numbers.add(25);
		for(int i=0;i<numbers.size();i++) {
//			System.out.println(numbers.get(i));
			if(numbers.get(i)%2==0) {
				System.out.println(numbers.get(i)+" even");
			}
			else {
				System.out.println(numbers.get(i)+" odd");
			}
		}		
		
	}
	
	public void size() {
		ArrayList<String> students = new ArrayList<>();

        students.add("Rahul");
        students.add("Priya");
        students.add("Amit");

        System.out.println("Total Students: " + students.size());
        System.out.println(students);
	}
	
	
// Reverse an ArrayList
	public void Reverse() {
		ArrayList<Integer>numbers =new ArrayList<>();
		 numbers.add(10);
	     numbers.add(15);
	     numbers.add(20);
	     numbers.add(25);
		for(int i=numbers.size()-1;i>=0;i--) {
			System.out.println(numbers.get(i));
		}
	}
	
	
//	 Remove Duplicate Elements
	public void RemoveDuplicate() {
		ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(10);
        numbers.add(30);
        numbers.add(20);
        ArrayList<Integer> unique = new ArrayList<>();
        
        for(int num :numbers) {
        	if(!unique.contains(num)) {
        		unique.add(num);
        	}
        }
        System.out.println(unique);
        

	}
	
//	Find Maximum Number
	
	public void maxnumber() {
		ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(45);
        numbers.add(20);
        numbers.add(5);
        
        int max= numbers.get(0);
        
        for(int num:numbers) 
        	if(num>max) {
        		max=num;
        }
        
        System.out.println("Maximum number: " + max);

       
	}
	
//	Search Element in ArrayList
	public void search() {
		ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(45);
        numbers.add(20);
        numbers.add(5);
        
        int Search =10;
        if(numbers.contains(Search)) {
        	System.out.println("number found");
        }
        else {
        	System.out.println("not found");
        }
        }
        
	
	
	public static void main(String[]args) {
		ArrayList1 s1 =new ArrayList1();
		s1.display();
		s1.getelement();
		s1.RemoveElement();
		s1.loop();
		s1.size();
		s1.Reverse();
		s1.RemoveDuplicate();
		s1.maxnumber();
		s1.search();
	}

}
