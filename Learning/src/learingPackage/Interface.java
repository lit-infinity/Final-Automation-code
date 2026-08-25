package learingPackage;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;


public class Interface {
//HashSet keeps unique elements and does not maintain insertion order
public void HashSetExample() {
	HashSet<String>list =new HashSet<>();
	list.add("vanita");
	list.add("vanita");
	list.add("Rohit");
    System.out.println(list);

}

//LinkedHashSetExample
//LinkedHashSet keeps unique elements and maintains insertion order.

public void LinkedHashSetExample() {
	HashSet<Integer>list =new HashSet<>();
	list.add(10);
	list.add(20);
	list.add(30);
    System.out.println(list);

}

//TreeSet stores elements in sorted order automatically.

public void TreeSetExample() {
	TreeSet<Integer>list =new TreeSet<>();
	list.add(50);
	list.add(10);
	list.add(70);
    System.out.println(list);

}

// Iterator (Traversing Collection)

//Iterator is used to loop through a collection.

public void Iterator() {
	ArrayList<String>list =new ArrayList<>();
	list.add("vanita");
	list.add("sneha");
	list.add("Rohit");
    
	Iterator<String> it =list.iterator();
    while(it.hasNext()) {
    	System.out.println(it.next());
    }
			
}

public void RemoveUsingIterator() {
	 ArrayList<Integer> numbers = new ArrayList<>();

     numbers.add(10);
     numbers.add(15);
     numbers.add(20);
     numbers.add(25);

     Iterator<Integer> it = numbers.iterator();

     while(it.hasNext()) {

         int num = it.next();

         if(num % 2 == 0) {
             it.remove();
         }
     }

     System.out.println(numbers);
	
}
			

public static void main(String[]args){
	Interface s1 =new Interface();
	s1.HashSetExample();
	s1.LinkedHashSetExample();
	s1.TreeSetExample();
	s1.Iterator();
	s1.RemoveUsingIterator();
}
}
