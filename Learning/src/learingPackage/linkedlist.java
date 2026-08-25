package learingPackage;

import java.util.LinkedList;


public class linkedlist {
	public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>();

        list.add("Apple");
        list.add("Mango");
        list.add("Banana");

        System.out.println(list);
        
        
        LinkedList<Integer> numbers = new LinkedList<>();

        numbers.addFirst(10);
        numbers.addLast(20);
        numbers.addLast(30);

        System.out.println(numbers);

        for(int num : numbers){
            System.out.println(num);
        } 
        numbers.removeFirst();
        numbers.removeLast();
        
        for(int num : numbers){
            if(num % 2 == 0){
                System.out.println(num + " even");
            }
        }

        System.out.println(numbers);
    }

}
