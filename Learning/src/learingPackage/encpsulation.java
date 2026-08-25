package learingPackage;


class Student{
	private String name;
	private int age;
	
	public void setName(String name) {
		this.name =name;
	}
	
	public void setAge(int age) {
		this.age =age;

	}
	
	public String getName(){
		return name;
	}
	
	public int getAge() {
		return age;
	}

}



class Employee {
    private int salary;

    public void setSalary(int salary) {
        if (salary > 0)
            this.salary = salary;
        else {
            System.out.println("invalid data");
        }
    }

    public int getSalary() {
        return salary;
    }
}
	
// 3. Read-Only Property

 class User{
	 private int  id =17;
	 public  int getID() {
		 return id;
	 }
 }

// single level inheritance
 
 class Animal{
	 public void eat(){
		 System.out.println("Animal is eating");
	 }
 }
 
 class Dog extends Animal{
	 public void bark() {
		 System.out.println("Dog is barking");
	 }
 }

// hirarchical level
 
 class Cat extends Animal{
	 public void meow() {
		 System.out.println("cat is meow");
	 }
 }
 
 
// multilevel inheritance
 class Vehicle{
	 public void start() {
		 System.out.println("vehicle is starting");
	 }
	 
 }
 
 class Car extends Vehicle{
	 public void drive() {
		 System.out.println("user driving the car");
	 }
 }
 
 class Sportscar extends Car{
	 public void speed() {
		 System.out.println("card speed is hign");
	 }
 }
 
 
// Method Overriding
 
 class puppy{
	 public void eat() {
		 System.out.println("hello this is overriding");
	 }
 }
 
 
//  checking the final variable, class and methods
 
// final variable
 
// class testing{
// final int check=100;
// 
// public void hello(int Check) {
//	 this.check =Check;
// }
// 
// }
// 
////  Final Method
// class checkmethod{
//	 public  final void  test() {
//		 System.out.println("hi there");
//	 }
// }
// 
// class finalmethod extends checkmethod{
//	 public void test() {
//		 System.out.println("hello");
//	 }
// }
// 
//// final class
// final class party{
//	 public void digg() {
//		 System.out.println("hello");
//	 }
// }
// 
// class bigb extends party{
//	 public void dip() {
//		 System.out.println("there");
//	 }
// }
 
 
class Product{
	private String productName;
	private int price;
	private int quantity;
	
	public void setproductname(String name) {
		this.productName =name;
	}
	
	public void setprice(int price) {
		this.price =price;
	}
	
	public void setquantity(int quantity) {
		this.quantity =quantity;
	}
	
	public String getproductname() {
		return productName;
	}
	
	public int getprice() {
		return price;
	}
	public int getqualtity() {
		return quantity;
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
// main method calling
public class encpsulation {
	public static void main(String[]args) {
		Student s1 =new Student();
		s1.setAge(18);
		s1.setName("vanita");
		System.out.println("Name: " + s1.getName());
        System.out.println("Age: " + s1.getAge());
        
		 Employee s2 = new Employee();
		 s2.setSalary(15000);
	     s2.setSalary(-18);
	     System.out.println("Salary: " + s2.getSalary());
	     
//	     Read only
	     
	     User s4 =new User();
	     System.out.println("Id  "+ s4.getID());
	     
//	    single inheritance
	     Dog s7 =new Dog();
	     s7.bark();
	     s7.eat();
	     
	     
//	     Multilevel inheritance
	     
	     Sportscar s8 =new Sportscar ();
	     s8.start();
	     s8.drive();
	     s8.speed();
	     
//	     hirarchical level
	     Cat s9 =new Cat();
	     s9.eat();
	     s9.meow();
	    
	     
// method overriding
	     puppy a1 =new puppy();
	     a1.eat();
	     
////	     final method
//	     finalmethod a2 =new finalmethod();
//	     a2.test();
	     
	     
	     Product name =new Product();
	     name.setproductname("Banana");
	     name.setprice(100);
	     name.setquantity(5);
	     System.out.println("product name is "+ name.getproductname());
	     System.out.println(name.getproductname()+ "  price is "+ name.getprice());
	     System.out.println(name.getproductname()+ "  Quantity is "+ name.getqualtity());
        
	}
 
}
